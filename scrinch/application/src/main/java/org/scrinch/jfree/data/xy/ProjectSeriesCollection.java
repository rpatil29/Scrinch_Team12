package org.scrinch.jfree.data.xy;

import java.util.ArrayList;
import java.util.List;
import org.jfree.data.DomainOrder;
import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.data.xy.AbstractIntervalXYDataset;
import org.jfree.util.PublicCloneable;

public class ProjectSeriesCollection extends AbstractIntervalXYDataset 
        implements ProjectXYDataSet, PublicCloneable {

    /**
     * Storage for the series keys.  This list must be kept in sync with the
     * seriesList.
     */
    private List seriesKeys = new ArrayList();
    /** 
     * Storage for the series in the dataset.  We use a list because the
     * order of the series is significant.  This list must be kept in sync 
     * with the seriesKeys list.
     */
    private List<List<TaskLoad>> seriesList = new ArrayList<List<TaskLoad>>();

    /**
     * Returns the number of items in the specified series.
     * 
     * @param series  the series index (in the range <code>0</code> to 
     *     <code>getSeriesCount() - 1</code>).
     * 
     * @return The item count.
     * 
     * @throws IllegalArgumentException if <code>series</code> is not in the 
     *     specified range.
     * @throws IllegalArgumentException if <code>item</code> is not in the 
     *     specified range.
     */
    public TaskLoad getTask(int series, int item) {
        if ((series < 0) || (series >= getSeriesCount())) {
            throw new IllegalArgumentException("Series index out of bounds");
        }
        List<TaskLoad> seriesArray = this.seriesList.get(series);
        return seriesArray.get(item);
    }


     /**
     * Returns all series of tasks
     * 
     * @return All series of tasks.
     */
    public List<List<TaskLoad>> getTaskSeries() {
        return seriesList;
    }

    
    /**
     * Adds a task series or if a series with the same key already exists replaces
     * the data for that series, then sends a {@link DatasetChangeEvent} to 
     * all registered listeners.
     * 
     * @param seriesKey  the series key (<code>null</code> not permitted).
     * @param data  the data (must be an array with length 2, containing two 
     *     arrays of equal length, the first containing the x-values and the
     *     second containing the y-values). 
     */
    public void addTaskSeries(Comparable seriesKey, List<TaskLoad> tasks) {
        if (seriesKey == null) {
            throw new IllegalArgumentException(
                    "The 'seriesKey' cannot be null.");
        }
        if (tasks == null) {
            throw new IllegalArgumentException("The 'tasks' are null.");
        }

        int seriesIndex = indexOf(seriesKey);
        if (seriesIndex == -1) {  // add a new series
            this.seriesKeys.add(seriesKey);
            this.seriesList.add(tasks);
        } else {  // replace an existing series
            this.seriesList.remove(seriesIndex);
            this.seriesList.add(seriesIndex, tasks);
        }
        notifyListeners(new DatasetChangeEvent(this, this));
    }

    /**
     * Removes a series from the dataset, then sends a 
     * {@link DatasetChangeEvent} to all registered listeners.
     * 
     * @param seriesKey  the series key (<code>null</code> not permitted).
     * 
     */
    public void removeTaskSeries(Comparable seriesKey) {
        int seriesIndex = indexOf(seriesKey);
        if (seriesIndex >= 0) {
            this.seriesKeys.remove(seriesIndex);
            this.seriesList.remove(seriesIndex);
            notifyListeners(new DatasetChangeEvent(this, this));
        }
    }

    /**
     * Returns the number of series in the dataset.
     *
     * @return The series count.
     */
    public int getSeriesCount() {
        return this.seriesList.size();
    }

    /**
     * Returns the key for a series.  
     *
     * @param series  the series index (in the range <code>0</code> to 
     *     <code>getSeriesCount() - 1</code>).
     *
     * @return The key for the series.
     * 
     * @throws IllegalArgumentException if <code>series</code> is not in the 
     *     specified range.
     */
    public Comparable getSeriesKey(int series) {
        if ((series < 0) || (series >= getSeriesCount())) {
            throw new IllegalArgumentException("Series index out of bounds");
        }
        return (Comparable) this.seriesKeys.get(series);
    }

    /**
     * Returns the index of the series with the specified key, or -1 if there 
     * is no such series in the dataset.
     * 
     * @param seriesKey  the series key (<code>null</code> permitted).
     * 
     * @return The index, or -1.
     */
    @Override
    public int indexOf(Comparable seriesKey) {
        return this.seriesKeys.indexOf(seriesKey);
    }

    /**
     * Returns the order of the domain (x-) values in the dataset.  In this
     * implementation, we cannot guarantee that the x-values are ordered, so 
     * this method returns <code>DomainOrder.NONE</code>.
     * 
     * @return <code>DomainOrder.NONE</code>.
     */
    @Override
    public DomainOrder getDomainOrder() {
        return DomainOrder.NONE;
    }

    /**
     * Returns the number of items in the specified series.
     * 
     * @param series  the series index (in the range <code>0</code> to 
     *     <code>getSeriesCount() - 1</code>).
     * 
     * @return The item count.
     * 
     * @throws IllegalArgumentException if <code>series</code> is not in the 
     *     specified range.
     */
    public int getItemCount(int series) {
        if ((series < 0) || (series >= getSeriesCount())) {
            throw new IllegalArgumentException("Series index out of bounds");
        }
        List<TaskLoad> seriesArray = this.seriesList.get(series);
        return seriesArray.size();
    }

    /**
     * Returns the x-value for an item within a series.
     * 
     * @param series  the series index (in the range <code>0</code> to 
     *     <code>getSeriesCount() - 1</code>).
     * @param item  the item index (in the range <code>0</code> to 
     *     <code>getItemCount(series)</code>).
     *     
     * @return The x-value which is also the start x-value
     * 
     * @throws ArrayIndexOutOfBoundsException if <code>series</code> is not 
     *     within the specified range.
     * @throws ArrayIndexOutOfBoundsException if <code>item</code> is not 
     *     within the specified range.
     * 
     * @see #getStartX(int, int)
     */
    public Number getX(int series, int item) {
        return getStartX(series, item);
    }

    /**
     * Returns the y-value for an item within a series.
     * 
     * @param series  the series index (in the range <code>0</code> to 
     *     <code>getSeriesCount() - 1</code>).
     * @param item  the item index (in the range <code>0</code> to 
     *     <code>getItemCount(series)</code>).
     *     
     * @return The y-value.
     * 
     * @throws ArrayIndexOutOfBoundsException if <code>series</code> is not 
     *     within the specified range.
     * @throws ArrayIndexOutOfBoundsException if <code>item</code> is not 
     *     within the specified range.
     *     
     * @see #getStartY(int, int)
     */
    public Number getY(int series, int item) {
        return getStartY(series, item);
    }

    /**
     * Returns the start x-value for an item within a series.
     * 
     * @param series  the series index (in the range <code>0</code> to 
     *     <code>getSeriesCount() - 1</code>).
     * @param item  the item index (in the range <code>0</code> to 
     *     <code>getItemCount(series)</code>).
     *     
     * @return The start x-value.
     * 
     * @throws ArrayIndexOutOfBoundsException if <code>series</code> is not 
     *     within the specified range.
     * @throws ArrayIndexOutOfBoundsException if <code>item</code> is not 
     *     within the specified range.
     * 
     * @see #getXValue(int, int)
     */
    public Number getStartX(int series, int item) {
        return this.seriesList.get(series).get(item).getBegin().getTime();
    }

    /**
     * Returns the end x-value for an item within a series.
     * 
     * @param series  the series index (in the range <code>0</code> to 
     *     <code>getSeriesCount() - 1</code>).
     * @param item  the item index (in the range <code>0</code> to 
     *     <code>getItemCount(series)</code>).
     *     
     * @return The end x-value.
     * 
     * @throws ArrayIndexOutOfBoundsException if <code>series</code> is not 
     *     within the specified range.
     * @throws ArrayIndexOutOfBoundsException if <code>item</code> is not 
     *     within the specified range.
     * 
     */
    public Number getEndX(int series, int item) {
        return this.seriesList.get(series).get(item).getEnd().getTime();
    }

    /**
     * Returns the start y-value for an item within a series. This value is not 
     * used by the renderer is therefore 0.
     * 
     * @param series  the series index (in the range <code>0</code> to 
     *     <code>getSeriesCount() - 1</code>).
     * @param item  the item index (in the range <code>0</code> to 
     *     <code>getItemCount(series)</code>).
     *     
     * @return The y-value.
     * 
     * @throws ArrayIndexOutOfBoundsException if <code>series</code> is not 
     *     within the specified range.
     * @throws ArrayIndexOutOfBoundsException if <code>item</code> is not 
     *     within the specified range.
     *     
     */
    public Number getStartY(int series, int item) {
        return 0;
    }

    /**
     * Returns the end y-value for an item within a series. This value is not 
     * used by the renderer is therefore 0.
     * 
     * @param series  the series index (in the range <code>0</code> to 
     *     <code>getSeriesCount() - 1</code>).
     * @param item  the item index (in the range <code>0</code> to 
     *     <code>getItemCount(series)</code>).
     *     
     * @return The end y-value.
     * 
     * @throws ArrayIndexOutOfBoundsException if <code>series</code> is not 
     *     within the specified range.
     * @throws ArrayIndexOutOfBoundsException if <code>item</code> is not 
     *     within the specified range.
     *     
     */
    public Number getEndY(int series, int item) {
        return 10;
    }

    /**
     * Returns the percentage of task completion (0.0 to 1.0)
     * 
     * @param series  the series index (in the range <code>0</code> to 
     *     <code>getSeriesCount() - 1</code>).
     * @param item  the item index (in the range <code>0</code> to 
     *     <code>getItemCount(series)</code>).
     *     
     * @return The completion percentage.
     * 
     * @throws ArrayIndexOutOfBoundsException if <code>series</code> is not 
     *     within the specified range.
     * @throws ArrayIndexOutOfBoundsException if <code>item</code> is not 
     *     within the specified range.
     *     
     */
    public Number getPercentComplete(int series, int item) {
        return this.seriesList.get(series).get(item).getPercentDone();
    }

    /**
     * Returns the total work for a task.
     * 
     * @param series  the series index (in the range <code>0</code> to 
     *     <code>getSeriesCount() - 1</code>).
     * @param item  the item index (in the range <code>0</code> to 
     *     <code>getItemCount(series)</code>).
     *     
     * @return The total work.
     * 
     * @throws ArrayIndexOutOfBoundsException if <code>series</code> is not 
     *     within the specified range.
     * @throws ArrayIndexOutOfBoundsException if <code>item</code> is not 
     *     within the specified range.
     *     
     */
    public Number getTotalWork(int series, int item) {
        return this.seriesList.get(series).get(item).getTotalWork();
    }

    /**
     * Returns the task name.
     * 
     * @param series  the series index (in the range <code>0</code> to 
     *     <code>getSeriesCount() - 1</code>).
     * @param item  the item index (in the range <code>0</code> to 
     *     <code>getItemCount(series)</code>).
     *     
     * @return The task name.
     * 
     * @throws ArrayIndexOutOfBoundsException if <code>series</code> is not 
     *     within the specified range.
     * @throws ArrayIndexOutOfBoundsException if <code>item</code> is not 
     *     within the specified range.
     *     
     */
    public String getTaskName(int series, int item) {
        return this.seriesList.get(series).get(item).getName();
    }

    /**
     * Tests this <code>DefaultXYDataset</code> instance for equality with an
     * arbitrary object.  This method returns <code>true</code> if and only if:
     * <ul>
     * <li><code>obj</code> is not <code>null</code>;</li>
     * <li><code>obj</code> is an instance of 
     *         <code>DefaultXYDataset</code>;</li>
     * <li>both datasets have the same number of series, each containing 
     *         exactly the same values.</li>
     * </ul>
     * 
     * @param obj  the object (<code>null</code> permitted).
     * 
     * @return A boolean.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ProjectSeriesCollection)) {
            return false;
        }
        ProjectSeriesCollection that = (ProjectSeriesCollection) obj;
        if (!this.seriesKeys.equals(that.seriesKeys)) {
            return false;
        }
        for (int i = 0; i < this.seriesList.size(); i++) {
            List<TaskLoad> l1 = this.seriesList.get(i);
            List<TaskLoad> l2 = that.seriesList.get(i);
            if (!l1.equals(l2)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns a hash code for this instance.
     * 
     * @return A hash code.
     */
    @Override
    public int hashCode() {
        int result;
        result = this.seriesKeys.hashCode();
        result = 29 * result + this.seriesList.hashCode();
        return result;
    }

    /**
     * Creates an independent copy of this dataset.
     * 
     * @return The cloned dataset.
     * 
     * @throws CloneNotSupportedException if there is a problem cloning the
     *     dataset (for instance, if a non-cloneable object is used for a
     *     series key).
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        ProjectSeriesCollection clone = (ProjectSeriesCollection) super.clone();
        clone.seriesKeys = new ArrayList(this.seriesKeys);
        clone.seriesList = new ArrayList(this.seriesList);
        return clone;
    }
}
