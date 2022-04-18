package com.hkrsdgroup.agileproduct;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class dailyScheduleTest {

    @Test
    void createDailyObject(){
        dailySchedule myDay = new dailySchedule(8,"course","gym",10, 30);

        assertNotNull(myDay);
    }

    @Test
    void testTimeConvert(){
        dailySchedule myDay = new dailySchedule(8,"course","gym",10, 30);
        int converted = (8 * 60) + 20;
        String time = myDay.converter(converted);

        assertEquals("8:20",time);
    }

    @Test
    void testBreakfast(){
        dailySchedule myDay = new dailySchedule(8,"course","gym",10, 30);
        String breakfast = myDay.breakfast();

        assertEquals("10:30 : Breakfast", breakfast);
    }

    @Test
    void testSmallSession(){
        dailySchedule myDay = new dailySchedule(8,"course","gym",10, 30);
        double originalTime = myDay.getStartDay();
        String smallSession = myDay.smallSession();

        assertEquals("10:30 : course", smallSession);
        assertNotEquals(originalTime, myDay.getStartDay());
    }

    @Test
    void GetSleep(){
        dailySchedule myDay = new dailySchedule(8,"course","gym",10, 30);

        assertEquals(8, myDay.getSleepAmount());
    }

    @Test
    void getStartDay(){
        dailySchedule myDay = new dailySchedule(8,"course","gym",10, 30);

        assertEquals(10, myDay.getStartDay());
    }

    @Test
    void getCourseName(){
        dailySchedule myDay = new dailySchedule(8,"course","gym",10, 30);

        assertEquals("course", myDay.getAssigment1());
    }

    @Test
    void getWorkout(){
        dailySchedule myDay = new dailySchedule(8,"course","gym",10, 30);

        assertEquals("gym", myDay.getWorkout());
    }

    @Test
    void getEndDay(){
        dailySchedule myDay = new dailySchedule(8,"course","gym",10, 30);

        assertEquals(2, myDay.getEndDay());
    }

    @Test
    void setStartDay(){
        dailySchedule myDay = new dailySchedule(8,"course","gym",10, 30);
        myDay.setStartDay(7);

        assertEquals(7, myDay.getStartDay());
    }

    @Test
    void setSleep(){
        dailySchedule myDay = new dailySchedule(8,"course","gym",10, 30);
        myDay.setSleepAmount(10);

        assertEquals(10, myDay.getSleepAmount());
    }

    @Test
    void setWorkout(){
        dailySchedule myDay = new dailySchedule(8,"course","gym",10, 30);
        myDay.setWorkout("boxing");

        assertEquals("boxing", myDay.getWorkout());
    }

    @Test
    void setAssignment(){
        dailySchedule myDay = new dailySchedule(8,"course","gym",10, 30);
        myDay.setAssigment1("database");

        assertEquals("database", myDay.getAssigment1());
    }
}
