package com.hkrsdgroup.agileproduct;

import com.hkrsdgroup.agileproduct.beans.DayScheduleItemBean;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

public class DailyScheduleTest {
    private final ResourceBundle rb = ResourceBundle.getBundle("app");

    @Test
    void createDailyObject(){
        DailySchedule myDay = new DailySchedule(8, "agile","gym", 8, 30);

        assertNotNull(myDay);
    }

    @Test
    void testTimeConvert(){
        DailySchedule myDay = new DailySchedule(8, "agile","gym", 8, 30);
        String time = myDay.converter(myDay.getClock());

        assertEquals("08:30",time);
    }

    @Test
    void testBreakfast(){
        DailySchedule myDay = new DailySchedule(8, "agile","gym", 8, 30);
        String breakfast = myDay.breakfast();

        assertEquals("08:00 - Breakfast", breakfast);
    }

    @Test
    void testSmallSession(){
        DailySchedule myDay = new DailySchedule(8, "agile","gym", 8, 30);
        int originalTime = myDay.getClock();
        myDay.setAssignment1("course");
        String smallSession = myDay.smallSession();

        assertEquals("08:30 - course", smallSession);
        assertNotEquals(originalTime, myDay.getClock());
    }

    @Test
    void testLongSession(){
        DailySchedule myDay = new DailySchedule(8, "agile","gym", 8, 30);
        int originalTime = myDay.getClock();
        myDay.setAssignment1("course");
        String longSession = myDay.longSession();

        assertEquals("08:30 - course", longSession);
        assertNotEquals(originalTime, myDay.getClock());
    }

    @Test
    void GetSleep(){
        DailySchedule myDay = new DailySchedule(8, "agile","gym", 8, 30);

        assertEquals(8*60, myDay.getSleepAmount());
    }

    @Test
    void getClock(){
        DailySchedule myDay = new DailySchedule(8, "agile","gym", 8, 30);

        assertEquals((8*60)+30, myDay.getClock());
    }

    @Test
    void getSelfActivity(){
        DailySchedule myDay = new DailySchedule(8, "agile","gym", 8, 30);

        assertEquals("gym", myDay.getSelfActivity());
    }

    @Test
    void getEndDay(){
        DailySchedule myDay = new DailySchedule(8, "agile","gym", 8, 30);

        assertEquals("00:00", myDay.converter(myDay.getEndDay()));
    }

    @Test
    void setStartDay(){
        DailySchedule myDay = new DailySchedule(8, "agile","gym", 8, 30);
        myDay.setClock(7);

        assertEquals(7*60, myDay.getClock());
    }

    @Test
    void setSleep(){
        DailySchedule myDay = new DailySchedule(8, "agile","gym", 8, 30);
        myDay.setSleepAmount(10);

        assertEquals((10*60), myDay.getSleepAmount());
    }

    @Test
    void setSelfActivity(){
        DailySchedule myDay = new DailySchedule(8, "agile","gym", 8, 30);
        myDay.setSelfActivity("boxing");

        assertEquals("boxing", myDay.getSelfActivity());
    }

    @Test
    void setAssignment(){
        DailySchedule myDay = new DailySchedule(8, "agile","gym", 8, 30);
        myDay.setAssignment1("database");

        assertEquals("database", myDay.getAssignment1());
    }

    @Test
    void setEndDay(){
        DailySchedule myDay = new DailySchedule(8, "agile","gym", 8, 30);

        myDay.setEndDay(7);
        assertEquals(7, myDay.getEndDay());
    }

    @Test
    void getStartDay(){
        DailySchedule myDay = new DailySchedule(8, "agile","gym", 8, 30);

        assertEquals((8*60)+30, myDay.getStartDay());
    }

    @Test
    void calculateTimeConstructor(){
        DailySchedule myDay = new DailySchedule(8, "agile","gym", 8, 30);

        int timeActual = myDay.calculateConstructorTime(8,30);
        int shouldBe = (8*60) + 30;
        assertEquals(shouldBe, timeActual);
    }

    @Test
    void featureShortSessions01(){
        DailySchedule myDay = new DailySchedule(8, "agile","gym", 8, 30);
        List<String> shortSchedule = myDay.ScheduleDayOnlyShortSession();

        assertEquals("08:00 - Breakfast", shortSchedule.get(0));
        assertEquals("12:00 - Lunch", shortSchedule.get(13));
        assertEquals("00:00 - Goodnight!", shortSchedule.get(26));
    }

    @Test
    void featureMixSessions02(){
        int strings = 19;
        DailySchedule myDay = new DailySchedule(8, "agile","gym", 8, 30);
        List<String> mixSchedule = myDay.ScheduleDayMixedSession();

        assertEquals(strings, mixSchedule.size());
        assertEquals("08:00 - Breakfast", mixSchedule.get(0));
        assertEquals("15:15 - agile", mixSchedule.get(14));
    }

    @Test
    void featureLongSessions03(){
        int strings = 17;
        DailySchedule myDay = new DailySchedule(8, "agile","gym", 8, 30);
        List<String> longSchedule = myDay.ScheduleDayOnlyLongSession();

        assertEquals(strings, longSchedule.size());
        assertEquals("08:00 - Breakfast", longSchedule.get(0));
        assertEquals("13:30 - agile", longSchedule.get(7));
        assertEquals("00:00 - Must sleep to get the required sleep!", longSchedule.get(16));
    }

    @Test
    void sendDailyToDB(){
        DBApi dbc = new DBApi(rb.getString("dsn-test"));
        dbc.initDB();
        DailySchedule myDay = new DailySchedule(8,"agile","yoga",8,30);
        myDay.setDBC(dbc);
        myDay.ScheduleDayMixedSession(); // sends to test DB

        dbc.removeDailyScheduleFromDB();
        dbc.resetIdDailyScheduleDB();
    }
}
