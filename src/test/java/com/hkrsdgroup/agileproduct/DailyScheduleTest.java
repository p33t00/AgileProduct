package com.hkrsdgroup.agileproduct;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DailyScheduleTest {

    @Test
    void createDailyObject(){
        int dayStartTime = (8 * 60) + 30;
        DailySchedule myDay = new DailySchedule(8,"course","gym",dayStartTime);

        assertNotNull(myDay);
    }

    @Test
    void testTimeConvert(){
        int dayStartTime = (8 * 60) + 30;
        DailySchedule myDay = new DailySchedule(8,"course","gym",dayStartTime);
        String time = myDay.converter(myDay.getStartDay());

        assertEquals("08:30",time);
    }

    @Test
    void testBreakfast(){
        int dayStartTime = (8 * 60) + 30;
        DailySchedule myDay = new DailySchedule(8,"course","gym",dayStartTime);
        String breakfast = myDay.breakfast();

        assertEquals("08:30 : Breakfast", breakfast);
    }

    @Test
    void testSmallSession(){
        int dayStartTime = (8 * 60) + 30;
        DailySchedule myDay = new DailySchedule(8,"course","gym",dayStartTime);
        double originalTime = myDay.getStartDay();
        String smallSession = myDay.smallSession();

        assertEquals("08:30 : course", smallSession);
        assertNotEquals(originalTime, myDay.getStartDay());
    }

    @Test
    void GetSleep(){
        int dayStartTime = (8 * 60) + 30;
        DailySchedule myDay = new DailySchedule(8,"course","gym",dayStartTime);

        assertEquals(8*60, myDay.getSleepAmount());
    }

    @Test
    void getStartDay(){
        int dayStartTime = (8 * 60) + 30;
        DailySchedule myDay = new DailySchedule(8,"course","gym",dayStartTime);

        assertEquals(dayStartTime, myDay.getStartDay());
    }

    @Test
    void getCourseName(){
        int dayStartTime = (8 * 60) + 30;
        DailySchedule myDay = new DailySchedule(8,"course","gym",dayStartTime);

        assertEquals("course", myDay.getAssigment1());
    }

    @Test
    void getWorkout(){
        int dayStartTime = (8 * 60) + 30;
        DailySchedule myDay = new DailySchedule(8,"course","gym",dayStartTime);

        assertEquals("gym", myDay.getWorkout());
    }

    @Test
    void getEndDay(){
        int dayStartTime = (8 * 60) + 30;
        DailySchedule myDay = new DailySchedule(8,"course","gym",dayStartTime);

        assertEquals("00:30", myDay.converter(myDay.getEndDay()));
    }

    @Test
    void setStartDay(){
        int dayStartTime = (8 * 60) + 30;
        DailySchedule myDay = new DailySchedule(8,"course","gym",dayStartTime);
        myDay.setStartDay(7);

        assertEquals(7*60, myDay.getStartDay());
    }

    @Test
    void setSleep(){
        int dayStartTime = (8 * 60) + 30;
        DailySchedule myDay = new DailySchedule(8,"course","gym",dayStartTime);
        myDay.setSleepAmount(10);

        assertEquals(10*60, myDay.getSleepAmount());
    }

    @Test
    void setWorkout(){
        int dayStartTime = (8 * 60) + 30;
        DailySchedule myDay = new DailySchedule(8,"course","gym",dayStartTime);
        myDay.setWorkout("boxing");

        assertEquals("boxing", myDay.getWorkout());
    }

    @Test
    void setAssignment(){
        int dayStartTime = (8 * 60) + 30;
        DailySchedule myDay = new DailySchedule(8,"course","gym",dayStartTime);
        myDay.setAssigment1("database");

        assertEquals("database", myDay.getAssigment1());
    }
}
