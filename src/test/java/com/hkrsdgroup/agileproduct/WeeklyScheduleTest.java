package com.hkrsdgroup.agileproduct;

import com.hkrsdgroup.agileproduct.beans.DayScheduleItemBean;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class WeeklyScheduleTest {
/*
    @Test
    void courseRemoveDay(){
        WeeklySchedule myCourse = new WeeklySchedule("agile", "hard", 220321, "learn jira");

        int shouldBe3 = myCourse.getDifficultyNumber();
        myCourse.CourseToString();
        int shouldBe2 = myCourse.getDifficultyNumber();

        assertNotEquals(shouldBe3, shouldBe2);
    }

    @Test
    void sortCoursesOnEndDate(){
        ArrayList<WeeklySchedule> myWeek = new ArrayList<>();

        WeeklySchedule myCourse = new WeeklySchedule("agile", "hard", 220321, "learn jira");
        myCourse.sortAddOnEndDate(myWeek, myCourse);
        WeeklySchedule myCourse2 = new WeeklySchedule("Database", "medium", 240321, "connection");
        myCourse.sortAddOnEndDate(myWeek, myCourse2);
        WeeklySchedule myCourse3 = new WeeklySchedule("python", "easy", 210532, "learn functional programming");
        myCourse.sortAddOnEndDate(myWeek, myCourse3);

        assertEquals("python", myWeek.get(0).getCourseName());
        assertEquals("agile", myWeek.get(1).getCourseName());
        assertEquals("Database", myWeek.get(2).getCourseName());
    }

    @Test
    void createWeekScheduleOneTask(){
        ArrayList<ArrayList> completeWeek = new ArrayList<>();

        WeeklySchedule myCourse = new WeeklySchedule("agile", "Hard", 220321, "learn jira");
        myCourse.sortAddOnEndDate(myCourse.getMyWeekList(), myCourse);
        WeeklySchedule myCourse2 = new WeeklySchedule("Database", "Medium", 240321, "assignment connect");
        myCourse.sortAddOnEndDate(myCourse.getMyWeekList(), myCourse2);
        WeeklySchedule myCourse3 = new WeeklySchedule("python", "Easy", 210532, "make clean code");
        myCourse.sortAddOnEndDate(myCourse.getMyWeekList(), myCourse3);
        WeeklySchedule myCourse4 = new WeeklySchedule("python", "Easy", 210532, "implement functions");
        myCourse.sortAddOnEndDate(myCourse.getMyWeekList(), myCourse4);

        String firstDateShouldBe = myCourse.incrementDateOneDay(myCourse.todayDate());
        String firstTaskShouldBe = "python";

        completeWeek = myCourse.createWeeklyOneTask(myCourse.getMyWeekList());

        assertEquals(firstDateShouldBe, completeWeek.get(0).get(0));
        assertEquals(firstTaskShouldBe, completeWeek.get(0).get(1));
    }

    @Test
    void checkCurrentDate(){
        WeeklySchedule myCourse = new WeeklySchedule("Database", "medium", 240321, " create connection");
        String actualDate = myCourse.todayDate();
        String [] dates = actualDate.split("-");

        assertEquals(4,dates[0].length());
        assertEquals(2,dates[1].length());
        assertEquals(2,dates[2].length());
    }

    @Test
    void checkDateShouldBeTomorrow(){
        WeeklySchedule myCourse = new WeeklySchedule("Database", "medium", 240321, " create connection");
        String tomorrowDate = myCourse.incrementDateOneDay(myCourse.todayDate());
        String [] dates = tomorrowDate.split("-");

        assertEquals(4,dates[0].length());
        assertEquals(2,dates[1].length());
        assertEquals(2,dates[2].length());
    }

    @Test
    void checkSecondCourse(){
        ArrayList<WeeklySchedule> myWeek = new ArrayList<>();
        String test = null;

        WeeklySchedule myCourse = new WeeklySchedule("agile", "Hard", 220321, "learn jira");
        myCourse.sortAddOnEndDate(myWeek, myCourse);
        WeeklySchedule myCourse2 = new WeeklySchedule("Database", "Medium", 240321, "assignment connect");
        myCourse.sortAddOnEndDate(myWeek, myCourse2);
        WeeklySchedule myCourse3 = new WeeklySchedule("python", "Easy", 210532, "make clean code");
        myCourse.sortAddOnEndDate(myWeek, myCourse3);
        WeeklySchedule myCourse4 = new WeeklySchedule("python", "Easy", 210532, "implement functions");
        myCourse.sortAddOnEndDate(myWeek, myCourse4);

        test = myCourse.retrieveSecondCourseForDay(myWeek);
        assertEquals("agile", test);
    }

    @Test
    void checkSecondTask(){
        ArrayList<WeeklySchedule> myWeek = new ArrayList<>();
        String test = null;

        WeeklySchedule myCourse = new WeeklySchedule("agile", "Hard", 220321, "learn jira");
        myCourse.sortAddOnEndDate(myWeek, myCourse);
        WeeklySchedule myCourse2 = new WeeklySchedule("Database", "Medium", 240321, "assignment connect");
        myCourse.sortAddOnEndDate(myWeek, myCourse2);
        WeeklySchedule myCourse3 = new WeeklySchedule("python", "Easy", 210532, "make clean code");
        myCourse.sortAddOnEndDate(myWeek, myCourse3);
        WeeklySchedule myCourse4 = new WeeklySchedule("python", "Easy", 210532, "implement functions");
        myCourse.sortAddOnEndDate(myWeek, myCourse4);

        test = myCourse.retrieveSecondTaskForDay(myWeek);
        assertEquals(test, "learn jira");
    }

    @Test
    void checkCompleteWeekWithTwoTasksADay(){
        ArrayList<WeeklySchedule> myWeek = new ArrayList<>();
        ArrayList<ArrayList> completeWeek = new ArrayList<>();

        WeeklySchedule myCourse = new WeeklySchedule("agile", "Hard", 220321, "learn jira");
        myCourse.sortAddOnEndDate(myWeek, myCourse);
        WeeklySchedule myCourse2 = new WeeklySchedule("Database", "Medium", 210321, "assignment connect");
        myCourse.sortAddOnEndDate(myWeek, myCourse2);
        WeeklySchedule myCourse3 = new WeeklySchedule("python", "Easy", 210532, "make clean code");
        myCourse.sortAddOnEndDate(myWeek, myCourse3);
        WeeklySchedule myCourse4 = new WeeklySchedule("python", "Easy", 230532, "implement functions");
        myCourse.sortAddOnEndDate(myWeek, myCourse4);

        completeWeek = myCourse.createWeeklyTwoTasks(myWeek);
        String todayDate = myCourse.todayDate();
        String shouldBeDate = myCourse.incrementDateOneDay(todayDate);

        assertEquals(completeWeek.get(0).get(2),"assignment connect");
        assertEquals(completeWeek.get(0).get(5), "agile");
        assertEquals(completeWeek.get(0).get(1),shouldBeDate);
    }

 */
}

