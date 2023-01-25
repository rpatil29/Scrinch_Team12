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

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class ProjectFactory {

    private List<Project> projects = new Vector<Project>();
    private final ScrinchEnvToolkit toolkit;

    protected ProjectFactory(ScrinchEnvToolkit toolkit) {
        this.toolkit = toolkit;
    }

    public Date findEarliestStartingDateAmongActiveProjects(){
        Date earliestDate = toolkit.getCurrentDate();
        for( Project project : findActiveAndNotMaintenances()){
            if (project.getStartDate()!=null && project.getStartDate().before(earliestDate)){
                earliestDate = project.getStartDate();
            }
        }
        return earliestDate;
    }

    public Project createProject(){
        Project project = new Project(toolkit);
        projects.add(project);
        this.toolkit.dataChanged();
        return project;
    }

    public List<Project> getList(){
        Collections.sort(projects,Project.BY_TITLE_ASCENDING_COMPARATOR);
        return projects;
    }

    public List<Project> findActiveAndNotMaintenances(){
        List<Project> activeList = new Vector();
        for(Project project : getList()){
            if(project.isActiveAndNotMaintenance()){
                activeList.add(project);
            }
        }
        return activeList;
    }

    public List<Project> findTopicals(){
        List<Project> topicalList = new Vector();
        for(Project project : getList()){
            if(project.isTopical()){
                topicalList.add(project);
            }
        }
        return topicalList;
    }
    
    public void dispose(Project project){
        projects.remove(project);
        this.toolkit.dataChanged();
    }

    public void disposeAll(){
        projects.clear();
        this.toolkit.dataChanged();
    }

    public org.scrinch.model.castor.Backlogs toCastor(){
        org.scrinch.model.castor.Backlogs cs = new org.scrinch.model.castor.Backlogs();
        for(int i=0; i<this.projects.size(); i++){
            Project pback = this.projects.get(i);
            org.scrinch.model.castor.ProductBacklog c = pback.toCastor();
            cs.addProductBacklog(c);
        }
        return cs;
    }

    public void loadFromCastor(org.scrinch.model.castor.Backlogs cs) throws ScrinchException{
        for(int i=0; i<cs.getProductBacklogCount(); i++){
            org.scrinch.model.castor.ProductBacklog c = cs.getProductBacklog(i);
            this.projects.add(Project.fromCastor(c, toolkit));
        }
    }
}
