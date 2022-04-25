package com.hkrsdgroup.agileproduct;

import com.hkrsdgroup.agileproduct.beans.DayScheduleItemBean;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WeeklyScheduleTest {

    @Test
    void CreateWeeklyObject(){
        WeeklySchedule myWeek = new WeeklySchedule("agile", "medium", 203221);

        assertNotNull(myWeek);
    }

    @Test
    void SortCoursesOnEndDate(){
        WeeklySchedule myWeek = new WeeklySchedule("agile", "medium", 213221);
        WeeklySchedule myWeekTwo = new WeeklySchedule("agile", "medium", 203221);
        ArrayList<WeeklySchedule> myWeekList = new ArrayList<>();

        myWeekList = myWeek.sortAddOnEndDate(myWeekList, myWeek);
        myWeekList = myWeek.sortAddOnEndDate(myWeekList, myWeekTwo);

        assertEquals(203221, myWeekList.get(0).getEndDate());
    }

    @Test
    void AssignNumberToDifficulty(){
        WeeklySchedule myWeek = new WeeklySchedule("agile", "medium", 213221);

        int ShouldBeTwo = myWeek.AssignNumberToDifficulty("medium");

        assertEquals(2, ShouldBeTwo);
    }

    @Test
    void AssignCourseToDailySchedule(){
        WeeklySchedule myWeek = new WeeklySchedule("agile", "medium", 213221);
        int dayStartTime = (8 * 60) + 30;
        DailySchedule myDay = new DailySchedule(8, "gym", dayStartTime);

        ArrayList<List> schedule = myWeek.CreateCompleteSchedule(myWeek, myDay);

        assertEquals(2, schedule.size());
    }

}

