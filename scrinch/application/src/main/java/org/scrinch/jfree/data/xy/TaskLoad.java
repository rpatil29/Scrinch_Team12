package org.scrinch.jfree.data.xy;

import java.util.Date;
import org.scrinch.model.ScrinchEnvToolkit;

public class TaskLoad {

    private final ScrinchEnvToolkit toolkit;

    protected String name;
    protected Date begin;
    protected Date end;
    protected int totalWork;
    protected int workDone;

    public TaskLoad(ScrinchEnvToolkit toolkit){
        this.toolkit = toolkit;
    }

    public int getWorkloadPercentage(Date date) {
        int requiredVelocity = 0;
        if (!begin.after(date) && end.after(date) ) {
            requiredVelocity =
                    (int) Math.round(
                    (totalWork * 100 / getBusinessDays()) / toolkit.getMeanVelocity());
        }
        return requiredVelocity;
    }

    public int getWorkloadPercentageWhenActive() {
        return (int) Math.round(
               (totalWork * 100 / getBusinessDays()) / toolkit.getMeanVelocity());
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getBegin() {
        return begin;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Date getEnd() {
        return end;
    }

    public void setTotalWork(int totalWork) {
        this.totalWork = totalWork;
    }

    public int getTotalWork() {
        return totalWork;
    }

    public void setWorkDone(int workDone) {
        this.workDone = workDone;
    }

    public int getWorkDone() {
        return workDone;
    }
    
    public Number getPercentDone() {
        return (double) this.workDone / (double)this.totalWork;
    }
    
    public int getBusinessDays() {
        return toolkit.getBusinessDaysCount(this.begin, this.end);
    }
    
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Task: '").append(this.name);
        sb.append("' , from: '").append(this.begin);
        sb.append("' to: '").append(this.end);
        sb.append("' Done: ").append(this.workDone);
        sb.append(" TotalWork: ").append(this.totalWork);
        return sb.toString();
    }
}
