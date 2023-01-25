/*
Scrinch is a stand-alone Swing application that helps managing your
projects based on Agile principles.
Copyright (C) 2007  Julien Piaser, Jerome Layat, Peter Fluckiger,
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
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYDifferenceRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.scrinch.model.ScrinchEnvToolkit;
import org.scrinch.model.Sprint;
import org.scrinch.pdf.PDFExport;

public class VelocityHistoryPanel extends javax.swing.JPanel {

    private JFreeChart chart;
    private final ScrinchEnvToolkit toolkit;

    public VelocityHistoryPanel() {
        this(new ScrinchEnvToolkit());
    }

    public VelocityHistoryPanel(ScrinchEnvToolkit toolkit) {
        this.toolkit = toolkit;
        initComponents();
        toolkit.addListener(new ScrinchEnvToolkit.Listener() {
            public void preferencesChanged() {
            }
            public void dataLoaded() {
                resetGraph();
            }

            public void dataChanged() {
            }
        });
        resetGraph();
    }

    private TimeSeriesCollection createDataSet(){
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        TimeSeries baseVelocitySerie = new TimeSeries("Possible velocity");
        TimeSeries resultingVelocitySerie = new TimeSeries("Resulting velocity");

        dataset.addSeries(resultingVelocitySerie);
        dataset.addSeries(baseVelocitySerie);

        List<Sprint> topicalSprints = toolkit.getSprintFactory().findNonTopicalSprints();
        for (Sprint sprint : topicalSprints) {
            fillTimeSeriesWith(baseVelocitySerie,resultingVelocitySerie,sprint);
        }
        fillTimeSeriesWith(baseVelocitySerie,resultingVelocitySerie, toolkit.getSprintFactory().findTodaySprint());
        return dataset;
    }

    private void fillTimeSeriesWith(TimeSeries baseVelocitySerie,TimeSeries resultingVelocitySerie, Sprint sprint){
        if(resultingVelocitySerie!=null && sprint!=null){
            try{
                resultingVelocitySerie.add(new Day(sprint.getStartDate()),sprint.getResultingVelocity());
                baseVelocitySerie.add(new Day(sprint.getStartDate()),sprint.getPossibleVelocity());
            } catch(Exception se){}
        }
    }

    private void resetGraph(){
        TimeSeriesCollection dataset = createDataSet();
        chart = ChartFactory.createTimeSeriesChart(
                "Velocity evolution",
                "Date",
                "Velocity",
                dataset,
                true,
                true,
                false);
        ChartPanel chartPanel = new ChartPanel(chart, false);
        XYPlot plot = chart.getXYPlot();

        XYDifferenceRenderer differenceRenderer = new XYDifferenceRenderer(Color.GREEN, Color.RED, true);
        plot.setRenderer(differenceRenderer);

        plot.getDomainAxis();
        plot.getRangeAxis().setLowerBound(0);
        plot.setDomainGridlinesVisible(true);

        graphPanel.removeAll();
        graphPanel.add(chartPanel,BorderLayout.CENTER);
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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        graphPanel = new javax.swing.JPanel();
        menuPanel = new javax.swing.JPanel();
        exportToPDFButton = new javax.swing.JButton();

        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        graphPanel.setOpaque(false);
        graphPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        graphPanel.setLayout(new java.awt.BorderLayout());
        add(graphPanel, java.awt.BorderLayout.CENTER);

        menuPanel.setOpaque(false);

        exportToPDFButton.setText("Export to PDF");
        exportToPDFButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportToPDFButtonActionPerformed(evt);
            }
        });
        menuPanel.add(exportToPDFButton);

        add(menuPanel, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void exportToPDFButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportToPDFButtonActionPerformed
        PDFExport.exportToPDF("velocityEvolution.pdf", this, new PDFExport() {
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
