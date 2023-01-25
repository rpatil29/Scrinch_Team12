/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.scrinch.jfree.data.xy;

import org.jfree.data.xy.IntervalXYDataset;

/**
 *
 * @author borerjc
 */
public interface ProjectXYDataSet extends IntervalXYDataset{

    /**
     * Returns the percent complete for a given item.
     *
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     *
     * @return The percent complete.
     */
    public Number getPercentComplete(int series, int item);

    public Number getTotalWork(int series, int item);
    
    public String getTaskName(int series, int item);
}
