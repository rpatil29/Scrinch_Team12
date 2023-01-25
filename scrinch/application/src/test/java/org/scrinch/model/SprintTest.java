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
import java.util.Vector;
import junit.framework.TestCase;

public class SprintTest extends TestCase {
    
    private int defaultCurrentSprintWeekCountValue;
    
    private class DefaultData {
        public DefaultData(){
            defaultMember.setNickname("default member");
        }
        List<Item> items = new Vector<Item>();
        Member defaultMember = new Member("member1");
    }
    
    private Date TODAY;

    private ScrinchEnvToolkit toolkit;

    @Override
    public void setUp(){
        toolkit = new ScrinchEnvToolkit();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2011);
        calendar.set(Calendar.MONTH, 6);
        calendar.set(Calendar.DAY_OF_MONTH, 26);
        TODAY = calendar.getTime();
        toolkit.setPreparedCalendarInstance(calendar);
        defaultCurrentSprintWeekCountValue = 
                toolkit.getSprintFactory().getCurrentSprintWeekCount();
    }
    
    @Override
    public void tearDown(){
        toolkit.getSprintFactory().setCurrentSprintWeekCount(
                defaultCurrentSprintWeekCountValue);
        toolkit.getSprintFactory().disposeAll();
    }
       
    private DefaultData getDefaultData(){
        DefaultData data = new DefaultData();
        try{
            Item item = new Item(toolkit);
            item.setEvaluation(FiboPoint.ONE);
            item.addVisa(new Item.Visa(Item.Status.EVALUATED, data.defaultMember, TODAY));
            item.setWorkType(toolkit.getWorkTypeFactory().findWorkTypeWithLabel("ANALYSIS"));
            data.items.add(item);

            item = new Item(toolkit);
            item.setEvaluation(FiboPoint.TWO);
            item.addVisa(new Item.Visa(Item.Status.EVALUATED, data.defaultMember, TODAY));
            item.addVisa(new Item.Visa(Item.Status.DONE, data.defaultMember, TODAY));
            item.setWorkType(toolkit.getWorkTypeFactory().findWorkTypeWithLabel("BUSINESS_LOGIC_DEVELOPMENT"));
            item.setOriginType(toolkit.getOriginTypeFactory().findOriginTypeWithLabel("NEW_REQUIREMENT"));
            data.items.add(item);

            item = new Item(toolkit);
            item.setEvaluation(FiboPoint.THREE);
            item.addVisa(new Item.Visa(Item.Status.EVALUATED, data.defaultMember, TODAY));
            item.addVisa(new Item.Visa(Item.Status.DONE, data.defaultMember, TODAY));
            item.addVisa(new Item.Visa(Item.Status.NOT_OK, data.defaultMember, TODAY));
            item.setWorkType(toolkit.getWorkTypeFactory().findWorkTypeWithLabel("DOCUMENTATION"));
            item.setOriginType(toolkit.getOriginTypeFactory().findOriginTypeWithLabel("ORIGINAL_REQUIREMENT"));
            data.items.add(item);

            item = new Item(toolkit);
            item.setEvaluation(FiboPoint.FIVE);
            item.addVisa(new Item.Visa(Item.Status.EVALUATED, data.defaultMember, TODAY));
            item.addVisa(new Item.Visa(Item.Status.DONE, data.defaultMember, TODAY));
            item.addVisa(new Item.Visa(Item.Status.OK, data.defaultMember, TODAY));
            item.setWorkType(toolkit.getWorkTypeFactory().findWorkTypeWithLabel("REQ_DOCUMENTATION"));
            item.setOriginType(toolkit.getOriginTypeFactory().findOriginTypeWithLabel("REQUIREMENT_CHANGE"));
            data.items.add(item);

            item = new Item(toolkit);
            item.setEvaluation(FiboPoint.EIGHT);
            item.addVisa(new Item.Visa(Item.Status.EVALUATED, data.defaultMember, TODAY));
            item.addVisa(new Item.Visa(Item.Status.DONE, data.defaultMember, TODAY));
            item.addVisa(new Item.Visa(Item.Status.NOT_OK, data.defaultMember, TODAY));
            item.addVisa(new Item.Visa(Item.Status.DONE, data.defaultMember, TODAY));
            item.addVisa(new Item.Visa(Item.Status.OK, data.defaultMember, TODAY));
            item.setWorkType(toolkit.getWorkTypeFactory().findWorkTypeWithLabel("TECHNICAL_DEVELOPMENT"));
            item.setOriginType(toolkit.getOriginTypeFactory().findOriginTypeWithLabel("TUNING"));
            data.items.add(item);

            item = new Item(toolkit);
            item.setEvaluation(FiboPoint.THIRTEEN);
            item.addVisa(new Item.Visa(Item.Status.EVALUATED, data.defaultMember, TODAY));
            item.setWorkType(toolkit.getWorkTypeFactory().findWorkTypeWithLabel("TECHNICAL_OPERATION"));
            data.items.add(item);

            item = new Item(toolkit);
            item.setEvaluation(FiboPoint.TWENTY_ONE);
            item.addVisa(new Item.Visa(Item.Status.EVALUATED, data.defaultMember, TODAY));
            data.items.add(item);

            item = new Item(toolkit);
            item.setEvaluation(FiboPoint.TWENTY_ONE);
            data.items.add(item);

            item = new Item(toolkit);
            item.setEvaluation(FiboPoint.TWENTY_ONE);
            item.addVisa(new Item.Visa(Item.Status.CANCELLED, data.defaultMember, TODAY));
            data.items.add(item);

            item = new Item(toolkit);
            item.setEvaluation(FiboPoint.TWENTY_ONE);
            item.addVisa(new Item.Visa(Item.Status.POSTPONED, data.defaultMember, TODAY));
            data.items.add(item);
        } catch(Exception e){
            fail(e.getMessage());
        }
        return data;
    }
    
    public SprintTest(String testName) {
        super(testName);
    }            

    public void testIsTopical() {
        System.out.println("Sprint.isTopical"+toolkit.getPreparedCalendarInstance().getTime());
        Sprint sprint = new Sprint(toolkit);
        assertEquals(true, sprint.isTopical());
        
        Calendar cal = toolkit.getPreparedCalendarInstance();
        cal.add(Calendar.DAY_OF_MONTH, -3);
        sprint.setStartDate(cal.getTime());
        sprint.setEndDate(cal.getTime());
        assertEquals(false, sprint.isTopical());
        
        cal.add(Calendar.DAY_OF_MONTH, 4);
        sprint.setStartDate(cal.getTime());
        sprint.setEndDate(cal.getTime());
        assertEquals(true, sprint.isTopical());
    }

    public void testIsRunning() {
        System.out.println("Sprint.isRunning");
        Sprint sprint = new Sprint(toolkit);
        Calendar cal = toolkit.getPreparedCalendarInstance();
        cal.set(Calendar.YEAR, 2011);
        cal.set(Calendar.MONTH, 07);
        cal.set(Calendar.DAY_OF_MONTH, 26);
        assertEquals(true, sprint.isRunning());
        
        cal.add(Calendar.DAY_OF_MONTH, -3);
        sprint.setStartDate(cal.getTime());
        sprint.setEndDate(cal.getTime());
        assertEquals(false, sprint.isRunning());
        
        cal.add(Calendar.DAY_OF_MONTH, 4);
        sprint.setStartDate(cal.getTime());
        sprint.setEndDate(cal.getTime());        
        assertEquals(false, sprint.isRunning());
    }
    
    public void testReplaceExistingMemberList() {
        System.out.println("Sprint.replaceExistingMemberList");
        List<Member> memberList = new Vector();
        memberList.add(new Member("member1"));
        memberList.add(new Member("member2"));
        memberList.add(new Member("member3"));
        Sprint sprint = new Sprint(toolkit);
        sprint.addAllMembers(memberList);
        assertEquals(3, sprint.getMembers().size());
        
        memberList.clear();
        sprint.replaceExistingMemberList(memberList);
        assertEquals(0, sprint.getMembers().size());
        
        memberList.add(new Member("member4"));
        memberList.add(new Member("member5"));
        memberList.add(new Member("member6"));
        sprint.addAllMembers(memberList);
        assertEquals(3, sprint.getMembers().size());
        
        memberList.clear();
        memberList.add(new Member("member7"));
        memberList.add(new Member("member8"));
        sprint.replaceExistingMemberList(memberList);
        assertEquals(2, sprint.getMembers().size());
    }
       
    public void testSettingDates(){
        System.out.println("Sprint.setStartDate");
        System.out.println("Sprint.setEndDate");
        Sprint sprint = new Sprint(toolkit);
        assertEquals(sprint.getStartDate(), sprint.getEndDate());
        
        Calendar cal = toolkit.getPreparedCalendarInstance();
        cal.add(Calendar.DATE, 10);
        sprint.setStartDate(cal.getTime());
        assertEquals(sprint.getStartDate(), sprint.getEndDate());
        
        cal.add(Calendar.DATE, 1);
        sprint.setEndDate(cal.getTime());
        assertTrue(!sprint.getEndDate().before(sprint.getStartDate()));
        
        cal.add(Calendar.DATE, -10);
        sprint.setEndDate(cal.getTime());
        assertEquals(sprint.getStartDate(), sprint.getEndDate());
    }
    
    public void testGetBusinessDaysCount() {
        System.out.println("Sprint.getBusinessDaysCount");
        
        Sprint sprint = toolkit.getSprintFactory().createSprint();
        assertEquals((double)toolkit.getSprintFactory().getCurrentSprintWeekCount()*5,
                sprint.getBusinessDaysCount());
        
        toolkit.getSprintFactory().setCurrentSprintWeekCount(3);
        sprint = toolkit.getSprintFactory().createSprint();
        assertEquals((double)toolkit.getSprintFactory().getCurrentSprintWeekCount()*5,
                sprint.getBusinessDaysCount());
        
        sprint.setStartDate(TODAY);
        sprint.setEndDate(TODAY);
        assertEquals(1.0,sprint.getBusinessDaysCount());
    }
    
    public void testGetWorkingDates() {
        System.out.println("Sprint.getWorkingDates");
        
        Sprint sprint = toolkit.getSprintFactory().createSprint();
        List<Date> result = sprint.getWorkingDates();
        assertEquals(toolkit.getSprintFactory().getCurrentSprintWeekCount()*5,
                result.size());
        
        toolkit.getSprintFactory().setCurrentSprintWeekCount(3);
        sprint = toolkit.getSprintFactory().createSprint();
        result = sprint.getWorkingDates();
        assertEquals(toolkit.getSprintFactory().getCurrentSprintWeekCount()*5,
                result.size());
        
        sprint.setStartDate(TODAY);
        sprint.setEndDate(TODAY);
        result = sprint.getWorkingDates();
        assertEquals(1,result.size());
    }

    public void testHasItem() {
        System.out.println("Sprint.hasItem");
        List<Item> items = getDefaultData().items;
        Item item = items.get(3);
        item.setKey("1");
        Sprint sprint = new Sprint(toolkit);
        
        assertFalse(sprint.hasItem(item.getKey()));
        
        sprint.affectItem(item);
        assertTrue(sprint.hasItem(item.getKey()));
    }
    
    public void testSlowDownAndVelocityCorrections() {
        System.out.println("Sprint.getAvailableResourcesInFiboPoints");
        System.out.println("Sprint.getGlobalSlowDown");
        System.out.println("Sprint.getCorrectedBusinessDaysCountWithSlowDowns");
        System.out.println("Sprint.getRealVelocity");
        try{            
            toolkit.getSprintFactory().setCurrentSprintWeekCount(2);
            Sprint sprint = toolkit.getSprintFactory().createSprint();
            sprint.setBaseVelocity(20.0);
            
            sprint.addMember(new Member("member1"));
            assertEquals(200.0, sprint.getAvailableResourcesInFiboPoints());
            assertEquals(0.0,  sprint.getGlobalSlowDown());  
            assertEquals(10.0, sprint.getCorrectedBusinessDaysCountWithSlowDowns());  
            assertEquals(20.0, sprint.getPossibleVelocity());

            SlowDown slow = new SlowDown();
            slow.addMember(new Member("member2"));
            slow.setDayCount(1);
            sprint.addSlowDown(slow);
            assertEquals(180.0, sprint.getAvailableResourcesInFiboPoints());
            assertEquals(1.0, sprint.getGlobalSlowDown());  
            assertEquals(9.0, sprint.getCorrectedBusinessDaysCountWithSlowDowns());  
            assertEquals(18.0, sprint.getPossibleVelocity());
            
            slow.setDayCount(2);
            assertEquals(160.0, sprint.getAvailableResourcesInFiboPoints());
            assertEquals(2.0, sprint.getGlobalSlowDown());  
            assertEquals(8.0, sprint.getCorrectedBusinessDaysCountWithSlowDowns());  
            assertEquals(16.0, sprint.getPossibleVelocity());
            
            slow.addMember(new Member("member3"));
            assertEquals(120.0, sprint.getAvailableResourcesInFiboPoints());
            assertEquals(4.0, sprint.getGlobalSlowDown());  
            assertEquals(6.0, sprint.getCorrectedBusinessDaysCountWithSlowDowns());  
            assertEquals(12.0, sprint.getPossibleVelocity());
            
            sprint.addMember(new Member("member4"));
            assertEquals(160.0, sprint.getAvailableResourcesInFiboPoints());
            assertEquals(4.0, sprint.getGlobalSlowDown());  
            assertEquals(8.0, sprint.getCorrectedBusinessDaysCountWithSlowDowns());  
            assertEquals(16.0, sprint.getPossibleVelocity());
            
        }catch(Exception e){
            fail(e.getMessage());
        }
    }

    public void testResultingValues() throws Exception {
        System.out.println("Sprint.getResultingVelocity");
        System.out.println("Sprint.getResultingWorkInFiboPoints");
        try{ 
            toolkit.getSprintFactory().setCurrentSprintWeekCount(2);
            Sprint sprint = toolkit.getSprintFactory().createSprint();
            sprint.setBaseVelocity(20.0);
            sprint.addItems(getDefaultData().items);
            sprint.addMember(new Member("member1"));
            assertEquals(15.0, sprint.getResultingVelocity());
            assertEquals(15.0, sprint.getResultingWorkInFiboPoints());
            
            Calendar cal = toolkit.getPreparedCalendarInstance();
            cal.setTime(sprint.getStartDate());
            cal.add(Calendar.DATE, -1);
            sprint.setStartDate(toolkit.getPreviousWeekDayIfNotWeekDay(cal.getTime()));
            assertEquals(7.5, sprint.getResultingVelocity()); 
            assertEquals(15.0, sprint.getResultingWorkInFiboPoints());
        }catch(Exception e){
            fail(e.getMessage());
        }
    }

    public void testGetOverloadedResourcesCount() {
        System.out.println("Sprint.getOverloadedResourcesCount");
        try{ 
            toolkit.getSprintFactory().setCurrentSprintWeekCount(2);
            Sprint sprint = toolkit.getSprintFactory().createSprint();
            sprint.setBaseVelocity(20.0);
            sprint.addItems(getDefaultData().items);
            sprint.addMember(new Member("member1"));
            assertEquals(53.0-200.0, sprint.getOverloadedResourcesCount());
                        
            Member member = new Member("member2");
            for (int i=0; i<10; i++){
                Item item = new Item(toolkit);
                item.setTitle("fake item "+i);
                item.setEvaluation(FiboPoint.TWENTY_ONE);
                item.addVisa(new Item.Visa(Item.Status.EVALUATED, member, TODAY));
                sprint.addItem(item);
            }
            assertEquals((double)10*21+53-200, sprint.getOverloadedResourcesCount());
            
            SlowDown slow = new SlowDown();
            slow.addMember(new Member("member3"));
            slow.setDayCount(1);
            sprint.addSlowDown(slow);
            assertEquals((double)10*21+53-180, sprint.getOverloadedResourcesCount());
            
        }catch(Exception e){
            fail(e.getMessage());
        }
    }
}
