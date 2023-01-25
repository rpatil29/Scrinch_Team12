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
import junit.framework.*;
import java.util.Date;
import java.util.Stack;

public class ItemTest extends TestCase {
    
    private static final Item.Visa STANDBY_VISA = new Item.Visa(Item.Status.STANDBY, null, null);
    private static final Item.Visa EVALUATED_VISA = new Item.Visa(Item.Status.EVALUATED, null, null);
    private static final Item.Visa DONE_VISA = new Item.Visa(Item.Status.DONE, null, null);
    private static final Item.Visa NOT_OK_VISA = new Item.Visa(Item.Status.NOT_OK, null, null);
    private static final Item.Visa OK_VISA = new Item.Visa(Item.Status.OK, null, null);
    private static final Item.Visa CANCELLED_VISA = new Item.Visa(Item.Status.CANCELLED, null, null);
    private static final Item.Visa POSTPONED_VISA = new Item.Visa(Item.Status.POSTPONED, null, null);

    private ScrinchEnvToolkit toolkit;
    
    public ItemTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        toolkit = new ScrinchEnvToolkit();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2011);
        calendar.set(Calendar.MONTH, 6);
        calendar.set(Calendar.DAY_OF_MONTH, 26);
        toolkit.setPreparedCalendarInstance(calendar);
    }

    public void testIsStatusValid() {
        System.out.println("Item.isStatusValid");
        Stack<Item.Visa> visas = new Stack();
        
        assertTrue(Item.isStatusValid(Item.Status.STANDBY,visas));
        assertFalse(Item.isStatusValid(Item.Status.EVALUATED,visas));
        assertFalse(Item.isStatusValid(Item.Status.DONE,visas));
        assertFalse(Item.isStatusValid(Item.Status.OK,visas));
        assertFalse(Item.isStatusValid(Item.Status.NOT_OK,visas));
        assertFalse(Item.isStatusValid(Item.Status.CANCELLED,visas));
        assertFalse(Item.isStatusValid(Item.Status.POSTPONED,visas));
        
        visas.add(STANDBY_VISA);
        assertTrue(Item.isStatusValid(Item.Status.EVALUATED,visas));
        assertFalse(Item.isStatusValid(Item.Status.DONE,visas));
        assertFalse(Item.isStatusValid(Item.Status.OK,visas));
        assertFalse(Item.isStatusValid(Item.Status.NOT_OK,visas));
        assertTrue(Item.isStatusValid(Item.Status.CANCELLED,visas));
        assertTrue(Item.isStatusValid(Item.Status.POSTPONED,visas));
        
        visas.add(EVALUATED_VISA);
        assertFalse(Item.isStatusValid(Item.Status.EVALUATED,visas));
        assertTrue(Item.isStatusValid(Item.Status.DONE,visas));
        assertFalse(Item.isStatusValid(Item.Status.OK,visas));
        assertFalse(Item.isStatusValid(Item.Status.NOT_OK,visas));
        assertTrue(Item.isStatusValid(Item.Status.CANCELLED,visas));
        assertTrue(Item.isStatusValid(Item.Status.POSTPONED,visas));
             
        visas.add(DONE_VISA);
        assertFalse(Item.isStatusValid(Item.Status.EVALUATED,visas));
        assertFalse(Item.isStatusValid(Item.Status.DONE,visas));
        assertTrue(Item.isStatusValid(Item.Status.OK,visas));
        assertTrue(Item.isStatusValid(Item.Status.NOT_OK,visas));
        assertTrue(Item.isStatusValid(Item.Status.CANCELLED,visas));
        assertTrue(Item.isStatusValid(Item.Status.POSTPONED,visas));

        visas.add(NOT_OK_VISA);
        assertFalse(Item.isStatusValid(Item.Status.EVALUATED,visas));
        assertTrue(Item.isStatusValid(Item.Status.DONE,visas));
        assertFalse(Item.isStatusValid(Item.Status.OK,visas));
        assertFalse(Item.isStatusValid(Item.Status.NOT_OK,visas));
        assertTrue(Item.isStatusValid(Item.Status.CANCELLED,visas));
        assertTrue(Item.isStatusValid(Item.Status.POSTPONED,visas));

        visas.add(OK_VISA);
        assertFalse(Item.isStatusValid(Item.Status.EVALUATED,visas));
        assertFalse(Item.isStatusValid(Item.Status.DONE,visas));
        assertFalse(Item.isStatusValid(Item.Status.OK,visas));
        assertFalse(Item.isStatusValid(Item.Status.NOT_OK,visas));
        assertFalse(Item.isStatusValid(Item.Status.CANCELLED,visas));
        assertFalse(Item.isStatusValid(Item.Status.POSTPONED,visas));
        visas.pop();

        visas.add(CANCELLED_VISA);
        assertFalse(Item.isStatusValid(Item.Status.EVALUATED,visas));
        assertFalse(Item.isStatusValid(Item.Status.DONE,visas));
        assertFalse(Item.isStatusValid(Item.Status.OK,visas));
        assertFalse(Item.isStatusValid(Item.Status.NOT_OK,visas));
        assertFalse(Item.isStatusValid(Item.Status.CANCELLED,visas));
        assertFalse(Item.isStatusValid(Item.Status.POSTPONED,visas));
        visas.pop();
        
        visas.add(POSTPONED_VISA);
        assertFalse(Item.isStatusValid(Item.Status.EVALUATED,visas));
        assertFalse(Item.isStatusValid(Item.Status.DONE,visas));
        assertFalse(Item.isStatusValid(Item.Status.OK,visas));
        assertFalse(Item.isStatusValid(Item.Status.NOT_OK,visas));
        assertFalse(Item.isStatusValid(Item.Status.CANCELLED,visas));
        assertFalse(Item.isStatusValid(Item.Status.POSTPONED,visas));
    }   
    
    public void testAreGeneratedDatesValid() {
        System.out.println("Item.areGeneratedDatesValid");
        Sprint sprint = new Sprint(toolkit);
        Calendar cal = toolkit.getPreparedCalendarInstance();

        cal.set(2006,11,30); sprint.setStartDate(cal.getTime());
        cal.set(2007,0,1);
        assertEquals(cal.getTime(),sprint.getStartDate());

        cal.set(2006,11,28); sprint.setEndDate(cal.getTime());
        cal.set(2007,0,1);
        assertEquals(cal.getTime(),sprint.getEndDate());
        
        cal.set(2007,0,1); sprint.setEndDate(cal.getTime());
        assertEquals(1.0,sprint.getBusinessDaysCount());
        cal.set(2007,0,25); sprint.setEndDate(cal.getTime());
        assertEquals(19.0,sprint.getBusinessDaysCount());
        cal.set(2007,0,23); sprint.setStartDate(cal.getTime());
        assertEquals(3.0,sprint.getBusinessDaysCount());
        cal.set(2007,0,25); sprint.setStartDate(cal.getTime());
        assertEquals(1.0,sprint.getBusinessDaysCount());
        cal.set(2007,0,27); sprint.setStartDate(cal.getTime());
        assertEquals(1.0,sprint.getBusinessDaysCount());
    }
    
    public void testStatusAccordingToSprint(){
        System.out.println("Item.statusAccordingToSprint");
        Sprint earlySprint = new Sprint(toolkit);
        Calendar calendar = toolkit.getPreparedCalendarInstance();
        calendar.set(2007,0,1); earlySprint.setStartDate(calendar.getTime());
        calendar.set(2007,0,30); earlySprint.setEndDate(calendar.getTime());
        
        Sprint lateSprint = new Sprint(toolkit);
        calendar.set(2007,0,31); lateSprint.setStartDate(calendar.getTime());
        calendar.set(2007,1,28); lateSprint.setEndDate(calendar.getTime());
        
        Item item = new Item(toolkit);
        try{
            calendar.set(2007,0,1); item.addVisa(new Item.Visa(Item.Status.EVALUATED,null,calendar.getTime()));
            earlySprint.addItem(item);
            lateSprint.addItem(item);
            calendar.set(2007,0,30); item.addVisa(new Item.Visa(Item.Status.DONE,null,calendar.getTime()));
            calendar.set(2007,1,1); item.addVisa(new Item.Visa(Item.Status.NOT_OK,null,calendar.getTime()));
            calendar.set(2007,2,1); item.addVisa(new Item.Visa(Item.Status.CANCELLED,null,calendar.getTime()));
            
            assertEquals(Item.Status.CANCELLED,item.getStatus());
            assertEquals(Item.Status.DONE,item.getStatus(earlySprint));
            assertEquals(Item.Status.NOT_OK,item.getStatus(lateSprint));            
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
    
    public void testCompareTo(){
        System.out.println("Item.compareTo");
        Member member = new Member("member1");
        Date date = new Date();
        
        Item firstItem = new Item(toolkit);
        firstItem.setBusinessValue(FiboPoint.FIVE);
        firstItem.setEvaluation(FiboPoint.FIVE);
        firstItem.setKey("0");
        
        Item secondItem = new Item(toolkit);

        secondItem.setBusinessValue(FiboPoint.FIVE);
        secondItem.setEvaluation(FiboPoint.EIGHT);
        secondItem.setKey("0");
        assertTrue(firstItem.compareTo(secondItem)<0);
        
        secondItem.setBusinessValue(FiboPoint.THIRTEEN);
        assertTrue(firstItem.compareTo(secondItem)>0);
        
        secondItem.setEvaluation(FiboPoint.FIVE);
        secondItem.setBusinessValue(FiboPoint.FIVE);
        assertTrue(firstItem.compareTo(secondItem)==0);
        
        try{
            firstItem.addVisa(new Item.Visa(Item.Status.EVALUATED, member, date));
            assertTrue(firstItem.compareTo(secondItem)>0);
            
            secondItem.addVisa(new Item.Visa(Item.Status.EVALUATED, member, date));
            assertTrue(firstItem.compareTo(secondItem)==0);
            
            firstItem.addVisa(new Item.Visa(Item.Status.DONE, member, date));
            assertTrue(firstItem.compareTo(secondItem)>0);
            
            secondItem.addVisa(new Item.Visa(Item.Status.DONE, member, date));
            assertTrue(firstItem.compareTo(secondItem)==0);
            
            firstItem.addVisa(new Item.Visa(Item.Status.NOT_OK, member, date));
            assertTrue(firstItem.compareTo(secondItem)<0);

            secondItem.addVisa(new Item.Visa(Item.Status.NOT_OK, member, date));
            assertTrue(firstItem.compareTo(secondItem)==0);
            
            firstItem.addVisa(new Item.Visa(Item.Status.DONE, member, date));
            firstItem.addVisa(new Item.Visa(Item.Status.OK, member, date));
            assertTrue(firstItem.compareTo(secondItem)>0);

            secondItem.addVisa(new Item.Visa(Item.Status.DONE, member, date));
            secondItem.addVisa(new Item.Visa(Item.Status.OK, member, date));
            assertTrue(firstItem.compareTo(secondItem)==0);

            firstItem.removeLastVisa();
            firstItem.addVisa(new Item.Visa(Item.Status.POSTPONED, member, date));
            assertTrue(firstItem.compareTo(secondItem)>0);
            
            secondItem.removeLastVisa();
            secondItem.addVisa(new Item.Visa(Item.Status.POSTPONED, member, date));
            assertTrue(firstItem.compareTo(secondItem)==0);
            
            firstItem.removeLastVisa();
            firstItem.addVisa(new Item.Visa(Item.Status.CANCELLED, member, date));
            assertTrue(firstItem.compareTo(secondItem)>0);
            
            secondItem.removeLastVisa();
            secondItem.addVisa(new Item.Visa(Item.Status.CANCELLED, member, date));
            assertTrue(firstItem.compareTo(secondItem)==0);
            
            secondItem.setBusinessValue(FiboPoint.EIGHT);
            assertTrue(firstItem.compareTo(secondItem)>0);
            
            secondItem.setBusinessValue(FiboPoint.FIVE);
            assertTrue(firstItem.compareTo(secondItem)==0);
            
            secondItem.setKey("1");
            assertTrue(firstItem.compareTo(secondItem)!=0);
            
        } catch(Exception e){
            fail(e.getMessage());
        }
    }
    
    public void testCompareToWithinSprint(){
        System.out.println("Item.compareToWithinSprint");
        Member member = new Member("member1");
        Calendar cal = toolkit.getPreparedCalendarInstance();
        Date today = cal.getTime();
        cal.add(Calendar.DAY_OF_MONTH, 1);
        Date tomorrow = cal.getTime();
        
        Sprint firstSprint = new Sprint(toolkit);
        firstSprint.setStartDate(today);
        firstSprint.setEndDate(today);
        
        Sprint secondSprint = new Sprint(toolkit);
        secondSprint.setStartDate(tomorrow);
        secondSprint.setEndDate(tomorrow);
        
        Item firstItem = new Item(toolkit);
        firstItem.setBusinessValue(FiboPoint.FIVE);
        firstItem.setEvaluation(FiboPoint.FIVE);
        firstItem.setKey("0");
        
        Item secondItem = new Item(toolkit);
        secondItem.setBusinessValue(FiboPoint.FIVE);
        secondItem.setEvaluation(FiboPoint.FIVE);
        secondItem.setKey("0");

        firstSprint.addItem(firstItem);
        firstSprint.addItem(secondItem);
        secondSprint.addItem(firstItem);
        secondSprint.addItem(secondItem);
        
        try{
            // setting visas in first sprint
            firstItem.addVisa(new Item.Visa(Item.Status.EVALUATED, member, today));
            secondItem.addVisa(new Item.Visa(Item.Status.EVALUATED, member, today));
            secondItem.addVisa(new Item.Visa(Item.Status.DONE, member, today));

            // setting visas in second sprint
            firstItem.addVisa(new Item.Visa(Item.Status.DONE, member, tomorrow));       
            
            assertTrue(firstItem.compareTo(secondItem)==0);
            assertTrue(firstItem.compareTo(secondItem,firstSprint)<0);
            assertTrue(firstItem.compareTo(secondItem,secondSprint)==0);
            
        } catch(Exception e){
            fail(e.getMessage());
        }
    }
    
    public void testIsAffectedToALaterSprintThan(){
        System.out.println("Item.isAffectedToALaterSprintThan");
        Calendar cal = toolkit.getPreparedCalendarInstance();
        Date today = cal.getTime();
        cal.add(Calendar.DAY_OF_MONTH, 1);
        Date tomorrow = cal.getTime();
        
        Sprint firstSprint = new Sprint(toolkit);
        firstSprint.setStartDate(today);
        firstSprint.setEndDate(today);
        
        Sprint secondSprint = new Sprint(toolkit);
        secondSprint.setStartDate(tomorrow);
        secondSprint.setEndDate(tomorrow);
        
        Item item = new Item(toolkit);

        firstSprint.addItem(item);
        assertFalse(item.isAffectedToALaterSprintThan(firstSprint));
        
        secondSprint.addItem(item);
        
        assertTrue(item.isAffectedToALaterSprintThan(firstSprint));
        assertFalse(item.isAffectedToALaterSprintThan(secondSprint));
    }
    
    public void testGetLastDoneVisaIfTaskDoneInSprint(){
        System.out.println("Item.getLastDoneVisaIfTaskDoneInSprint");
        Member member = new Member("member1");
        Calendar cal = toolkit.getPreparedCalendarInstance();
        Date today = cal.getTime();
        cal.add(Calendar.DAY_OF_MONTH, 1);
        Date tomorrow = cal.getTime();
        
        Sprint firstSprint = new Sprint(toolkit);
        firstSprint.setStartDate(today);
        firstSprint.setEndDate(today);
        
        Sprint secondSprint = new Sprint(toolkit);
        secondSprint.setStartDate(tomorrow);
        secondSprint.setEndDate(tomorrow);
        
        Item item = new Item(toolkit);

        firstSprint.addItem(item);
        secondSprint.addItem(item);
        
        try{
            item.addVisa(new Item.Visa(Item.Status.EVALUATED, member, today));
            item.addVisa(new Item.Visa(Item.Status.DONE, member, tomorrow));
            
            assertTrue(item.getLastDoneVisaIfTaskDoneInSprint(firstSprint)==null);
            assertTrue(item.getLastDoneVisaIfTaskDoneInSprint(secondSprint)!=null);
            
            item.removeLastVisa();
            item.addVisa(new Item.Visa(Item.Status.DONE, member, today));
            item.addVisa(new Item.Visa(Item.Status.NOT_OK, member, tomorrow));
            
            assertTrue(item.getLastDoneVisaIfTaskDoneInSprint(firstSprint)!=null);
            assertTrue(item.getLastDoneVisaIfTaskDoneInSprint(secondSprint)==null);
            
            item.addVisa(new Item.Visa(Item.Status.DONE, member, tomorrow));
            
            Item.Visa visa1 = item.getLastDoneVisaIfTaskDoneInSprint(firstSprint);
            Item.Visa visa2 = item.getLastDoneVisaIfTaskDoneInSprint(secondSprint);
            
            assertTrue(visa1!=null&&visa2!=null);
            assertTrue(!visa1.equals(visa2));
            
        } catch(Exception e){
            fail(e.getMessage());
        }
    }
    
    public void testIsOrphan(){
        System.out.println("Item.isOrphan");
        Item item = new Item(toolkit);
        assertTrue(item.isOrphan());
        ProjectItemSet pis = new ProjectItemSet();
        pis.affectItem(item);
        assertFalse(item.isOrphan());
    }
}
