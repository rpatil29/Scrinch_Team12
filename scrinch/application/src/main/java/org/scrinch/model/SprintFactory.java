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

 package org.scrinch.model;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;

public class SprintFactory {

    private List<Sprint> sprints = new Vector<Sprint>();
    private final static int DEFAULT_SPRINT_DURATION_IN_WEEKS = 1;
    private int durationInWeeks = DEFAULT_SPRINT_DURATION_IN_WEEKS;

    private final ScrinchEnvToolkit toolkit;

    protected SprintFactory(ScrinchEnvToolkit toolkit) {
        this.toolkit = toolkit;
    }

    public Sprint createSprint(){
        Sprint sprint = new Sprint(toolkit);

        sprint.setStartDate(computeStartDate(sprint));
        sprint.setEndDate(computeEndDate(sprint));
        
        sprints.add(sprint);
        Collections.sort(sprints);
        sprint.setBaseVelocity(toolkit.getMeanVelocity(true));
        
        this.toolkit.dataChanged();
        return sprint;
    }
    
    private Date computeStartDate(Sprint sprint){
        Sprint latestSprint = findLatestSprint();

        Calendar cal = toolkit.getPreparedCalendarInstance();
        if(latestSprint != null){
            cal.setTime(latestSprint.getEndDate());
            cal.add(Calendar.DAY_OF_MONTH,1);
            cal.setTime(toolkit.getNextWeekDayIfNotWeekDay(cal.getTime()));
        } else {
            cal.setTime(sprint.getStartDate());
        }
        return cal.getTime();
    }
    
    private Date computeEndDate(Sprint sprint){
        Calendar cal = toolkit.getPreparedCalendarInstance();
        cal.setTime(sprint.getStartDate());
        cal.add(Calendar.WEEK_OF_YEAR,getCurrentSprintWeekCount());
        cal.add(Calendar.DAY_OF_MONTH,-1);
        cal.setTime(toolkit.getPreviousWeekDayIfNotWeekDay(cal.getTime()));
        
        return cal.getTime();
    }

    public int getCurrentSprintWeekCount(){
        return durationInWeeks;
    }

    public void setCurrentSprintWeekCount(int weekCount){
        this.durationInWeeks = weekCount;
        this.toolkit.dataChanged();
    }

    public Sprint findTodaySprint(){
        Sprint todaySprint = null;
        for(Sprint sprint : sprints){
            if( sprint.isRunning() ){
                todaySprint = sprint;
                break;
            }
        }
        return todaySprint;
    }

    public List<Sprint> getSprintList(){
        return sprints;
    }

    public Double getMeanVelocity(Date start, Date end){
        return getMeanVelocity(false,start,end);
    }
    
    public Double getMeanVelocity(boolean correctedAccordingToSlowDowns, Date start, Date end){
        Double meanVelocity = null;
        if(sprints.size()>0){
            double velocitySum = 0;
            int sprintSelectedCount = 0;
            for(Sprint sprint : findNonTopicalSprints()){
                try{
                    if(start==null || end==null 
                        ||(sprint.getEndDate()!=null
                            && !sprint.getEndDate().before(start) 
                            && !sprint.getEndDate().after(end))){
                        velocitySum += sprint.getResultingVelocity(correctedAccordingToSlowDowns);
                        sprintSelectedCount++;
                    }
                } catch(Exception e){
                    ScrinchEnvToolkit.logger.log(Level.WARNING, "Unable to compute Mean Velocity", e);
                }
            }
            if(sprintSelectedCount!=0){
                meanVelocity = new Double((double)velocitySum/sprintSelectedCount);
            }
        }
        return meanVelocity;
    }

    public List<Sprint> findTopicalSprints(){
        List<Sprint> topicalSprints = new Vector<Sprint>();
        for(Sprint sprint : getSprintList()){
            if(sprint.isTopical()){
                topicalSprints.add(sprint);
            }
        }
        return topicalSprints;
    }

    public Sprint findLatestSprint(){
        Sprint latestSprint = null;
        for(Sprint sprint : sprints){
            if(latestSprint==null || sprint.getEndDate().after(latestSprint.getEndDate())){
                latestSprint = sprint;
            }
        }
        return latestSprint;
    }

    public List<Sprint> findNonTopicalSprints(){
        List<Sprint> nonTopicalSprints = new Vector<Sprint>();
        for(Sprint sprint : getSprintList()){
            if(!sprint.isTopical()){
                nonTopicalSprints.add(sprint);
            }
        }
        return nonTopicalSprints;
    }

    public void dispose(Sprint sprint){
        sprints.remove(sprint);
        this.toolkit.dataChanged();
    }

    public void disposeAll(){
        sprints.clear();
        this.toolkit.dataChanged();
    }

    public org.scrinch.model.castor.Sprints toCastor() throws UnsupportedEncodingException{
        org.scrinch.model.castor.Sprints cSprints = new org.scrinch.model.castor.Sprints();
        cSprints.setDurationInWeeks(getCurrentSprintWeekCount());
        for(int i=0; i<this.sprints.size(); i++){
            Sprint sprint = this.sprints.get(i);
            org.scrinch.model.castor.Sprint cSprint = sprint.toCastor();
            cSprints.addSprint(cSprint);
        }
        return cSprints;
    }

    public void loadFromCastor(org.scrinch.model.castor.Sprints cSprints)
        throws ScrinchException, UnsupportedEncodingException{
        setCurrentSprintWeekCount(cSprints.getDurationInWeeks()>0?cSprints.getDurationInWeeks():DEFAULT_SPRINT_DURATION_IN_WEEKS);

        for(int i=0; i<cSprints.getSprintCount(); i++){
            org.scrinch.model.castor.Sprint cSprint = cSprints.getSprint(i);
            this.sprints.add(Sprint.fromCastor(cSprint, toolkit));
        }
        Collections.sort(this.sprints);
    }
}
