package com.hkrsdgroup.agileproduct;

import com.hkrsdgroup.agileproduct.beans.CourseScheduleTaskBean;
import com.hkrsdgroup.agileproduct.beans.DayScheduleItemBean;
import com.hkrsdgroup.agileproduct.beans.TaskBean;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class DBApiTest {
    private final ResourceBundle rb = ResourceBundle.getBundle("app");

    @Test
    void retrieveDailyScheduleFromDB() {
        DBApi dbc = new DBApi(rb.getString("dsn-test"));
        List<DayScheduleItemBean> scheduleItems = new ArrayList<>();
        scheduleItems.add(new DayScheduleItemBean("Eat food", "01:20"));
        scheduleItems.add(new DayScheduleItemBean("Feed Cat", "02:30"));
        scheduleItems.add(new DayScheduleItemBean("Study Math", "03:00"));

        createDayScheduleItemsTable(dbc);
        dbc.insertDailyScheduleItems(scheduleItems);

        List<DayScheduleItemBean> resultItems = dbc.retrieveDailyScheduleFromDB();


        assertEquals(3, resultItems.size());

        assertEquals(scheduleItems.get(0).getActivity(), resultItems.get(0).getActivity());
        assertEquals(scheduleItems.get(1).getActivity(), resultItems.get(1).getActivity());
        assertEquals(scheduleItems.get(2).getActivity(), resultItems.get(2).getActivity());

        assertEquals(scheduleItems.get(0).getTime(), resultItems.get(0).getTime());
        assertEquals(scheduleItems.get(1).getTime(), resultItems.get(1).getTime());
        assertEquals(scheduleItems.get(2).getTime(), resultItems.get(2).getTime());

        dropDayScheduleItemsTable(dbc);
    }

    @Test
    void retrieveCourseScheduleTaskFromDB(){
        DBApi dbc = new DBApi(rb.getString("dsn-test"));
        dbc.removeWeeklyScheduleFromDB();
        ArrayList<WeeklySchedule> myCourses = new ArrayList<>();
        ArrayList<ArrayList> complete = new ArrayList<>();

        WeeklySchedule course = new WeeklySchedule("database","Easy",220622,"assignment1");
        WeeklySchedule course2 = new WeeklySchedule("agile","Easy",220723,"assignment2");
        myCourses.add(course);
        myCourses.add(course2);
        complete.add(myCourses);
        ArrayList<ArrayList> completed = course.createWeeklyOneTask(myCourses);

        dbc.initDBWeeklyOneTask();
        dbc.insertWeeklyScheduleItems(completed);

        List<CourseScheduleTaskBean> resultItems = dbc.retrieveCourseScheduleTaskFromDB();

        assertEquals(2, resultItems.size());
        assertEquals(completed.get(0).get(0), resultItems.get(0).getTaskDate());
        assertEquals(completed.get(0).get(1), resultItems.get(0).getCourse());
        assertEquals(completed.get(0).get(2), resultItems.get(0).getTask());
        assertEquals(completed.get(0).get(3), resultItems.get(0).getDifficulty());


        assertEquals(completed.get(1).get(0), resultItems.get(1).getTaskDate());
        assertEquals(completed.get(1).get(1), resultItems.get(1).getCourse());
        assertEquals(completed.get(1).get(2), resultItems.get(1).getTask());
        assertEquals(completed.get(1).get(3), resultItems.get(1).getDifficulty());
    }

    private void createWeeklyScheduleItemsTable(DBConnect dbc) {
        dbc.updateRawQuery("CREATE TABLE IF NOT EXISTS course_schedule_tasks (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE," +
                "taskDate VARCHAR(45)," +
                "course VARCHAR(45)," +
                "task VARCHAR(45)," +
                "difficulty VARCHAR(45));");
    }


    private void createDayScheduleItemsTable(DBConnect dbc) {
        dbc.updateRawQuery("CREATE TABLE IF NOT EXISTS day_schedule_items (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE," +
                "activity VARCHAR(45)," +
                "time VARCHAR(5)," +
                "done TINYINT DEFAULT 0);");
    }

    private void dropDayScheduleItemsTable(DBConnect dbc) {
        dbc.updateRawQuery("DROP TABLE IF EXISTS day_schedule_items;");
    }

}