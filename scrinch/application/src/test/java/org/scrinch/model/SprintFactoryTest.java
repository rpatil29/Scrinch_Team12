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

public class SprintFactoryTest extends TestCase {
    
    private int defaultCurrentSprintWeekCountValue;
    private Sprint latestSprint;
    private Sprint currentSprint;
    private Sprint oldestSprint;
    private Date latestDate;
    private Date oldestDate;

    private ScrinchEnvToolkit toolkit;
    
    @Override
    public void setUp(){
        toolkit = new ScrinchEnvToolkit();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2011);
        calendar.set(Calendar.MONTH, 6);
        calendar.set(Calendar.DAY_OF_MONTH, 26);
        toolkit.setPreparedCalendarInstance(calendar);

        Calendar cal = toolkit.getPreparedCalendarInstance();
        defaultCurrentSprintWeekCountValue = 
            toolkit.getSprintFactory().getCurrentSprintWeekCount();
        toolkit.getSprintFactory().setCurrentSprintWeekCount(2);

        // non topical sprint
        oldestSprint = toolkit.getSprintFactory().createSprint();
        cal.add(Calendar.DATE, -10);
        oldestSprint.setStartDate(cal.getTime());
        oldestDate = oldestSprint.getStartDate(); 
        cal.setTime(oldestDate);
        cal.add(Calendar.DATE, 1);
        oldestSprint.setEndDate(cal.getTime());
        oldestSprint.addMember(new Member("member1"));

        // current sprint
        currentSprint = toolkit.getSprintFactory().createSprint();
        currentSprint.setTitle("Current sprint");
        currentSprint.addMember(new Member("member2"));

        // future sprint
        latestSprint = toolkit.getSprintFactory().createSprint();
        latestSprint.addMember(new Member("member3"));
        latestDate = latestSprint.getEndDate();
    }
    
    @Override
    public void tearDown(){
        toolkit.getSprintFactory().setCurrentSprintWeekCount(defaultCurrentSprintWeekCountValue);
        toolkit.getSprintFactory().disposeAll();
    }
    
    public SprintFactoryTest(String testName) {
        super(testName);
    }
       
    public void testCreateSprint() {
        System.out.println("SprintFactory.computeStartDate");
        
        Sprint newSprint = toolkit.getSprintFactory().createSprint();
        
        Calendar cal = toolkit.getPreparedCalendarInstance();
        cal.setTime(latestDate);
        cal.add(Calendar.DATE, 1);
        cal.setTime(toolkit.getNextWeekDayIfNotWeekDay(cal.getTime()));
        assertEquals(cal.getTime(), newSprint.getStartDate());
        cal.setTime(newSprint.getEndDate());
        cal.add(Calendar.DATE, 1);
        cal.setTime(toolkit.getNextWeekDayIfNotWeekDay(cal.getTime()));
        cal.add(Calendar.DATE, -14);
        assertEquals(cal.getTime(), newSprint.getStartDate());        
    }
    
    public void testFindTodaySprint() {
        System.out.println("SprintFactory.findTodaySprint");
        assertEquals(currentSprint, toolkit.getSprintFactory().findTodaySprint());
    }

    public void testGetMeanVelocity() {
        System.out.println("SprintFactory.getMeanVelocity");
        try{
            Date today = toolkit.getCurrentDate();
            assertEquals(0.0, toolkit.getSprintFactory().getMeanVelocity(oldestDate, today));

            Item item = new Item(toolkit);
            item.setEvaluation(FiboPoint.TWENTY_ONE);
            item.addVisa(new Item.Visa(Item.Status.EVALUATED, new Member("member1"), oldestDate));
            oldestSprint.addItem(item);
            assertEquals(0.0, toolkit.getSprintFactory().getMeanVelocity(oldestDate, today));

            item.addVisa(new Item.Visa(Item.Status.DONE, new Member("member2"), oldestDate));
            assertEquals(10.5, toolkit.getSprintFactory().getMeanVelocity(oldestDate, today));
        }catch(Exception e){
            fail(e.getMessage());
        }
    }

    public void testFindTopicalSprints() {
        System.out.println("SprintFactory.findTopicalSprints");
        assertEquals(2, toolkit.getSprintFactory().findTopicalSprints().size());
    }

    public void testFindLatestSprint() {
        System.out.println("SprintFactory.findLatestSprint");
        assertEquals(latestSprint, toolkit.getSprintFactory().findLatestSprint());
    }

    public void testFindNonTopicalSprints() {
        System.out.println("SprintFactory.findNonTopicalSprints");
        assertEquals(1, toolkit.getSprintFactory().findNonTopicalSprints().size());
    }
}
