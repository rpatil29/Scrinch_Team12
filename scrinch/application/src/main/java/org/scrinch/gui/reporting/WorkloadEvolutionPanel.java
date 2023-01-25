package org.scrinch.gui.reporting;

import org.scrinch.jfree.data.xy.TaskLoad;
import java.awt.BasicStroke;
import org.scrinch.jfree.chart.renderer.xy.ProjectXYRenderer;
import org.scrinch.jfree.data.xy.ProjectSeriesCollection;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYLineAnnotation;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYStepAreaRenderer;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.scrinch.model.Item;
import org.scrinch.model.ItemToolkit;
import org.scrinch.model.Project;
import org.scrinch.model.ScrinchEnvToolkit;

public class WorkloadEvolutionPanel extends javax.swing.JPanel {

    private static final String DATE_PATTERN = "MMM yy";
    private static final String DAY_DATE_PATTERN = "dd MMM yy";
    private static final Color TODAY_LINE_COLOR = new Color(120, 33, 33);
    private final static Color BACKGROUND_COLOR = new Color(241, 248, 251, 180);
    private final static Color STROKE_COLOR = new Color(180, 180, 180);
    private final static Color GAUGE_STROKE_COLOR = new Color(101, 132, 201);
    private final static Color GAUGE_GRADIENT_TOP_COLOR = new Color(194, 215, 244);
    private final static Color GAUGE_GRADIENT_BOTTOM_COLOR = new Color(72, 161, 243);
    private final static Color MAINTENANCE_GAUGE_STROKE_COLOR = new Color(189, 93, 34);
    private final static Color MAINTENANCE_GAUGE_GRADIENT_TOP_COLOR = new Color(255, 209, 197);
    private final static Color MAINTENANCE_GAUGE_GRADIENT_BOTTOM_COLOR = new Color(255, 130, 42);
    private final static Stroke TODAY_STROKE = new BasicStroke();
    private final static int TASK_SERIES_INDEX = 1;
    private final static int MAINTENANCE_TASK_SERIES_INDEX = 0;

    private final DateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN, Locale.ENGLISH);
    private final DateFormat labelDateFormat = new SimpleDateFormat(DAY_DATE_PATTERN, Locale.ENGLISH);
    
    private class MaintenanceTaskLoad {
        String name;
        int averageDailyWorkload;
    }

    private class GraphModel {
        Date today;
        Date startDate;
        Date endDate;
        int businessDays;
        int totalWorkToBeDone;
        Collection<MaintenanceTaskLoad> maintenanceTasks = new ArrayList<MaintenanceTaskLoad>();
        Collection<TaskLoad> activeTasks = new ArrayList<TaskLoad>();
        List<Date> sortedDates = new ArrayList<Date>();
    }
    
    private GraphModel createGraphModel() {
        GraphModel model = new GraphModel();
        Calendar cal = toolkit.getPreparedCalendarInstance();
        model.today = cal.getTime();
        model.startDate = toolkit.getProjectFactory().findEarliestStartingDateAmongActiveProjects();
        cal.add(Calendar.YEAR, 1);
        model.endDate = cal.getTime();
        model.businessDays = toolkit.getBusinessDaysCount(model.startDate, model.endDate);
        model.totalWorkToBeDone = 0;

        List<Project> projectListSortedByDate = new ArrayList<Project>();
        projectListSortedByDate.addAll(toolkit.getProjectFactory().getList());
        Collections.sort(projectListSortedByDate,Project.BY_DATE_ASCENDING_COMPARATOR);

        for (Project project : projectListSortedByDate) {
            boolean maintenance = project.isMaintenanceProject();
            int workToBeDone = project.getWorkToBeDoneItemEvaluationCount();
            if (maintenance) {
                MaintenanceTaskLoad maintenanceTask = new MaintenanceTaskLoad();
                maintenanceTask.name = project.getProjectName();

                int workDone = project.getWorkDoneItemEvaluationCount();
                Collection<Item> items = project.getWorkDoneItems();
                Date currentOldestDate = ItemToolkit.getOldestDate(items);
                Date currentLatestDate = ItemToolkit.getLatestDate(items);

                int maintenanceBusinessDays =
                        toolkit.getBusinessDaysCount(currentOldestDate, currentLatestDate);
                if (maintenanceBusinessDays > 0) {
                    maintenanceTask.averageDailyWorkload = workDone / maintenanceBusinessDays;
                }
                model.maintenanceTasks.add(maintenanceTask);
            } else if (project.isActiveAndNotMaintenance()) {
                TaskLoad activeTask = new TaskLoad(toolkit);
                activeTask.setName(project.getProjectName());

                model.totalWorkToBeDone += workToBeDone;
                activeTask.setWorkDone(project.getWorkDoneItemEvaluationCount());
                activeTask.setTotalWork(project.getActiveItemEvaluationCount());
                activeTask.setBegin(project.getStartDate());
                activeTask.setEnd(project.getEndDate());
                model.activeTasks.add(activeTask);

                model.sortedDates.add(activeTask.getBegin());
                model.sortedDates.add(activeTask.getEnd());

            } else if (project.getEndDate().after(model.startDate)) {
                TaskLoad activeTask = new TaskLoad(toolkit);
                activeTask.setName(project.getProjectName());

                // artificial work done for the period considered
                Date artificialBeginDate = model.startDate.after(project.getStartDate())?model.startDate:project.getStartDate();
                
                activeTask.setWorkDone(project.getWorkDoneItemEvaluationCount() * toolkit.getBusinessDaysCount(artificialBeginDate, project.getEndDate()) / project.getBusinessDaysCount());
                activeTask.setTotalWork(project.getActiveItemEvaluationCount() * toolkit.getBusinessDaysCount(artificialBeginDate, project.getEndDate()) / project.getBusinessDaysCount());
                activeTask.setBegin(artificialBeginDate);
                activeTask.setEnd(project.getEndDate());
                model.activeTasks.add(activeTask);

                if (!activeTask.getBegin().before(model.startDate)) {
                    model.sortedDates.add(activeTask.getBegin());
                }
                model.sortedDates.add(activeTask.getEnd());
            }
        }
        model.sortedDates.add(model.endDate);
        Collections.sort(model.sortedDates);

        return model;
    }

    private JFreeChart createChart(GraphModel model) {
        // JFree chart

        XYPlot totalWorkloadSubplot = createTotalWorkloadPlot(model);
        XYPlot projectsWorkloadSubplot = createProjectsWorkloadPlot(model);

        DateAxis domainAxis = new DateAxis("Time");
        domainAxis.setDateFormatOverride(dateFormat);
        domainAxis.setUpperMargin(0.01);
        domainAxis.setLowerMargin(0.01);

        CombinedDomainXYPlot plot = new CombinedDomainXYPlot(domainAxis);
        plot.setGap(5.0);
        plot.add(totalWorkloadSubplot, 2);
        plot.add(projectsWorkloadSubplot, 3);
        plot.setOrientation(PlotOrientation.VERTICAL);

        JFreeChart result = new JFreeChart("Workload Graph", JFreeChart.DEFAULT_TITLE_FONT, plot, false);
        return result;
    }

    private XYPlot createTotalWorkloadPlot(GraphModel model) {
        XYSeriesCollection serie = new XYSeriesCollection();
        fillWorkloadDataset(model, serie);

        XYStepAreaRenderer renderer = new XYStepAreaRenderer();
        renderer.setOutline(true);
        renderer.setSeriesPaint(0, new Color(255, 170, 170, 180));
        // Range axis
        NumberAxis rangeAxis = new NumberAxis("Total Workload");
        rangeAxis.setRange(0, 200);
        rangeAxis.setTickUnit(new NumberTickUnit(50.0));
        rangeAxis.setNumberFormatOverride(new DecimalFormat("### '%'"));
        // Annotation
        long todayTime = model.today.getTime();
        XYLineAnnotation todayLineAnnotation = new XYLineAnnotation(todayTime, 200, todayTime, 0, TODAY_STROKE, TODAY_LINE_COLOR);
        XYTextAnnotation todayTextAnnotation = new XYTextAnnotation(labelDateFormat.format(model.today), todayTime, 190);
        // Plot
        XYPlot plot = new XYPlot(serie, null, rangeAxis, renderer);
        plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        plot.setDomainGridlinesVisible(true);
        plot.addAnnotation(todayLineAnnotation);
        plot.addAnnotation(todayTextAnnotation);
        return plot;
    }

    private XYPlot createProjectsWorkloadPlot(GraphModel model) {
        IntervalXYDataset serie = createProjectCharterDataset(model);
        ProjectXYRenderer renderer = new ProjectXYRenderer();
        // tasks serie
        renderer.setSeriesOutlinePaint(TASK_SERIES_INDEX, STROKE_COLOR);
        renderer.setSeriesPaint(TASK_SERIES_INDEX, BACKGROUND_COLOR);
        renderer.setSeriesOutlineFillPaint(TASK_SERIES_INDEX, GAUGE_STROKE_COLOR);
        renderer.setSeriesFillPaint(TASK_SERIES_INDEX, new GradientPaint(0f, 0f, GAUGE_GRADIENT_TOP_COLOR, 1f, 1f, GAUGE_GRADIENT_BOTTOM_COLOR, true));
        // maintenance serie
        renderer.setSeriesOutlinePaint(MAINTENANCE_TASK_SERIES_INDEX, STROKE_COLOR);
        renderer.setSeriesPaint(MAINTENANCE_TASK_SERIES_INDEX, BACKGROUND_COLOR);
        renderer.setSeriesOutlineFillPaint(MAINTENANCE_TASK_SERIES_INDEX, MAINTENANCE_GAUGE_STROKE_COLOR);
        renderer.setSeriesFillPaint(MAINTENANCE_TASK_SERIES_INDEX, new GradientPaint(0f, 0f, MAINTENANCE_GAUGE_GRADIENT_TOP_COLOR, 1f, 1f, MAINTENANCE_GAUGE_GRADIENT_BOTTOM_COLOR, true));
        // Label
        Font labelFont = renderer.getBaseItemLabelFont().deriveFont(Font.BOLD, 8.0f);
        renderer.setSeriesItemLabelFont(TASK_SERIES_INDEX, labelFont, true);
        renderer.setSeriesItemLabelFont(MAINTENANCE_TASK_SERIES_INDEX, labelFont, true);
        
        // Range Axis
        NumberAxis rangeAxis = new NumberAxis("Projects Workload");
        rangeAxis.setAutoRangeIncludesZero(false);
        rangeAxis.setTickLabelsVisible(false);
        rangeAxis.setTickMarksVisible(false);
        rangeAxis.setRange(-5, 105);
        // Annotation
        long todayTime = model.today.getTime();
        XYLineAnnotation todayLineAnnotation = new XYLineAnnotation(todayTime, 105, todayTime, -5, TODAY_STROKE, TODAY_LINE_COLOR);
        //Plot
        XYPlot plot = new XYPlot(serie, null, rangeAxis, renderer);
        plot.setRangeAxisLocation(AxisLocation.TOP_OR_LEFT);
        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinesVisible(false);
        plot.addAnnotation(todayLineAnnotation);
        return plot;
    }

    private void fillWorkloadDataset(GraphModel model, XYSeriesCollection dataset) {

        int overallWorkloadForCurrentDate = 0;
        XYSeries series = new XYSeries("Workload in time");
        double meanVelocity = toolkit.getMeanVelocity();

        for (Date currentDate : model.sortedDates) {
            if (overallWorkloadForCurrentDate > 0) {

                series.add(currentDate.getTime(), overallWorkloadForCurrentDate);
                // plot(x=currentDate,  f(x)=overallWorkloadForCurrentDate)
                // this is necessary to draw horizontal lines between between 2 dates
                overallWorkloadForCurrentDate = 0;
            }
            for (MaintenanceTaskLoad currentMaintenanceTaskLoad : model.maintenanceTasks) {
                overallWorkloadForCurrentDate += currentMaintenanceTaskLoad.averageDailyWorkload * 100 / meanVelocity;
            }
            for (TaskLoad currentTaskLoad : model.activeTasks) {
                overallWorkloadForCurrentDate += currentTaskLoad.getWorkloadPercentage(currentDate);
            }
            series.add(currentDate.getTime(), overallWorkloadForCurrentDate);
        // plot(x=currentDate, f(x)=overallWorkloadForCurrentDate)
        }
        dataset.addSeries(series);
    }

    private IntervalXYDataset createProjectCharterDataset(GraphModel model) {
        ProjectSeriesCollection projectCharterCollection = new ProjectSeriesCollection();

        List<TaskLoad> maintenanceTasks = new ArrayList<TaskLoad>();
        for (MaintenanceTaskLoad maintenanceTask : model.maintenanceTasks) {
            maintenanceTasks.add(maintenanceToTaskLoad(model, maintenanceTask));
        }
        projectCharterCollection.addTaskSeries("Maintenance", maintenanceTasks);

        List<TaskLoad> tasks = new ArrayList<TaskLoad>();
        for (TaskLoad currentTask : model.activeTasks) {
            tasks.add(currentTask);
        }
        projectCharterCollection.addTaskSeries("Tasks", tasks);

        return projectCharterCollection;
    }

    private TaskLoad maintenanceToTaskLoad(GraphModel model,
            MaintenanceTaskLoad maintenanceTaskLoad) {
        TaskLoad taskLoad = new TaskLoad(toolkit);
        taskLoad.setName(maintenanceTaskLoad.name);
        int totalWorkload = model.businessDays * maintenanceTaskLoad.averageDailyWorkload;
        taskLoad.setTotalWork(totalWorkload);
        taskLoad.setWorkDone(
                toolkit.getBusinessDaysCount(model.startDate, model.today)
                *maintenanceTaskLoad.averageDailyWorkload);
        taskLoad.setBegin(model.startDate);
        taskLoad.setEnd(model.endDate);
        return taskLoad;
    }
    
    private JFreeChart theChart = null;

    private final ScrinchEnvToolkit toolkit;

    public WorkloadEvolutionPanel() {
        this(new ScrinchEnvToolkit());
    }

    public WorkloadEvolutionPanel(ScrinchEnvToolkit toolkit) {
        initComponents();
        this.toolkit = toolkit;
        this.theChart = createChart(createGraphModel());
        ChartPanel panel = new ChartPanel(this.theChart, false);
        add(panel, BorderLayout.CENTER);
    }
    
    public void draw(Graphics2D g2, Rectangle2D dimensions){
        int x = (int)dimensions.getX();
        int y = (int)dimensions.getY();
        int width = (int)dimensions.getWidth();
        int height = (int)dimensions.getHeight();
        
        Rectangle2D chartR2d = new Rectangle2D.Double(x, y, width, height);

        this.theChart.setBackgroundPaint(null);
        this.theChart.draw(g2, chartR2d);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setOpaque(false);
        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
