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
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import junit.framework.TestCase;

public class ItemToolkitTest extends TestCase {
    
    private Sprint sprint;
    
    private class DefaultData {
        public DefaultData(){
            defaultMember.setNickname("default member");
        }
        List<Item> items = new Vector<Item>();
        Member defaultMember = new Member("member1");
    }
       
    private final static Date TODAY = new Date();
    
    public ItemToolkitTest(String testName) {
        super(testName);
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

    private ScrinchEnvToolkit toolkit;

    @Override
    protected void setUp() throws Exception {
        toolkit = new ScrinchEnvToolkit();
        sprint = toolkit.getSprintFactory().createSprint();
        sprint.setStartDate(TODAY);
        sprint.setEndDate(TODAY);
    }
    
    @Override
    protected void tearDown(){
        toolkit.getSprintFactory().disposeAll();
    }
    
    public void testGetTotalFiboPoints() {
        System.out.println("ItemToolkit.getTotalFiboPoints");
        int expResult = 116;
        int result = ItemToolkit.getTotalFiboPoints(getDefaultData().items);
        assertEquals(expResult, result);
    }

    public void testGetOldestDate() {
        try{
            System.out.println("ItemToolkit.getOldestDate");
            DefaultData data = getDefaultData();
            Calendar cal = toolkit.getPreparedCalendarInstance();
            cal.add(Calendar.DAY_OF_MONTH, -10);
            Date expResult = cal.getTime();
            List<Item> items = data.items;
            items.get(2).removeLastVisa();
            items.get(2).removeLastVisa();
            items.get(2).removeLastVisa();
            items.get(2).addVisa(new Item.Visa(Item.Status.EVALUATED, data.defaultMember, expResult));

            Date result = ItemToolkit.getOldestDate(items);
            assertEquals(expResult, result);
        }catch(Exception e){
            fail(e.getMessage());
        }
    }

    public void testGetLatestDate() {
        try{
            System.out.println("ItemToolkit.getOldestDate");
            DefaultData data = getDefaultData();
            Calendar cal = toolkit.getPreparedCalendarInstance();
            cal.add(Calendar.DAY_OF_MONTH, 5);
            Date expResult = cal.getTime();
            List<Item> items = data.items;
            items.get(1).removeLastVisa();
            items.get(1).removeLastVisa();
            items.get(1).addVisa(new Item.Visa(Item.Status.EVALUATED, data.defaultMember, expResult));

            Date result = ItemToolkit.getLatestDate(items);
            assertEquals(expResult, result);
        }catch(Exception e){
            fail(e.getMessage());
        }
    }

    public void testFindWorkDoneItems() {
        System.out.println("ItemToolkit.findWorkDoneItems");
        Collection<Item> result = ItemToolkit.findWorkDoneItems(getDefaultData().items);
        assertEquals(3, result.size());
    }

    public void testFindWorkToBeDoneItems() {
        Collection<Item> result = ItemToolkit.findWorkToBeDoneItems(getDefaultData().items);
        assertEquals(4, result.size());
    }

    public void testFindItem() {
        System.out.println("ItemToolkit.findItem");
        List<Item> items = getDefaultData().items;
        String key = "1";
        Item expResult = items.get(3);
        expResult.setKey(key);
        Item result = ItemToolkit.findItem(items, key);
        assertEquals(expResult, result);
    }

    public void testFindItemsWithTarget() {
        System.out.println("ItemToolkit.findItemsWithTarget");
        List<Item> items = getDefaultData().items;

        Target target = new Target(toolkit);
        Collection<Item> itemList = ItemToolkit.findItemsWithTarget(items, target);
        assertEquals(0, itemList.size());
        
        items.get(5).setTarget(target);
        items.get(6).setTarget(target);
        items.get(7).setTarget(target);
        items.get(8).setTarget(target);
        items.get(9).setTarget(target);
        itemList = ItemToolkit.findItemsWithTarget(items, target);
        assertEquals(5, itemList.size());
    }

    public void testFindItemsWithStatus() {
        System.out.println("ItemToolkit.findItemsWithStatus");
        Collection<Item> result;
        result = ItemToolkit.findItemsWithStatus(getDefaultData().items,Item.Status.CANCELLED);
        assertEquals(1, result.size());
        result = ItemToolkit.findItemsWithStatus(getDefaultData().items,Item.Status.DONE);
        assertEquals(1, result.size());
        result = ItemToolkit.findItemsWithStatus(getDefaultData().items,Item.Status.EVALUATED);
        assertEquals(3, result.size());
        result = ItemToolkit.findItemsWithStatus(getDefaultData().items,Item.Status.NOT_OK);
        assertEquals(1, result.size());
        result = ItemToolkit.findItemsWithStatus(getDefaultData().items,Item.Status.OK);
        assertEquals(2, result.size());
        result = ItemToolkit.findItemsWithStatus(getDefaultData().items,Item.Status.POSTPONED);
        assertEquals(1, result.size());
        result = ItemToolkit.findItemsWithStatus(getDefaultData().items,Item.Status.STANDBY);
        assertEquals(1, result.size());
        result = ItemToolkit.findItemsWithStatus(getDefaultData().items,Item.Status.DELAYED);
        assertEquals(0, result.size());
    }

    public void testFindItemsWithVisaFrom() {
        try{
            System.out.println("ItemToolkit.findItemsWithVisaFrom");
            DefaultData data = getDefaultData();
            Member member = new Member("member1");
            member.setNickname("nickname");
            List<Item> items = data.items;

            Collection<Item> itemList;
            itemList = ItemToolkit.findItemsWithVisaFrom(items, data.defaultMember.getNickname());
            assertEquals(9, itemList.size());

            itemList = ItemToolkit.findItemsWithVisaFrom(items, member.getNickname());
            assertEquals(null, itemList);

            data.items.get(0).addVisa(new Item.Visa(Item.Status.DONE, member, TODAY));
            itemList = ItemToolkit.findItemsWithVisaFrom(items, data.defaultMember.getNickname());
            assertEquals(9, itemList.size());
            itemList = ItemToolkit.findItemsWithVisaFrom(items, member.getNickname());
            assertEquals(1, itemList.size());

            data.items.get(0).removeLastVisa();
            data.items.get(0).removeLastVisa();
            itemList = ItemToolkit.findItemsWithVisaFrom(items, data.defaultMember.getNickname());
            assertEquals(8, itemList.size());        
            itemList = ItemToolkit.findItemsWithVisaFrom(items, member.getNickname());
            assertEquals(null, itemList); 
        }catch(Exception e){
            fail(e.getMessage());
        }
    }

    public void testOrphanItem() {
        System.out.println("ItemToolkit.findOrphanItems");
        Item item = getDefaultData().items.get(0);
        assertTrue(item.isOrphan());
        
        ProjectItemSet pis = new ProjectItemSet();
        pis.affectItem(item);
        assertFalse(item.isOrphan());
    }

    public void testFindToBeReaffectedItems() {
        System.out.println("ItemToolkit.findToBeReaffectedItems");
        List<Item> items = getDefaultData().items;
        Collection<Item> result = ItemToolkit.findToBeReaffectedItems(items, toolkit);
        assertEquals(4, result.size());
        
        sprint.affectItem(items.get(0));
        result = ItemToolkit.findToBeReaffectedItems(items, toolkit);
        assertEquals(3, result.size());
    }

    public void testFindWorkToBeDoneItemsInTopicalSprints() {
        System.out.println("ItemToolkit.findWorkToBeDoneItemsInTopicalSprints");
        List<Item> items = getDefaultData().items;
        Collection<Item> result = ItemToolkit.findWorkToBeDoneItemsInTopicalSprints(items, toolkit);
        assertEquals(0, result.size());
        
        sprint.setStartDate(TODAY);
        sprint.setEndDate(TODAY);
        sprint.affectItem(items.get(0));
        result = ItemToolkit.findWorkToBeDoneItemsInTopicalSprints(items, toolkit);
        assertEquals(1, result.size());
    }

    public void testFindItemsWithWorkType() {
        System.out.println("ItemToolkit.findItemsWithWorkType");
        List<Item> items = getDefaultData().items;
        Collection<Item> result;
        
        result = ItemToolkit.findItemsWithWorkType(items, toolkit.getWorkTypeFactory().findWorkTypeWithLabel("ANALYSIS"));
        assertEquals(1, result.size());
        
        result = ItemToolkit.findItemsWithWorkType(items, toolkit.getWorkTypeFactory().findWorkTypeWithLabel("BUSINESS_LOGIC_DEVELOPMENT"));
        assertEquals(1, result.size());

        result = ItemToolkit.findItemsWithWorkType(items, toolkit.getWorkTypeFactory().findWorkTypeWithLabel("DOCUMENTATION"));
        assertEquals(1, result.size());

        result = ItemToolkit.findItemsWithWorkType(items, toolkit.getWorkTypeFactory().findWorkTypeWithLabel("REQ_DOCUMENTATION"));
        assertEquals(1, result.size());

        result = ItemToolkit.findItemsWithWorkType(items, toolkit.getWorkTypeFactory().findWorkTypeWithLabel("TECHNICAL_DEVELOPMENT"));
        assertEquals(1, result.size());

        result = ItemToolkit.findItemsWithWorkType(items, toolkit.getWorkTypeFactory().findWorkTypeWithLabel("TECHNICAL_OPERATION"));
        assertEquals(1, result.size());

        result = ItemToolkit.findItemsWithWorkType(items, toolkit.getWorkTypeFactory().findWorkTypeWithLabel("TESTS_DOCUMENTATION"));
        assertEquals(0, result.size());

        result = ItemToolkit.findItemsWithWorkType(items, toolkit.getWorkTypeFactory().getDefaultWorkType());
        assertEquals(4, result.size());
    }

    public void testFindWorkDoneItemsWithWorkType() {
        System.out.println("ItemToolkit.findWorkDoneItemsWithWorkType");
        List<Item> items = getDefaultData().items;
        Collection<Item> result;
        
        result = ItemToolkit.findWorkDoneItemsWithWorkType(items, toolkit.getWorkTypeFactory().findWorkTypeWithLabel("ANALYSIS"));
        assertEquals(0, result.size());
        
        result = ItemToolkit.findWorkDoneItemsWithWorkType(items, toolkit.getWorkTypeFactory().findWorkTypeWithLabel("BUSINESS_LOGIC_DEVELOPMENT"));
        assertEquals(1, result.size());

        result = ItemToolkit.findWorkDoneItemsWithWorkType(items, toolkit.getWorkTypeFactory().findWorkTypeWithLabel("DOCUMENTATION"));
        assertEquals(0, result.size());

        result = ItemToolkit.findWorkDoneItemsWithWorkType(items, toolkit.getWorkTypeFactory().findWorkTypeWithLabel("REQ_DOCUMENTATION"));
        assertEquals(1, result.size());

        result = ItemToolkit.findWorkDoneItemsWithWorkType(items, toolkit.getWorkTypeFactory().findWorkTypeWithLabel("TECHNICAL_DEVELOPMENT"));
        assertEquals(1, result.size());

        result = ItemToolkit.findWorkDoneItemsWithWorkType(items, toolkit.getWorkTypeFactory().findWorkTypeWithLabel("TECHNICAL_OPERATION"));
        assertEquals(0, result.size());

        result = ItemToolkit.findWorkDoneItemsWithWorkType(items, toolkit.getWorkTypeFactory().findWorkTypeWithLabel("TESTS_DOCUMENTATION"));
        assertEquals(0, result.size());

        result = ItemToolkit.findWorkDoneItemsWithWorkType(items, toolkit.getWorkTypeFactory().getDefaultWorkType());
        assertEquals(0, result.size());
    }

    public void testFindWorkToBeDoneItemsWithWorkType() {
        System.out.println("ItemToolkit.findWorkToBeDoneItemsWithWorkType");
        System.out.println("ItemToolkit.findWorkDoneItemsWithWorkType");
        List<Item> items = getDefaultData().items;
        Collection<Item> result;
        
        result = ItemToolkit.findWorkToBeDoneItemsWithWorkType(items, toolkit.getWorkTypeFactory().findWorkTypeWithLabel("ANALYSIS"));
        assertEquals(1, result.size());
        
        result = ItemToolkit.findWorkToBeDoneItemsWithWorkType(items, toolkit.getWorkTypeFactory().findWorkTypeWithLabel("BUSINESS_LOGIC_DEVELOPMENT"));
        assertEquals(0, result.size());

        result = ItemToolkit.findWorkToBeDoneItemsWithWorkType(items, toolkit.getWorkTypeFactory().findWorkTypeWithLabel("DOCUMENTATION"));
        assertEquals(1, result.size());

        result = ItemToolkit.findWorkToBeDoneItemsWithWorkType(items, toolkit.getWorkTypeFactory().findWorkTypeWithLabel("REQ_DOCUMENTATION"));
        assertEquals(0, result.size());

        result = ItemToolkit.findWorkToBeDoneItemsWithWorkType(items, toolkit.getWorkTypeFactory().findWorkTypeWithLabel("TECHNICAL_DEVELOPMENT"));
        assertEquals(0, result.size());

        result = ItemToolkit.findWorkToBeDoneItemsWithWorkType(items, toolkit.getWorkTypeFactory().findWorkTypeWithLabel("TECHNICAL_OPERATION"));
        assertEquals(1, result.size());

        result = ItemToolkit.findWorkToBeDoneItemsWithWorkType(items, toolkit.getWorkTypeFactory().findWorkTypeWithLabel("TESTS_DOCUMENTATION"));
        assertEquals(0, result.size());

        result = ItemToolkit.findWorkToBeDoneItemsWithWorkType(items, toolkit.getWorkTypeFactory().getDefaultWorkType());
        assertEquals(1, result.size());
    }

    public void testFindWorkDoneItemsWithRequestType() {
        System.out.println("ItemToolkit.findWorkDoneItemsWithRequestType");
        List<Item> items = getDefaultData().items;
        Collection<Item> result;
        
        result = ItemToolkit.findWorkDoneItemsWithOriginType(items, toolkit.getOriginTypeFactory().findOriginTypeWithLabel("Type.BUG"));
        assertEquals(0, result.size());
        
        result = ItemToolkit.findWorkDoneItemsWithOriginType(items, toolkit.getOriginTypeFactory().findOriginTypeWithLabel("NEW_REQUIREMENT"));
        assertEquals(1, result.size());

        result = ItemToolkit.findWorkDoneItemsWithOriginType(items, toolkit.getOriginTypeFactory().findOriginTypeWithLabel("ORIGINAL_REQUIREMENT"));
        assertEquals(0, result.size());

        result = ItemToolkit.findWorkDoneItemsWithOriginType(items, toolkit.getOriginTypeFactory().findOriginTypeWithLabel("REQUIREMENT_CHANGE"));
        assertEquals(1, result.size());

        result = ItemToolkit.findWorkDoneItemsWithOriginType(items, toolkit.getOriginTypeFactory().findOriginTypeWithLabel("TUNING"));
        assertEquals(1, result.size());

        result = ItemToolkit.findWorkDoneItemsWithOriginType(items, toolkit.getOriginTypeFactory().getDefaultOriginType());
        assertEquals(0, result.size());
    }

    public void testFindWorkToBeDoneItemsWithRequestType() {
        System.out.println("ItemToolkit.findWorkToBeDoneItemsWithRequestType");
        List<Item> items = getDefaultData().items;
        Collection<Item> result;
        
        result = ItemToolkit.findWorkToBeDoneItemsWithOriginType(items, toolkit.getOriginTypeFactory().findOriginTypeWithLabel("BUG"));
        assertEquals(0, result.size());
        
        result = ItemToolkit.findWorkToBeDoneItemsWithOriginType(items, toolkit.getOriginTypeFactory().findOriginTypeWithLabel("NEW_REQUIREMENT"));
        assertEquals(0, result.size());

        result = ItemToolkit.findWorkToBeDoneItemsWithOriginType(items, toolkit.getOriginTypeFactory().findOriginTypeWithLabel("ORIGINAL_REQUIREMENT"));
        assertEquals(1, result.size());

        result = ItemToolkit.findWorkToBeDoneItemsWithOriginType(items, toolkit.getOriginTypeFactory().findOriginTypeWithLabel("REQUIREMENT_CHANGE"));
        assertEquals(0, result.size());

        result = ItemToolkit.findWorkToBeDoneItemsWithOriginType(items, toolkit.getOriginTypeFactory().findOriginTypeWithLabel("TUNING"));
        assertEquals(0, result.size());

        result = ItemToolkit.findWorkToBeDoneItemsWithOriginType(items, toolkit.getOriginTypeFactory().getDefaultOriginType());
        assertEquals(3, result.size());
    }

    public void testFindItemsWithRequestType() {
        System.out.println("ItemToolkit.findItemsWithRequestType");
        List<Item> items = getDefaultData().items;
        Collection<Item> result;
        
        result = ItemToolkit.findItemsWithOriginType(items, toolkit.getOriginTypeFactory().findOriginTypeWithLabel("BUG"));
        assertEquals(0, result.size());
        
        result = ItemToolkit.findItemsWithOriginType(items, toolkit.getOriginTypeFactory().findOriginTypeWithLabel("NEW_REQUIREMENT"));
        assertEquals(1, result.size());

        result = ItemToolkit.findItemsWithOriginType(items, toolkit.getOriginTypeFactory().findOriginTypeWithLabel("ORIGINAL_REQUIREMENT"));
        assertEquals(1, result.size());

        result = ItemToolkit.findItemsWithOriginType(items, toolkit.getOriginTypeFactory().findOriginTypeWithLabel("REQUIREMENT_CHANGE"));
        assertEquals(1, result.size());

        result = ItemToolkit.findItemsWithOriginType(items, toolkit.getOriginTypeFactory().findOriginTypeWithLabel("TUNING"));
        assertEquals(1, result.size());

        result = ItemToolkit.findItemsWithOriginType(items, toolkit.getOriginTypeFactory().getDefaultOriginType());
        assertEquals(6, result.size());
    }

    public void testFindSprintsUsingItems() {
        System.out.println("ItemToolkit.findSprintsUsingItems");
        List<Item> items = getDefaultData().items;
        Collection<Sprint> result = ItemToolkit.findSprintsUsingItems(items, toolkit);
        assertEquals(null, result);
        
        sprint.affectItem(items.get(0));
        result = ItemToolkit.findSprintsUsingItems(items, toolkit);
        assertEquals(1, result.size());
    }

    public void testGetActiveItemEvaluationCount() {
        System.out.println("ItemToolkit.getActiveItemEvaluationCount");
        int result = ItemToolkit.getActiveItemEvaluationCount(getDefaultData().items);
        int workDone = ItemToolkit.getWorkDoneItemEvaluationCount(getDefaultData().items);
        int workToBeDone = ItemToolkit.getWorkToBeDoneItemEvaluationCount(getDefaultData().items);
        int expectedResult = 53;
        assertEquals(expectedResult, result);
        assertEquals(expectedResult, workDone+workToBeDone);
    }

    public void testGetWorkDoneItemEvaluationCount() {
        System.out.println("ItemToolkit.getWorkDoneItemEvaluationCount");
        int result = ItemToolkit.getWorkDoneItemEvaluationCount(getDefaultData().items);
        assertEquals(15, result);
    }

    public void testGetWorkToBeDoneItemEvaluationCount() {
        System.out.println("ItemToolkit.getWorkToBeDoneItemEvaluationCount");
        int result = ItemToolkit.getWorkToBeDoneItemEvaluationCount(getDefaultData().items);
        assertEquals(38, result);
    }
}