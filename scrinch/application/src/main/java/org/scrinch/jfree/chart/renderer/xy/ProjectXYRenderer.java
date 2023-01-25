package org.scrinch.jfree.chart.renderer.xy;

import org.scrinch.jfree.data.xy.TaskLoad;
import org.scrinch.gui.reporting.*;
import org.scrinch.jfree.data.xy.ProjectSeriesCollection;
import org.scrinch.gui.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.List;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.plot.DrawingSupplier;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYDotRenderer;
import org.jfree.chart.renderer.xy.XYItemRendererState;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.GradientPaintTransformer;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.StandardGradientPaintTransformer;
import org.jfree.util.PaintList;

public class ProjectXYRenderer extends XYDotRenderer {

    public static final Paint DEFAULT_OUTLINEFILL_PAINT = Color.GRAY;
    private final static int CORNER_RADIUS = 7;
    private final static int VERTICAL_GAP = 2;
    private int totalWorkload = 0;
    private static final int FIRST_TASK_Y = 100;
    private int nextY;
    private int transY = -1;
    /** The outline paint list. */
    private PaintList outlineFillPaintList = new PaintList();
    /** The base outline paint. */
    private transient Paint baseOutlineFillPaint = DEFAULT_OUTLINEFILL_PAINT;
    /**
     * A flag that controls whether or not the outlinePaintList is 
     * auto-populated in the {@link #lookupSeriesOutlinePaint(int)} method.
     * 
     * @since 1.0.6
     */
    private boolean autoPopulateSeriesOutlinePaint = false;
    /** 
     * An optional class used to transform gradient paint objects to fit each 
     * bar. 
     */
    private GradientPaintTransformer gradientPaintTransformer = new StandardGradientPaintTransformer();

    public ProjectXYRenderer() {
        this.setDotHeight(10);
        this.setDotWidth(10);
    }

    @Override
    public XYItemRendererState initialise(Graphics2D g2, Rectangle2D dataArea, XYPlot plot, XYDataset dataset, PlotRenderingInfo info) {
        ProjectSeriesCollection projectDataset = (ProjectSeriesCollection) dataset;
        totalWorkload = 0;
        nextY = FIRST_TASK_Y;
        for (List<TaskLoad> serie : projectDataset.getTaskSeries()) {
            for (TaskLoad taskLoad : serie) {
                totalWorkload += taskLoad.getWorkloadPercentageWhenActive();
            }
        }

        return super.initialise(g2, dataArea, plot, dataset, info);
    }

    @Override
    public void drawItem(Graphics2D g2, XYItemRendererState state, Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot, ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset, int series, int item, CrosshairState crosshairState, int pass) {

        // get the data point...
        ProjectSeriesCollection projectDataset = (ProjectSeriesCollection) dataset;
        double x = projectDataset.getXValue(series, item);
        double endX = projectDataset.getEndXValue(series, item);

        TaskLoad task = projectDataset.getTask(series, item);
        int taskWeight = (int) (((double) task.getWorkloadPercentageWhenActive() / (double) this.totalWorkload) * 100.0);

        Paint gaugeStrokePaint = this.getItemOutlineFillPaint(series, item);
        if (task.getTotalWork() > 0) {
            if (!Double.isNaN(this.nextY)) {
                Graphics2D gc = (Graphics2D) g2;
                gc.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
                RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
                int transX = (int) domainAxis.valueToJava2D(x, dataArea, xAxisLocation);
                int transEndX = (int) domainAxis.valueToJava2D(endX, dataArea, xAxisLocation);

                if (item == 0) {
                    transY = (int) rangeAxis.valueToJava2D(this.nextY, dataArea, yAxisLocation);
                }
                int transEndY = (int) rangeAxis.valueToJava2D(this.nextY - taskWeight, dataArea, yAxisLocation);


                int transLength = transEndX - transX;
                int transHeight = (int) (transEndY - transY) - VERTICAL_GAP;
                int transPercentDrawn = (int) ((double) transLength * task.getPercentDone().doubleValue());

//                System.out.println("----------------");
//                System.out.println("Series: " + series + " item: " + item + " pass:" + pass);
//                System.out.println("task name : " + task.getName());
//                System.out.println("task total work: " + task.getTotalWork());
//                System.out.println("task business days: " + task.getBusinessDays());
//                System.out.println("task weight: " + task.getBusinessDays());
//                System.out.println("task percent done: " + task.getPercentDone());
//                System.out.println("");
//                System.out.println("dataArea.getX()" + dataArea.getX());
//                System.out.println("dataArea.getY()" + dataArea.getY());
//                System.out.println("dataArea.getHeight()" + dataArea.getHeight());
//                System.out.println("");
//                System.out.println("transX : " + transX);
//                System.out.println("transEndX : " + transEndX);
//                System.out.println("transY : " + transY);
//                System.out.println("transEndY : " + transEndY);
//                System.out.println("transHeight : " + transHeight);

                Shape rounded = new RoundRectangle2D.Double(transX, transY, transLength, transHeight, CORNER_RADIUS, CORNER_RADIUS);
                gc.setStroke(this.getItemStroke(series, item));

                // Draw background
                gc.setPaint(this.getItemPaint(series, item));
                gc.setClip(dataArea);
                gc.clip(rounded);
                gc.fillRect((int) transX, (int) transY, (int) transLength, (int) transHeight);
                gc.setClip(dataArea);
                gc.setPaint(this.getItemOutlinePaint(series, item));
                gc.draw(rounded);

                // Draw gauge
                if (transPercentDrawn > 0) {
                    gc.setClip(dataArea);
                    gc.clip(rounded);
                    gc.clipRect((int) transX, (int) transY, transPercentDrawn, transHeight);

                    Paint workDoneGradient = this.getSeriesFillPaint(series);
                    Shape gradientBar = new Rectangle2D.Double(transX, transY, transPercentDrawn, transHeight);
                    if (getGradientPaintTransformer() != null && workDoneGradient instanceof GradientPaint) {
                        GradientPaint gp = (GradientPaint) workDoneGradient;
                        workDoneGradient = getGradientPaintTransformer().transform(gp, rounded);
                    }

                    gc.setPaint(workDoneGradient);
                    gc.fill(gradientBar);

                    gc.setClip(dataArea);
                    gc.clipRect(transX - 5, transY - 5, transPercentDrawn + 5, transHeight + 10);

                    gc.setPaint(gaugeStrokePaint);
                    gc.draw(rounded);
                    if (task.getPercentDone().doubleValue() < 1.0) {
                        // need to clip to draw inside the rounded corner when tasks completion nears 99%
                        gc.setClip(dataArea);
                        gc.clip(rounded);
                        gc.drawLine(transX + transPercentDrawn, transY, transX + transPercentDrawn, transY + transHeight - 1);
                        gc.setClip(dataArea);
                    }
                }

                gc.setPaint(gaugeStrokePaint);
                gc.setClip(dataArea);
                Font labelFont = this.getItemLabelFont(series, item);
                Paint labelColor = this.getItemLabelPaint(series, item);
                gc.setFont(labelFont);
                FontMetrics fontMetrics = gc.getFontMetrics();
                Rectangle2D fontBounds = fontMetrics.getStringBounds(task.getName(), gc);

                int rightBound = Math.min(transX + transLength, (int)dataArea.getMaxX());
                int fx = rightBound - (int) fontBounds.getWidth() - CORNER_RADIUS;
                fx = Math.max((int) dataArea.getX(), fx);
                // a bit weird but this is the only way to center the strings
                int fy = transY + (transHeight + (int) fontBounds.getHeight()) / 2 - fontMetrics.getDescent();
                gc.setPaint(labelColor);
                gc.drawString(task.getName(), fx, fy);

                // rember values for next project position
                nextY -= taskWeight;
                transY += transHeight + VERTICAL_GAP;

            }
        }
    }

    /**
     * Returns the gradient paint transformer (an object used to transform 
     * gradient paint objects to fit each bar).
     * 
     * @return A transformer (<code>null</code> possible).
     * 
     * @see #setGradientPaintTransformer(GradientPaintTransformer)
     */
    public GradientPaintTransformer getGradientPaintTransformer() {
        return this.gradientPaintTransformer;
    }

    /**
     * Sets the gradient paint transformer and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param transformer  the transformer (<code>null</code> permitted).
     * 
     * @see #getGradientPaintTransformer()
     */
    public void setGradientPaintTransformer(
            GradientPaintTransformer transformer) {
        this.gradientPaintTransformer = transformer;
    // this.fireChangeEvent();
    }

    /**
     * Sets the paint used for a series outline and sends a 
     * {@link RendererChangeEvent} to all registered listeners.
     *
     * @param series  the series index (zero-based).
     * @param paint  the paint (<code>null</code> permitted).
     * 
     * @see #getSeriesOutlinePaint(int)
     */
    public void setSeriesOutlineFillPaint(int series, Paint paint) {
        setSeriesOutlineFillPaint(series, paint, true);
    }

    /**
     * Sets the paint used to draw the outline for a series and, if requested, 
     * sends a {@link RendererChangeEvent} to all registered listeners.
     * 
     * @param series  the series index (zero-based).
     * @param paint  the paint (<code>null</code> permitted).
     * @param notify  notify listeners?
     * 
     * @see #getSeriesOutlinePaint(int)
     */
    public void setSeriesOutlineFillPaint(int series, Paint paint, boolean notify) {
        this.outlineFillPaintList.setPaint(series, paint);
        if (notify) {
        // fireChangeEvent();
        }
    }

    /**
     * Returns the paint used to outline an item drawn by the renderer.
     *
     * @param series  the series (zero-based index).
     *
     * @return The paint (possibly <code>null</code>).
     * 
     * @see #setSeriesOutlinePaint(int, Paint)
     */
    public Paint getSeriesOutlineFillPaint(int series) {
        return this.outlineFillPaintList.getPaint(series);
    }

    /**
     * Returns the paint used to outline data items as they are drawn.
     * <p>
     * The default implementation passes control to the 
     * {@link #lookupSeriesOutlinePaint} method.  You can override this method 
     * if you require different behaviour.
     *
     * @param row  the row (or series) index (zero-based).
     * @param column  the column (or category) index (zero-based).
     *
     * @return The paint (never <code>null</code>).
     */
    public Paint getItemOutlineFillPaint(int row, int column) {
        return lookupSeriesOutlineFillPaint(row);
    }

    /**
     * Returns the paint used to outline an item drawn by the renderer.
     *
     * @param series  the series (zero-based index).
     *
     * @return The paint (never <code>null</code>).
     * 
     * @since 1.0.6
     */
    public Paint lookupSeriesOutlineFillPaint(int series) {

        // otherwise look up the paint table
        Paint seriesOutlineFillPaint = getSeriesOutlineFillPaint(series);
        if (seriesOutlineFillPaint == null && this.autoPopulateSeriesOutlinePaint) {
            DrawingSupplier supplier = getDrawingSupplier();
            if (supplier != null) {
                seriesOutlineFillPaint = supplier.getNextOutlinePaint();
                setSeriesOutlineFillPaint(series, seriesOutlineFillPaint, false);
            }
        }
        if (seriesOutlineFillPaint == null) {
            seriesOutlineFillPaint = this.baseOutlineFillPaint;
        }
        return seriesOutlineFillPaint;
    }
}
