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
import junit.framework.TestCase;

public class ProjectTest extends TestCase {
    
    public ProjectTest(String testName) {
        super(testName);
    }
    
    private ScrinchEnvToolkit toolkit;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        toolkit = new ScrinchEnvToolkit();
    }

    public void testSettingDates(){
        System.out.println("Sprint.setStartDate");
        System.out.println("Sprint.setEndDate");
        Project project = new Project(toolkit);
        assertEquals(project.getStartDate(), project.getEndDate());
        
        Calendar cal = toolkit.getPreparedCalendarInstance();
        cal.add(Calendar.DATE, 10);
        project.setStartDate(cal.getTime());
        assertEquals(project.getStartDate(), project.getEndDate());
        
        cal.add(Calendar.DATE, 1);
        project.setEndDate(cal.getTime());
        assertTrue(!project.getEndDate().before(project.getStartDate()));
        
        cal.add(Calendar.DATE, -10);
        project.setEndDate(cal.getTime());
        assertEquals(project.getStartDate(), project.getEndDate());
    }    
    
    public void testIsActiveAndNotMaintenance() {
        System.out.println("Project.isActiveAndNotMaintenance");
        try{
            Project project = new Project(toolkit);
            ProjectItemSet itemSet = new ProjectItemSet();
            Item item = new Item(toolkit);
            item.addVisa(new Item.Visa(Item.Status.EVALUATED, new Member("member1"), new Date()));
            itemSet.affectItem(item);
            project.addItemSet(itemSet);

            assertEquals(true, project.isActiveAndNotMaintenance());

            Calendar cal = toolkit.getPreparedCalendarInstance();

            cal.add(Calendar.DATE, -10);
            project.setStartDate(cal.getTime());
            project.setEndDate(cal.getTime());
            assertEquals(false, project.isActiveAndNotMaintenance());

            cal.add(Calendar.DATE, 30);
            project.setStartDate(cal.getTime());
            assertEquals(true, project.isActiveAndNotMaintenance());

            project.setMaintenanceProject(true);
            assertEquals(false, project.isActiveAndNotMaintenance());
        }catch(Exception e){
            fail(e.getMessage());
        }
    }

    public void testIsTopical() {
        System.out.println("Project.isTopical");
            Project project = new Project(toolkit);
            assertEquals(true, project.isTopical());

            Calendar cal = toolkit.getPreparedCalendarInstance();

            cal.add(Calendar.DATE, -10);
            project.setStartDate(cal.getTime());
            project.setEndDate(cal.getTime());
            assertEquals(false, project.isTopical());

            project.setMaintenanceProject(true);
            assertEquals(true, project.isTopical());
    }

    public void testGetActiveItemEvaluationCount() {
        System.out.println("Project.getActiveItemEvaluationCount");
        try{
            Project project = new Project(toolkit);
            
            ProjectItemSet itemSet = new ProjectItemSet();
            project.addItemSet(itemSet);
            Item item = new Item(toolkit);
            item.setEvaluation(FiboPoint.EIGHT);
            item.addVisa(new Item.Visa(Item.Status.EVALUATED, new Member("member1"), new Date()));
            itemSet.affectItem(item);
            assertEquals(8, project.getActiveItemEvaluationCount());

            itemSet = new ProjectItemSet();
            project.addItemSet(itemSet);
            item = new Item(toolkit);
            item.setEvaluation(FiboPoint.TWO);
            item.addVisa(new Item.Visa(Item.Status.EVALUATED, new Member("member2"), new Date()));
            itemSet.affectItem(item);
            assertEquals(10, project.getActiveItemEvaluationCount());

            item = new Item(toolkit);
            item.setEvaluation(FiboPoint.THIRTEEN);
            item.addVisa(new Item.Visa(Item.Status.EVALUATED, new Member("member3"), new Date()));
            itemSet.affectItem(item);
            assertEquals(23, project.getActiveItemEvaluationCount());
            
            item = new Item(toolkit);
            item.setEvaluation(FiboPoint.ONE);
            itemSet.affectItem(item);
            assertEquals(23, project.getActiveItemEvaluationCount());
            
        }catch(Exception e){
            fail(e.getMessage());
        }
    }

    public void testWorkDoneItems() {
        System.out.println("Project.getWorkDoneItemEvaluationCount");
        System.out.println("Project.getWorkDoneItems");
        System.out.println("Project.getWorkToBeDoneItemEvaluationCount");
        System.out.println("Project.getWorkToBeDoneItems");
        try{
            Project project = new Project(toolkit);
            
            ProjectItemSet itemSet = new ProjectItemSet();
            project.addItemSet(itemSet);
            Item firstItem = new Item(toolkit);
            firstItem.setEvaluation(FiboPoint.EIGHT);
            firstItem.addVisa(new Item.Visa(Item.Status.EVALUATED, new Member("member1"), new Date()));
            itemSet.affectItem(firstItem);
            assertEquals(0, project.getWorkDoneItemEvaluationCount());
            assertEquals(0, project.getWorkDoneItems().size());
            assertEquals(8, project.getWorkToBeDoneItemEvaluationCount());
            assertEquals(1, project.getWorkToBeDoneItems().size());

            itemSet = new ProjectItemSet();
            project.addItemSet(itemSet);
            Item secondItem = new Item(toolkit);
            secondItem.setEvaluation(FiboPoint.TWO);
            secondItem.addVisa(new Item.Visa(Item.Status.EVALUATED, new Member("member2"), new Date()));
            itemSet.affectItem(secondItem);
            assertEquals(0, project.getWorkDoneItemEvaluationCount());
            assertEquals(0, project.getWorkDoneItems().size());
            assertEquals(10, project.getWorkToBeDoneItemEvaluationCount());
            assertEquals(2, project.getWorkToBeDoneItems().size());

            firstItem.addVisa(new Item.Visa(Item.Status.DONE, new Member("member3"), new Date()));
            assertEquals(8, project.getWorkDoneItemEvaluationCount());
            assertEquals(1, project.getWorkDoneItems().size());
            assertEquals(2, project.getWorkToBeDoneItemEvaluationCount());
            assertEquals(1, project.getWorkToBeDoneItems().size());
            
            secondItem.addVisa(new Item.Visa(Item.Status.DONE, new Member("member4"), new Date()));
            assertEquals(10, project.getWorkDoneItemEvaluationCount());
            assertEquals(2, project.getWorkDoneItems().size());
            assertEquals(0, project.getWorkToBeDoneItemEvaluationCount());
            assertEquals(0, project.getWorkToBeDoneItems().size());
            
        }catch(Exception e){
            fail(e.getMessage());
        }
    }
   
    public void testCompareTo() {
        System.out.println("Project.compareTo");
        Project oldest = new Project(toolkit);
        Project latest = new Project(toolkit);
        assertEquals(0, Project.BY_DATE_ASCENDING_COMPARATOR.compare(oldest,latest));
        
        Calendar cal = toolkit.getPreparedCalendarInstance();
        cal.add(Calendar.DATE, 1);
        latest.setStartDate(cal.getTime());
        assertTrue(Project.BY_DATE_ASCENDING_COMPARATOR.compare(oldest,latest)<0);
    }
}
