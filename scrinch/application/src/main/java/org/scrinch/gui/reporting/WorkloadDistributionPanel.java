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

import org.scrinch.gui.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.scrinch.jfree.chart.renderer.category.RoundedBarRenderer;
import org.scrinch.model.Item;
import org.scrinch.model.ItemSet;
import org.scrinch.model.ItemToolkit;
import org.scrinch.model.OriginType;
import org.scrinch.model.Project;
import org.scrinch.model.ScrinchEnvToolkit;
import org.scrinch.model.WorkType;

public class WorkloadDistributionPanel extends javax.swing.JPanel {

    private final static Color GAUGE_STROKE_COLOR = new Color(101, 132, 201);
    private final static Color GAUGE_GRADIENT_TOP_COLOR = new Color(194, 215, 244);
    private final static Color GAUGE_GRADIENT_BOTTOM_COLOR = new Color(72, 161, 243);

    public Date getRangeEnd() {
        return rangeEnd;
    }

    public void setRangeEnd(Date rangeEnd) {
        this.rangeEnd = rangeEnd;
    }

    public Date getRangeBeginning() {
        return rangeBeginning;
    }

    public void setRangeBeginning(Date rangeBeginning) {
        this.rangeBeginning = rangeBeginning;
    }

    public enum Criterion {

        BY_WORK_TYPE("work type"),
        BY_REQUEST_TYPE("request type"),
        BY_PROJECT("project");
        private String criterionLabel;

        Criterion(String criterionLabel) {
            this.criterionLabel = criterionLabel;
        }

        @Override
        public String toString() {
            return criterionLabel;
        }

        public static Criterion[] getCriteriaForProject() {
            return new Criterion[]{
                BY_WORK_TYPE,
                BY_REQUEST_TYPE
            };
        }

        public static Criterion valueOfLabel(String criterionLabel) {
            if (criterionLabel.equals(BY_WORK_TYPE.criterionLabel)) {
                return BY_WORK_TYPE;
            } else if (criterionLabel.equals(BY_REQUEST_TYPE.criterionLabel)) {
                return BY_REQUEST_TYPE;
            } else if (criterionLabel.equals(BY_PROJECT.criterionLabel)) {
                return BY_PROJECT;
            } else {
                return null;
            }
        }
    }

    public interface EvaluationValueAggregator {

        public Collection<Item> getItemsByWorkType(WorkType workType, Date from, Date to);

        public Collection<Item> getItemsByWorkType(ItemSet itemSet, WorkType workType, Date from, Date to);

        public Collection<Item> getItemsByRequestType(OriginType requestType, Date from, Date to);

        public Collection<Item> getItemsByRequestType(ItemSet itemSet, OriginType requestType, Date from, Date to);

        public Collection<Item> getItems(Project project, Date from, Date to);
    }

    private static Collection<Item> getItemsInRange(Collection<Item> baseCollection, Date from, Date to) {
        Collection<Item> toReturn = new ArrayList();
        toReturn.addAll(baseCollection);
        if (from != null) {
            for (Item item : baseCollection) {
                Item.Visa lastVisa = item.getVisasHistory().peek();
                if (lastVisa.date.compareTo(from) <= 0) {
                    toReturn.remove(item);
                }
            }
        }
        if (to != null) {
            for (Item item : baseCollection) {
                Item.Visa lastVisa = item.getVisasHistory().peek();
                if (lastVisa.date.compareTo(to) >= 0) {
                    toReturn.remove(item);
                }
            }
        }
        return toReturn;
    }

    private class WorkDoneEvaluationValueAggregator implements EvaluationValueAggregator {

        public Collection<Item> getItemsByWorkType(WorkType workType, Date from, Date to) {
            return getItemsInRange(ItemToolkit.findWorkDoneItemsWithWorkType(toolkit.getItemFactory().getItemList(), workType), from, to);
        }

        public Collection<Item> getItemsByWorkType(ItemSet itemSet, WorkType workType, Date from, Date to) {
            return getItemsInRange(ItemToolkit.findWorkDoneItemsWithWorkType(itemSet.getItemList(), workType), from, to);
        }

        public Collection<Item> getItemsByRequestType(OriginType requestType, Date from, Date to) {
            return getItemsInRange(ItemToolkit.findWorkDoneItemsWithOriginType(toolkit.getItemFactory().getItemList(), requestType), from, to);
        }

        public Collection<Item> getItemsByRequestType(ItemSet itemSet, OriginType requestType, Date from, Date to) {
            return getItemsInRange(ItemToolkit.findWorkDoneItemsWithOriginType(itemSet.getItemList(), requestType), from, to);
        }

        public Collection<Item> getItems(Project project, Date from, Date to) {
            return getItemsInRange(project.getWorkDoneItems(), from, to);
        }

        @Override
        public String toString() {
            return "Work done";
        }
    }

    private class WorkToBeDoneEvaluationValueAggregator implements EvaluationValueAggregator {

        public Collection<Item> getItemsByWorkType(WorkType workType, Date from, Date to) {
            return getItemsInRange(ItemToolkit.findWorkToBeDoneItemsWithWorkType(toolkit.getItemFactory().getItemList(), workType), from, to);
        }

        public Collection<Item> getItemsByWorkType(ItemSet itemSet, WorkType workType, Date from, Date to) {
            return getItemsInRange(ItemToolkit.findWorkToBeDoneItemsWithWorkType(itemSet.getItemList(), workType), from, to);
        }

        public Collection<Item> getItemsByRequestType(OriginType requestType, Date from, Date to) {
            return getItemsInRange(ItemToolkit.findWorkToBeDoneItemsWithOriginType(toolkit.getItemFactory().getItemList(), requestType), from, to);
        }

        public Collection<Item> getItemsByRequestType(ItemSet itemSet, OriginType requestType, Date from, Date to) {
            return getItemsInRange(ItemToolkit.findWorkToBeDoneItemsWithOriginType(itemSet.getItemList(), requestType), from, to);
        }

        public Collection<Item> getItems(Project project, Date from, Date to) {
            return getItemsInRange(project.getWorkToBeDoneItems(), from, to);
        }

        @Override
        public String toString() {
            return "Work to be done";
        }
    }

    private class AllWorkEvaluationValueAggregator implements EvaluationValueAggregator {

        public Collection<Item> getItemsByWorkType(WorkType workType, Date from, Date to) {
            return getItemsInRange(ItemToolkit.findItemsWithWorkType(toolkit.getItemFactory().getItemList(), workType), from, to);
        }

        public Collection<Item> getItemsByWorkType(ItemSet itemSet, WorkType workType, Date from, Date to) {
            return getItemsInRange(ItemToolkit.findItemsWithWorkType(itemSet.getItemList(), workType), from, to);
        }

        public Collection<Item> getItemsByRequestType(OriginType requestType, Date from, Date to) {
            return getItemsInRange(ItemToolkit.findItemsWithOriginType(toolkit.getItemFactory().getItemList(), requestType), from, to);
        }

        public Collection<Item> getItemsByRequestType(ItemSet itemSet, OriginType requestType, Date from, Date to) {
            return getItemsInRange(ItemToolkit.findItemsWithOriginType(itemSet.getItemList(), requestType), from, to);
        }

        public Collection<Item> getItems(Project project, Date from, Date to) {
            return getItemsInRange(project.getAllItems(), from, to);
        }

        @Override
        public String toString() {
            return "Work done and to be done";
        }
    }
    protected final EvaluationValueAggregator WORK_DONE_AGGREGATOR;
    protected final EvaluationValueAggregator WORK_TO_BE_DONE_AGGREGATOR;
    protected final EvaluationValueAggregator ALL_WORK_AGGREGATOR;

    private JFreeChart chart;
    private JFreeChart pie;
    private ChartPanel chartPanel;
    private ChartPanel pieChartPanel;
    private String title;
    private Criterion criterion;
    private double meanVelocity;
    private Project project;
    private EvaluationValueAggregator aggregator;
    private Date rangeBeginning;
    private Date rangeEnd;
    private DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
    private DefaultPieDataset pieDataset = new DefaultPieDataset();

    public Criterion getCriterion(){
        return criterion;
    }

    private final ScrinchEnvToolkit toolkit;

    public WorkloadDistributionPanel() {
        this(new ScrinchEnvToolkit());
    }

    public WorkloadDistributionPanel(ScrinchEnvToolkit toolkit) {
        this.toolkit = toolkit;
        WORK_DONE_AGGREGATOR = new WorkDoneEvaluationValueAggregator();
        WORK_TO_BE_DONE_AGGREGATOR = new WorkToBeDoneEvaluationValueAggregator();
        ALL_WORK_AGGREGATOR = new AllWorkEvaluationValueAggregator();
        initComponents();
    }


    public void initComponent(Criterion criterion, Project project) {
        this.criterion = criterion;
        this.project = project;
        meanVelocity = toolkit.getMeanVelocity();
    }

    public void setAggregator(EvaluationValueAggregator aggregator) {
        this.aggregator = aggregator;
        drawGraphs();
        this.piePanel.repaint();
        this.graphPanel.repaint();
    }

    public void draw(Graphics2D g2, Rectangle2D dimensions) {
        int x = (int) dimensions.getX();
        int y = (int) dimensions.getY();
        int width = (int) dimensions.getWidth();
        int height = (int) dimensions.getHeight();

        g2.setColor(Color.BLACK);
        g2.setFont(titleLabel.getFont());
        int fontHeight = g2.getFontMetrics().getHeight();
        int stringWidth = g2.getFontMetrics().stringWidth(title);
        g2.drawString(title, x + width / 2 - stringWidth / 2, y + fontHeight);

        int upperMargin = fontHeight + 8;

        Rectangle2D chartR2d = new Rectangle2D.Double(x, y + upperMargin, width / 2, height - upperMargin);
        Rectangle2D pieR2d = new Rectangle2D.Double(x + width / 2 + 1, y + upperMargin, width / 2, height - upperMargin);

        pie.setBackgroundPaint(null);
        pie.draw(g2, pieR2d);
        chart.setBackgroundPaint(null);
        chart.draw(g2, chartR2d);
    }

    public Hashtable<String,Double> getDatasetsByWorkType(){
        Hashtable<String,Double> toReturn = new Hashtable<String,Double>();
        List<WorkType> workTypes = toolkit.getWorkTypeFactory().getWorkTypeList();
        for (int i = 0; i < workTypes.size(); i++) {

            double work = 0;
            if (project != null) {
                for (ItemSet itemSet : project.getProjectItemSetList()) {
                    work += ItemToolkit.getTotalFiboPoints(aggregator.getItemsByWorkType(itemSet, workTypes.get(i), getRangeBeginning(), getRangeEnd())) / meanVelocity;
                }
            } else {
                work = ItemToolkit.getTotalFiboPoints(
                        aggregator.getItemsByWorkType(workTypes.get(i), getRangeBeginning(), getRangeEnd())) / meanVelocity;
            }

            if (work > 0) {
                toReturn.put(workTypes.get(i).toString(),roundToTwoDecimalsOnly(work));
            }
        }
        return toReturn;
    }

    public Hashtable<String,Double> getDatasetsByRequestType(){
        Hashtable<String,Double> toReturn = new Hashtable<String,Double>();
        List<OriginType> requestTypes = toolkit.getOriginTypeFactory().getOriginTypeList();
        for (int i = 0; i < requestTypes.size(); i++) {

            double work = 0;
            if (project != null) {
                for (ItemSet itemSet : project.getProjectItemSetList()) {
                    work += ItemToolkit.getTotalFiboPoints(
                            aggregator.getItemsByRequestType(itemSet, requestTypes.get(i), getRangeBeginning(), getRangeEnd())) / meanVelocity;
                }
            } else {
                work = ItemToolkit.getTotalFiboPoints(
                        aggregator.getItemsByRequestType(requestTypes.get(i), getRangeBeginning(), getRangeEnd())) / meanVelocity;
            }
            if (work > 0) {
                toReturn.put(requestTypes.get(i).toString(),roundToTwoDecimalsOnly(work));
            }
        }
        return toReturn;
    }

    public Hashtable<String,Double> getDatasetsByProject(){
        Hashtable<String,Double> toReturn = new Hashtable<String,Double>();
        for (Project pjct : toolkit.getProjectFactory().getList()) {

            double work = ItemToolkit.getTotalFiboPoints(
                    aggregator.getItems(pjct, getRangeBeginning(), getRangeEnd())) / meanVelocity;

            if (work > 0) {
                toReturn.put(pjct.getProjectName(),roundToTwoDecimalsOnly(work));
            }
        }
        return toReturn;
    }

    private double roundToTwoDecimalsOnly(double rawValue){
        return ((double)Math.round(rawValue*100))/100.0;
    }

    public Hashtable<String,Double> getDatasets(){
        switch(criterion){
            case BY_PROJECT:
                return getDatasetsByProject();
            case BY_REQUEST_TYPE:
                return getDatasetsByRequestType();
        }
        return getDatasetsByWorkType();
    }

    private void fillDatasets(Hashtable<String,Double> dataset, String criterion){
        Enumeration<String> keys = dataset.keys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            Double workCount = dataset.get(key);
            categoryDataset.addValue(
                workCount,
                criterion,
                key);

            pieDataset.setValue(key, workCount);
        }
    }

    private void fillDatasets() {
        switch (criterion) {
            case BY_WORK_TYPE:
                fillDatasets(getDatasetsByWorkType(),Criterion.BY_WORK_TYPE.toString());
                break;
            case BY_REQUEST_TYPE:
                fillDatasets(getDatasetsByRequestType(),Criterion.BY_REQUEST_TYPE.toString());
                break;
            case BY_PROJECT:
                fillDatasets(getDatasetsByProject(),Criterion.BY_PROJECT.toString());
                break;
        }
    }

    private void drawGraphs() {
        categoryDataset.clear();
        pieDataset.clear();

        fillDatasets();

        title = this.aggregator.toString();

        title += " by " + criterion.toString();

        if (this.getRangeBeginning() != null) {
            title += " since " + ScrinchGuiToolkit.getDefaultDayFormat().format(this.getRangeBeginning());
        } else {
            Date oldest = toolkit.getItemFactory().getOldestEvaluatedItemDate();
            if( oldest==null ){
                oldest = new Date();
            }
            title += " since " +
                    ScrinchGuiToolkit.getDefaultDayFormat().format(oldest);
        }

        if (this.getRangeEnd() != null) {
            title += " to " + ScrinchGuiToolkit.getDefaultDayFormat().format(this.getRangeEnd());
        }

        if (project != null) {
            title += " for project " + project.getProjectName();
        } else {
            title += " for all projects";
        }

        titleLabel.setText(title);

        // chart creation
        chart = ChartFactory.createBarChart(
                "",
                criterion.toString(),
                "Work",
                categoryDataset,
                PlotOrientation.HORIZONTAL,
                true,
                true,
                false);
        if (chartPanel == null) {
            chartPanel = new ChartPanel(chart, false);
            graphPanel.add(chartPanel, BorderLayout.CENTER);
        } else {
            chartPanel.setChart(chart);
        }

        CategoryPlot plot = chart.getCategoryPlot();
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(
                CategoryLabelPositions.createUpRotationLabelPositions(
                Math.PI / 6.0));
        plot.setDomainGridlinesVisible(true);

        // plot.setForegroundAlpha(0.5f);
        RoundedBarRenderer renderer = new RoundedBarRenderer();
        renderer.setSeriesOutlinePaint(0,GAUGE_STROKE_COLOR);
        renderer.setSeriesPaint(0, new GradientPaint(0f, 0f, GAUGE_GRADIENT_TOP_COLOR, 1f, 1f, GAUGE_GRADIENT_BOTTOM_COLOR, true));
        plot.setRenderer(renderer);

        // pie creation
        pie = ChartFactory.createPieChart("", pieDataset, true, true, false);
        if (pieChartPanel == null) {
            pieChartPanel = new ChartPanel(pie, false);
            piePanel.add(pieChartPanel, BorderLayout.CENTER);
        } else {
            pieChartPanel.setChart(pie);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        graphPanel = new javax.swing.JPanel();
        piePanel = new javax.swing.JPanel();
        titlePanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        mainPanel.setLayout(new javax.swing.BoxLayout(mainPanel, javax.swing.BoxLayout.LINE_AXIS));

        graphPanel.setLayout(new java.awt.BorderLayout());
        mainPanel.add(graphPanel);

        piePanel.setLayout(new java.awt.BorderLayout());
        mainPanel.add(piePanel);

        add(mainPanel, java.awt.BorderLayout.CENTER);

        titleLabel.setFont(new java.awt.Font("Dialog", 1, 18));
        titlePanel.add(titleLabel);

        add(titlePanel, java.awt.BorderLayout.NORTH);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel graphPanel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel piePanel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables
}
