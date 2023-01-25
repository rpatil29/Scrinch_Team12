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

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import junit.framework.TestCase;

public class ProjectFactoryTest extends TestCase {
    
    public ProjectFactoryTest(String testName) {
        super(testName);
    }
    
    private ProjectItemSet createDefaultItemSet() throws ScrinchException {
        
        ProjectItemSet itemSet = ProjectItemSetFactory.getInstance().createProjectItemSet();
        Item item = toolkit.getItemFactory().createItem();
        item.addVisa(new Item.Visa(Item.Status.EVALUATED, null, new Date()));
        itemSet.addItem(item);
        
        return itemSet;
    }

    private ScrinchEnvToolkit toolkit;
    
    @Override
    protected void setUp() throws Exception {
        try{
            toolkit = new ScrinchEnvToolkit();
            ProjectFactory factory = toolkit.getProjectFactory();
            Project project;
            Calendar cal = toolkit.getPreparedCalendarInstance();
            
            // project in the past
            project = factory.createProject();
            project.setMaintenanceProject(false);
            cal.add(Calendar.DATE, -4);
            project.setStartDate(cal.getTime());
            cal.add(Calendar.DATE, 1);
            project.setEndDate(cal.getTime());
            project.addItemSet(createDefaultItemSet());

            // project currently opened
            project = factory.createProject();
            project.setMaintenanceProject(false);
            cal = toolkit.getPreparedCalendarInstance();
            cal.add(Calendar.DATE, -1);
            cal.setTime(toolkit.getPreviousWeekDayIfNotWeekDay(cal.getTime()));
            project.setStartDate(cal.getTime());
            cal.add(Calendar.DATE, 2);
            project.setEndDate(cal.getTime());
            project.addItemSet(createDefaultItemSet());

            // project in the future
            project = factory.createProject();
            project.setMaintenanceProject(false);
            cal.add(Calendar.DATE, 1);
            project.setStartDate(cal.getTime());
            cal.add(Calendar.DATE, 1);
            project.setEndDate(cal.getTime());
            project.addItemSet(createDefaultItemSet());

            // maintenance project (dates have no meaning)
            project = factory.createProject();
            project.setMaintenanceProject(true);
            cal.add(Calendar.DATE, -10);
            project.setStartDate(cal.getTime());
            cal.add(Calendar.DATE, 1);
            project.setEndDate(cal.getTime());
            project.addItemSet(createDefaultItemSet());
            
        }catch(Exception e){
            fail(e.getMessage());
        }
    }
    
    @Override
    public void tearDown(){
        toolkit.getProjectFactory().disposeAll();
        ProjectItemSetFactory.getInstance().disposeAll();
        toolkit.getItemFactory().disposeAll();
    }
    
    public void testFindEarliestStartingDateAmongActiveProjects() {
        System.out.println("ProjectFactory.findEarliestStartingDateAmongActiveProjects");
        Calendar cal = toolkit.getPreparedCalendarInstance();
        cal.add(Calendar.DATE, -1);
        cal.setTime(toolkit.getPreviousWeekDayIfNotWeekDay(cal.getTime()));
        Date result = toolkit.getProjectFactory().findEarliestStartingDateAmongActiveProjects();
        assertEquals(cal.getTime(), result);
    }

    public void testFindActives() {
        System.out.println("ProjectFactory.findActives");
        List<Project> result = toolkit.getProjectFactory().findActiveAndNotMaintenances();
        assertEquals(2, result.size());
    }

    public void testFindTopicals() {
        System.out.println("ProjectFactory.findTopicals");
        List<Project> result = toolkit.getProjectFactory().findTopicals();
        assertEquals(3, result.size());
    }
}
