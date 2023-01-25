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
import junit.framework.*;

public class ScrinchEnvToolkitTest extends TestCase {
    
    public ScrinchEnvToolkitTest(String testName) {
        super(testName);
    }
    
    private ScrinchEnvToolkit toolkit;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        toolkit = new ScrinchEnvToolkit();
    }

    public void testGetPreviousWeekDayIfNotWeekDay(){
        System.out.println("ScrinchEnvToolkit.getPreviousWeekDayIfNotWeekDay");
        Calendar cal = Calendar.getInstance();
        
        int weekDay = Calendar.MONDAY;
        cal.set(Calendar.DAY_OF_WEEK,weekDay);
        cal.setTime(toolkit.getPreviousWeekDayIfNotWeekDay(cal.getTime()));
        assertEquals(weekDay, cal.get(Calendar.DAY_OF_WEEK));
        
        weekDay = Calendar.TUESDAY;
        cal.set(Calendar.DAY_OF_WEEK,weekDay);
        cal.setTime(toolkit.getPreviousWeekDayIfNotWeekDay(cal.getTime()));
        assertEquals(weekDay, cal.get(Calendar.DAY_OF_WEEK));
    
        weekDay = Calendar.WEDNESDAY;
        cal.set(Calendar.DAY_OF_WEEK,weekDay);
        cal.setTime(toolkit.getPreviousWeekDayIfNotWeekDay(cal.getTime()));
        assertEquals(weekDay, cal.get(Calendar.DAY_OF_WEEK));
        
        weekDay = Calendar.THURSDAY;
        cal.set(Calendar.DAY_OF_WEEK,weekDay);
        cal.setTime(toolkit.getPreviousWeekDayIfNotWeekDay(cal.getTime()));
        assertEquals(weekDay, cal.get(Calendar.DAY_OF_WEEK));
        
        weekDay = Calendar.FRIDAY;
        cal.set(Calendar.DAY_OF_WEEK,weekDay);
        cal.setTime(toolkit.getPreviousWeekDayIfNotWeekDay(cal.getTime()));
        assertEquals(weekDay, cal.get(Calendar.DAY_OF_WEEK));
        
        weekDay = Calendar.SATURDAY;
        cal.set(Calendar.DAY_OF_WEEK,weekDay);
        cal.setTime(toolkit.getPreviousWeekDayIfNotWeekDay(cal.getTime()));
        assertEquals(Calendar.FRIDAY, cal.get(Calendar.DAY_OF_WEEK));
        
        weekDay = Calendar.SUNDAY;
        cal.set(Calendar.DAY_OF_WEEK,weekDay);
        cal.setTime(toolkit.getPreviousWeekDayIfNotWeekDay(cal.getTime()));
        assertEquals(Calendar.FRIDAY, cal.get(Calendar.DAY_OF_WEEK));
    }
    
    public void testGetNextWeekDayIfNotWeekDay(){
        System.out.println("ScrinchEnvToolkit.getNextWeekDayIfNotWeekDay");
        Calendar cal = Calendar.getInstance();
        
        int weekDay = Calendar.MONDAY;
        cal.set(Calendar.DAY_OF_WEEK,weekDay);
        cal.setTime(toolkit.getNextWeekDayIfNotWeekDay(cal.getTime()));
        assertEquals(weekDay, cal.get(Calendar.DAY_OF_WEEK));
        
        weekDay = Calendar.TUESDAY;
        cal.set(Calendar.DAY_OF_WEEK,weekDay);
        cal.setTime(toolkit.getNextWeekDayIfNotWeekDay(cal.getTime()));
        assertEquals(weekDay, cal.get(Calendar.DAY_OF_WEEK));
    
        weekDay = Calendar.WEDNESDAY;
        cal.set(Calendar.DAY_OF_WEEK,weekDay);
        cal.setTime(toolkit.getNextWeekDayIfNotWeekDay(cal.getTime()));
        assertEquals(weekDay, cal.get(Calendar.DAY_OF_WEEK));
        
        weekDay = Calendar.THURSDAY;
        cal.set(Calendar.DAY_OF_WEEK,weekDay);
        cal.setTime(toolkit.getNextWeekDayIfNotWeekDay(cal.getTime()));
        assertEquals(weekDay, cal.get(Calendar.DAY_OF_WEEK));
        
        weekDay = Calendar.FRIDAY;
        cal.set(Calendar.DAY_OF_WEEK,weekDay);
        cal.setTime(toolkit.getNextWeekDayIfNotWeekDay(cal.getTime()));
        assertEquals(weekDay, cal.get(Calendar.DAY_OF_WEEK));
        
        weekDay = Calendar.SATURDAY;
        cal.set(Calendar.DAY_OF_WEEK,weekDay);
        cal.setTime(toolkit.getNextWeekDayIfNotWeekDay(cal.getTime()));
        assertEquals(Calendar.MONDAY, cal.get(Calendar.DAY_OF_WEEK));
        
        weekDay = Calendar.SUNDAY;
        cal.set(Calendar.DAY_OF_WEEK,weekDay);
        cal.setTime(toolkit.getNextWeekDayIfNotWeekDay(cal.getTime()));
        assertEquals(Calendar.MONDAY, cal.get(Calendar.DAY_OF_WEEK));
    }
    
    public void testGetLatestDateAdjustedIfNotWeekDay(){
        System.out.println("ScrinchEnvToolkit.getLatestDateAdjustedIfNotWeekDay");
        
        Calendar cal = toolkit.getPreparedCalendarInstance();
        Date firstDate = cal.getTime();
        cal.add(Calendar.DATE, 1);
        Date secondDate = cal.getTime();
        
        while(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
            cal.add(Calendar.DATE, 1);
        }
        Date secondDateAdjustedToNextWeekDay = cal.getTime();
        
        assertEquals(secondDateAdjustedToNextWeekDay, toolkit.getLatestDateAdjustedIfNotWeekDay(firstDate, secondDate));
        assertEquals(secondDateAdjustedToNextWeekDay, toolkit.getLatestDateAdjustedIfNotWeekDay(secondDate, firstDate));
    }
    
    public void testGetBusinessDaysCount(){
        System.out.println("ScrinchEnvToolkit.getBusinessDaysCount");
        Calendar calendar = toolkit.getPreparedCalendarInstance();
        calendar.set(2007,0,1);
        Date date0 = calendar.getTime();
        calendar.set(2007,0,10);
        Date date1 = calendar.getTime();
        assertEquals(8,toolkit.getBusinessDaysCount(date0,date1));
        calendar.set(2007,0,6);
        date0 = calendar.getTime();
        calendar.set(2007,0,7);
        date1 = calendar.getTime();
        assertEquals(0,toolkit.getBusinessDaysCount(date0,date1));
        calendar.set(2007,0,8);
        date0 = calendar.getTime();
        calendar.set(2007,0,8);
        date1 = calendar.getTime();
        assertEquals(1,toolkit.getBusinessDaysCount(date0,date1));
    }
    
    public void testIsWeekDay(){
        System.out.println("ScrinchEnvToolkit.isWeekDay");
        Calendar calendar = toolkit.getPreparedCalendarInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        assertTrue(toolkit.isWeekDay(calendar.getTime()));
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
        assertTrue(toolkit.isWeekDay(calendar.getTime()));
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
        assertTrue(toolkit.isWeekDay(calendar.getTime()));
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
        assertTrue(toolkit.isWeekDay(calendar.getTime()));
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        assertTrue(toolkit.isWeekDay(calendar.getTime()));
        
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        assertFalse(toolkit.isWeekDay(calendar.getTime()));
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        assertFalse(toolkit.isWeekDay(calendar.getTime()));
    }
}
