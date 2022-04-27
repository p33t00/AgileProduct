package com.hkrsdgroup.agileproduct;

import com.hkrsdgroup.agileproduct.beans.DayScheduleItemBean;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DailyScheduleTest {

    @Test
    void createDailyObject(){
        int dayStartTime = (8 * 60) + 30;
        DailySchedule myDay = new DailySchedule(8, "agile","gym",dayStartTime);

        assertNotNull(myDay);
    }

    @Test
    void testTimeConvert(){
        int dayStartTime = (8 * 60) + 30;
        DailySchedule myDay = new DailySchedule(8, "agile","gym",dayStartTime);
        String time = myDay.converter(myDay.getClock());

        assertEquals("08:30",time);
    }

    @Test
    void testBreakfast(){
        int dayStartTime = (8 * 60) + 30;
        DailySchedule myDay = new DailySchedule(8, "agile","gym",dayStartTime);
        String breakfast = myDay.breakfast();

        assertEquals("08:30 - Breakfast", breakfast);
    }

    @Test
    void testSmallSession(){
        int dayStartTime = (8 * 60) + 30;
        DailySchedule myDay = new DailySchedule(8, "agile","gym",dayStartTime);
        int originalTime = myDay.getClock();
        myDay.setAssignment1("course");
        String smallSession = myDay.smallSession();

        assertEquals("08:30 - course", smallSession);
        assertNotEquals(originalTime, myDay.getClock());
    }

    @Test
    void testLongSession(){
        int dayStartTime = (8 * 60) + 30;
        DailySchedule myDay = new DailySchedule(8, "agile","gym",dayStartTime);
        int originalTime = myDay.getClock();
        myDay.setAssignment1("course");
        String longSession = myDay.longSession();

        assertEquals("08:30 - course", longSession);
        assertNotEquals(originalTime, myDay.getClock());
    }

    @Test
    void GetSleep(){
        int dayStartTime = (8 * 60) + 30;
        DailySchedule myDay = new DailySchedule(8, "agile","gym",dayStartTime);

        assertEquals(8*60, myDay.getSleepAmount());
    }

    @Test
    void getClock(){
        int dayStartTime = (8 * 60) + 30;
        DailySchedule myDay = new DailySchedule(8, "agile","gym",dayStartTime);

        assertEquals(dayStartTime, myDay.getClock());
    }

    @Test
    void getWorkout(){
        int dayStartTime = (8 * 60) + 30;
        DailySchedule myDay = new DailySchedule(8, "agile","gym",dayStartTime);

        assertEquals("gym", myDay.getWorkout());
    }

    @Test
    void getEndDay(){
        int dayStartTime = (8 * 60) + 30;
        DailySchedule myDay = new DailySchedule(8, "agile","gym",dayStartTime);

        assertEquals("00:30", myDay.converter(myDay.getEndDay()));
    }

    @Test
    void getDayScheduleItems(){
        DailySchedule myDay = new DailySchedule(8, "agile","gym",510);

        assertEquals(3, myDay.getDayScheduleItems().size());
        assertInstanceOf(DayScheduleItemBean.class, myDay.getDayScheduleItems().get(0));
        assertInstanceOf(DayScheduleItemBean.class, myDay.getDayScheduleItems().get(1));
        assertInstanceOf(DayScheduleItemBean.class, myDay.getDayScheduleItems().get(2));
    }

    @Test
    void setStartDay(){
        int dayStartTime = (8 * 60) + 30;
        DailySchedule myDay = new DailySchedule(8, "agile","gym",dayStartTime);
        myDay.setClock(7);

        assertEquals(7*60, myDay.getClock());
    }

    @Test
    void setSleep(){
        int dayStartTime = (8 * 60) + 30;
        DailySchedule myDay = new DailySchedule(8, "agile","gym",dayStartTime);
        myDay.setSleepAmount(10);

        assertEquals(10*60, myDay.getSleepAmount());
    }

    @Test
    void setWorkout(){
        int dayStartTime = (8 * 60) + 30;
        DailySchedule myDay = new DailySchedule(8, "agile","gym",dayStartTime);
        myDay.setWorkout("boxing");

        assertEquals("boxing", myDay.getWorkout());
    }

    @Test
    void setAssignment(){
        int dayStartTime = (8 * 60) + 30;
        DailySchedule myDay = new DailySchedule(8, "agile","gym",dayStartTime);
        myDay.setAssignment1("database");

        assertEquals("database", myDay.getAssignment1());
    }

    @Test
    void setEndDay(){
        int dayStartTime = (8 * 60) + 30;
        DailySchedule myDay = new DailySchedule(8, "agile","gym",dayStartTime);

        myDay.setEndDay(7);
        assertEquals(7, myDay.getEndDay());
    }

    @Test
    void getStartDay(){
        int dayStartTime = (8 * 60) + 30;
        DailySchedule myDay = new DailySchedule(8, "agile","gym",dayStartTime);

        assertEquals(dayStartTime, myDay.getStartDay());
    }
}
