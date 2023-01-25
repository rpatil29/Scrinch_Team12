/*
 Scrinch is a stand-alone Swing application that helps managing your
 projects based on Agile principles.
 Copyright (C) 2007  Julien Piaser, Jerome Layat, Peter Fluekiger,
 Christian Lebaudy, Jean-Marc Borer

 Scrinch is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 Scrinch is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.scrinch.gui.reporting;

import com.lowagie.text.pdf.PdfContentByte;
import java.awt.BasicStroke;
import org.scrinch.gui.*;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.scrinch.model.Item;
import org.scrinch.model.ScrinchEnvToolkit;
import org.scrinch.model.Sprint;
import org.scrinch.pdf.PDFExport;

public class SprintGraphDialog extends javax.swing.JDialog implements Pageable{

    private Sprint sprint;
    private JFreeChart chart;
    private static final float LINE_STROKE_WIDTH = 3.0f;

    private final ScrinchEnvToolkit toolkit;

    public SprintGraphDialog(java.awt.Frame parent, boolean modal, Sprint sprint, ScrinchEnvToolkit toolkit) {
        super(parent, modal);
        initComponents();
        this.toolkit = toolkit;
        ScrinchGuiToolkit.centerFrame(this);
        this.sprint =sprint;
        createGraph();
    }
    
    private final static String POSSIBLE_LABEL = "Possible";
    private final static String OBJECTIVE_LABEL = "Objective";
    private final static String RESULT_LABEL = "Result";
    private final static String BEGINNING_OF_SPRINT_LABEL = "Beginning of sprint";
    
    private CategoryDataset fillDataSetUp(  DefaultCategoryDataset dataset, 
                                            Collection<Item> itemList, 
                                            List<Date> dateList, 
                                            int possibleWork, 
                                            int theoreticalWork){
        int dateWork = 0;
        int i = 0;

        dataset.addValue(0,RESULT_LABEL,BEGINNING_OF_SPRINT_LABEL);
        dataset.addValue(0,OBJECTIVE_LABEL,BEGINNING_OF_SPRINT_LABEL);
        dataset.addValue(0,POSSIBLE_LABEL,BEGINNING_OF_SPRINT_LABEL);
        
        for (Date date : dateList) {
            String category = ScrinchGuiToolkit.getDefaultDayFormat().format(date);
            if(date.compareTo(toolkit.getCurrentDate())<=0){
                for (Item item : itemList) {
                    Item.Visa lastVisa = item.getLastDoneVisaIfTaskDoneInSprint(sprint);
                    if(lastVisa != null &&
                            lastVisa.date.equals(date) &&
                            lastVisa.status.isWorkDone()){
                        dateWork += item.getEvaluation().intValue();
                    }
                }
                dataset.addValue((double)dateWork,RESULT_LABEL,category);
            }
            dataset.addValue(
                ((double)theoreticalWork*(++i)/(double)dateList.size()),
                OBJECTIVE_LABEL,
                category);
            
            dataset.addValue(
                ((double)possibleWork*i/(double)dateList.size()),
                POSSIBLE_LABEL,
                category);
        }

        return dataset;
    }
    
    private CategoryDataset fillDataSetDown(DefaultCategoryDataset dataset, 
                                            Collection<Item> itemList, 
                                            List<Date> dateList, 
                                            int possibleWork, 
                                            int theoreticalWork){

        dataset.addValue(theoreticalWork,RESULT_LABEL,BEGINNING_OF_SPRINT_LABEL);
        dataset.addValue(theoreticalWork,OBJECTIVE_LABEL,BEGINNING_OF_SPRINT_LABEL);
        dataset.addValue(theoreticalWork,POSSIBLE_LABEL,BEGINNING_OF_SPRINT_LABEL);

        int dateWork = theoreticalWork;
        int i = dateList.size();
        for (Date date : dateList) {
            String category = ScrinchGuiToolkit.getDefaultDayFormat().format(date);
            if(date.compareTo(toolkit.getCurrentDate())<=0){
                for (Item item : itemList) {
                    Item.Visa lastVisa = item.getLastDoneVisaIfTaskDoneInSprint(sprint);
                    if(lastVisa != null &&
                            lastVisa.date.equals(date) &&
                            lastVisa.status.isWorkDone()){
                        dateWork -= item.getEvaluation().intValue();
                    }
                }
                dataset.addValue((double)dateWork,RESULT_LABEL,category);
            }
            dataset.addValue(
                ((double)theoreticalWork*(--i)/(double)dateList.size()),
                OBJECTIVE_LABEL,
                category);
            
            dataset.addValue(
                ((double)possibleWork*i/(double)dateList.size())+theoreticalWork-possibleWork,
                POSSIBLE_LABEL,
                category);
        }
        
        return dataset;
        
    }
    
    private CategoryDataset createDataSet(boolean up){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Collection<Item> itemList = sprint.getItemList();
        List<Date> dateList = sprint.getWorkingDates();

        int possibleWork = (int)Math.round(sprint.getAvailableResourcesInFiboPoints());       
        
        int theoreticalWork = 0;
        for (Item item : itemList) {
            if(item.isActive()){
                theoreticalWork+=item.getEvaluation().intValue();
            }
        }

        if(up){
            return fillDataSetUp(   dataset, 
                                    itemList, 
                                    dateList, 
                                    possibleWork, 
                                    theoreticalWork);
        }else{
            return fillDataSetDown( dataset, 
                                    itemList, 
                                    dateList, 
                                    possibleWork, 
                                    theoreticalWork);
        }

    }
    
    private static class DashedDrawingSupplier extends DefaultDrawingSupplier{
        float dashUnit = 3.0f;
        @Override
        public Stroke getNextStroke(){
            float dash[] = { dashUnit };
            DashedDrawingSupplier.this.dashUnit += 5.0f;
            return new BasicStroke(LINE_STROKE_WIDTH, BasicStroke.CAP_BUTT,
            BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
        }
    }

    private void createGraph(){
        CategoryDataset dataset = createDataSet(toolkit.isBurnUpChart());

        chart = ChartFactory.createLineChart(
                sprint.getTitle(),
                "Date",
                "Work",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);
        
        ChartPanel chartPanel = new ChartPanel(chart, false);
        graphPanel.add(chartPanel,BorderLayout.CENTER);

        CategoryPlot plot = chart.getCategoryPlot();  
        plot.setDrawingSupplier(new DashedDrawingSupplier());
                
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(
                CategoryLabelPositions.createUpRotationLabelPositions(
                        Math.PI / 6.0));
        plot.setDomainGridlinesVisible(true);        
    }

    public Printable getPrintable(int pageIndex) throws IndexOutOfBoundsException {
        Printable printable = new Printable() {
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                graphPanel.getComponent(0).paint(graphics);

                return Printable.PAGE_EXISTS;
            }
        };
        return printable;
    }

    public PageFormat getPageFormat(int pageIndex) throws IndexOutOfBoundsException {
        PageFormat pageFormat = new PageFormat();
        pageFormat.setOrientation(PageFormat.LANDSCAPE);
        return  pageFormat;
    }

    public int getNumberOfPages() {
        return 1;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        graphPanel = new javax.swing.JPanel();
        menuPanel = new javax.swing.JPanel();
        exportToPDFButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        graphPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        graphPanel.setLayout(new java.awt.BorderLayout());
        getContentPane().add(graphPanel, java.awt.BorderLayout.CENTER);

        exportToPDFButton.setText("Export to PDF");
        exportToPDFButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportToPDFButtonActionPerformed(evt);
            }
        });
        menuPanel.add(exportToPDFButton);

        getContentPane().add(menuPanel, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exportToPDFButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportToPDFButtonActionPerformed
        PDFExport.exportToPDF(this.sprint.getTitle().replace(' ', '_') + "_progress.pdf", this, new PDFExport() {
            public void draw(PdfContentByte cb, Graphics2D g2, Rectangle2D r2d){
                chart.setBackgroundPaint(null);
                chart.draw(g2, r2d);
            }
        });
    }//GEN-LAST:event_exportToPDFButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exportToPDFButton;
    private javax.swing.JPanel graphPanel;
    private javax.swing.JPanel menuPanel;
    // End of variables declaration//GEN-END:variables

}
