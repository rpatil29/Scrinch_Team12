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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class Project {

    public static final Comparator<Project> BY_DATE_ASCENDING_COMPARATOR = new Comparator<Project>(){
        public int compare(Project o1, Project o2) {
            return o1.endDate.compareTo(o2.endDate);
        }
    };

    public static final Comparator<Project> BY_TITLE_ASCENDING_COMPARATOR = new Comparator<Project>(){
        public int compare(Project o1, Project o2) {
            int toReturn = 0;
            if(o1.projectName==null && o2.projectName==null){
                toReturn =0;
            } else if (o1.projectName==null){
                toReturn = -1;
            } else if (o2.projectName==null){
                toReturn = 1;
            }else {
                toReturn = o1.projectName.compareTo(o2.projectName);
            }
            return toReturn;
        }
    };

    public interface Listener{
        public void datesChanged(Project project);
    }

    private List<Listener> listeners = new Vector();
    private List<ProjectItemSet> projectItemSetList = new Vector();
    private Date startDate;
    private Date endDate;
    private String projectName;
    private String description;
    private boolean maintenanceProject;

    private final ScrinchEnvToolkit toolkit;

    public Project(String projectName, ScrinchEnvToolkit toolkit) {
        this(toolkit);
        setProjectName(projectName);
    }

    public Project(ScrinchEnvToolkit toolkit) {
        this.toolkit = toolkit;
        startDate = toolkit.getCurrentDate();
        endDate = toolkit.getCurrentDate();
    }

    public void addListener(Listener listener){
        this.listeners.add(listener);
    }

    public void removeListener(Listener listener){
        this.listeners.remove(listener);
    }

    private void notifyListeners(){
        for(Listener listener : listeners){
            listener.datesChanged(this);
        }
    }
    
    public boolean isActiveAndNotMaintenance(){
        return ( !this.isMaintenanceProject()
                && !this.getEndDate().before(toolkit.getCurrentDate())
                && this.getActiveItemEvaluationCount()!=0);
    }

    public boolean isTopical(){
        return ( this.isMaintenanceProject()
                || (this.getEndDate()!=null && !toolkit.getCurrentDate().after(this.getEndDate())));
    }

    public boolean isMaintenanceProject(){
        return maintenanceProject;
    }

    public void setMaintenanceProject(boolean maintenanceProject){
        this.maintenanceProject = maintenanceProject;
        this.toolkit.dataChanged();
    }

    public int getActiveItemEvaluationCount(){
        int evaluationCount = 0;
        for( int i=0 ; i<projectItemSetList.size() ; i++ ){
            evaluationCount += ItemToolkit.getActiveItemEvaluationCount(projectItemSetList.get(i).getItemList());
        }
        return evaluationCount;
    }

    public int getWorkDoneItemEvaluationCount(){
        int evaluationCount = 0;
        for( int i=0 ; i<projectItemSetList.size() ; i++ ){
            evaluationCount += ItemToolkit.getWorkDoneItemEvaluationCount(projectItemSetList.get(i).getItemList());
        }
        return evaluationCount;
    }
    
    public int getWorkToBeDoneItemEvaluationCount(){
        int evaluationCount = 0;
        for( int i=0 ; i<projectItemSetList.size() ; i++ ){
            evaluationCount += ItemToolkit.getWorkToBeDoneItemEvaluationCount(projectItemSetList.get(i).getItemList());
        }
        return evaluationCount;        
    }

    public Collection<Item> getAllItems(){
        Collection<Item> workDoneItems = new ArrayList();
        for(ItemSet itemSet : this.getProjectItemSetList()){
            workDoneItems.addAll(itemSet.getItemList());
        }
        return workDoneItems;
    }
    
    public Collection<Item> getWorkDoneItems(){
        Collection<Item> workDoneItems = new ArrayList();
        for(ItemSet itemSet : this.getProjectItemSetList()){
            workDoneItems.addAll(
                    ItemToolkit.findWorkDoneItems(
                        itemSet.getItemList()));
        }
        return workDoneItems;
    }
    
    public Collection<Item> getWorkToBeDoneItems(){
        Collection<Item> workToBeDoneItems = new ArrayList();
        for(ItemSet itemSet : this.getProjectItemSetList()){
            workToBeDoneItems.addAll(
                    ItemToolkit.findWorkToBeDoneItems(
                        itemSet.getItemList()));
        }
        return workToBeDoneItems;    
    }
    
    public void addItemSet(ProjectItemSet projectItemSet){
        projectItemSetList.add(projectItemSet);
        projectItemSet.setProject(this);
    }

    public void removeItemSet(ProjectItemSet projectItemSet){
        projectItemSetList.remove(projectItemSet);
        projectItemSet.setProject(null);
        this.toolkit.dataChanged();
    }

    public List<ProjectItemSet> getProjectItemSetList(){
        Collections.sort(projectItemSetList,ItemSet.BY_TITLE_ASCENDING_COMPARATOR);
        return projectItemSetList;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = toolkit.getNextWeekDayIfNotWeekDay(startDate);
        this.endDate = toolkit.getLatestDateAdjustedIfNotWeekDay(startDate, endDate);
        this.toolkit.dataChanged();
        notifyListeners();
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = toolkit.getLatestDateAdjustedIfNotWeekDay(startDate, endDate);
        this.toolkit.dataChanged();
        notifyListeners();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
        this.toolkit.dataChanged();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        this.toolkit.dataChanged();
    }

    public org.scrinch.model.castor.ProductBacklog toCastor(){
        org.scrinch.model.castor.ProductBacklog cPBackLog =
                                    new org.scrinch.model.castor.ProductBacklog();
        for (int i = 0; i < projectItemSetList.size(); i++) {
            ProjectItemSet pISet = projectItemSetList.get(i);
            cPBackLog.addItemSet(pISet.toCastor());
        }
        cPBackLog.setName(this.getProjectName());
        cPBackLog.setDescription(this.getDescription());
        cPBackLog.setStartDate(new org.exolab.castor.types.Date(this.getStartDate()));
        cPBackLog.setEndDate(new org.exolab.castor.types.Date(this.getEndDate()));
        cPBackLog.setMaintenanceProductBacklog(isMaintenanceProject());
        return cPBackLog;
    }

    public static Project fromCastor(org.scrinch.model.castor.ProductBacklog cPBackLog, ScrinchEnvToolkit toolkit)
      throws ScrinchException{
        Project pBack = new Project(cPBackLog.getName(), toolkit);
        for (int i = 0; i < cPBackLog.getItemSetCount(); i++) {
            org.scrinch.model.castor.ItemSet cItemSet = cPBackLog.getItemSet(i);
            pBack.addItemSet(ProjectItemSet.fromCastor(cItemSet, toolkit));
        }
        pBack.setProjectName(cPBackLog.getName());
        pBack.setDescription(cPBackLog.getDescription());
        pBack.setStartDate(cPBackLog.getStartDate().toDate());
        pBack.setEndDate(cPBackLog.getEndDate().toDate());
        pBack.setMaintenanceProject(cPBackLog.getMaintenanceProductBacklog());
        return pBack;
    }
     
    public int getBusinessDaysCount(){
        return toolkit.getBusinessDaysCount(
            this.getStartDate(),
            this.getEndDate());
    }
       
    @Override
    public String toString(){
        return this.getProjectName();
    }
}
