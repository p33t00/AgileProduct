package com.hkrsdgroup.agileproduct;

import com.hkrsdgroup.agileproduct.beans.CourseScheduleTaskBean;
import com.hkrsdgroup.agileproduct.beans.DayScheduleItemBean;
import com.hkrsdgroup.agileproduct.beans.TaskBean;
import com.hkrsdgroup.agileproduct.beans.TmpCourseScheduleTaskBean;
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
    void retrieveCourseScheduleTaskFromDB() {
        DBApi dbc = new DBApi(rb.getString("dsn-test"));
        // init db
        dbc.initDBWeeklyOneTask();
        dbc.initDBCourseTask();

        List<CourseScheduleTaskBean> scheduleItems = new ArrayList<>();
        scheduleItems.add(new CourseScheduleTaskBean(1, "2022-05-14"));
        scheduleItems.add(new CourseScheduleTaskBean(2, "2022-05-15"));
        scheduleItems.add(new CourseScheduleTaskBean(3, "2022-05-16"));

//        scheduleItems.add(new CourseScheduleTaskBean(1, "2022-05-14",
//                new TaskBean("Math", "Hard", 220602, "Exam")));
//        scheduleItems.add(new CourseScheduleTaskBean(2, "2022-05-15",
//                new TaskBean("Agile", "Medium", 220529, "Final Report")));
//        scheduleItems.add(new CourseScheduleTaskBean(3, "2022-05-16",
//                new TaskBean("Databases", "Easy", 220615, "Homework")));

        dbc.insertWeeklyScheduleItems(scheduleItems);

        List<TmpCourseScheduleTaskBean> resultItems = dbc.test_retrieveCourseScheduleTaskFromDB();

        assertEquals(3, resultItems.size());

        assertEquals(scheduleItems.get(0).getTaskId(), resultItems.get(0).getTaskId());
        assertEquals(scheduleItems.get(1).getTaskId(), resultItems.get(1).getTaskId());
        assertEquals(scheduleItems.get(2).getTaskId(), resultItems.get(2).getTaskId());

//        assertEquals(scheduleItems.get(0).getTask().getCourse(), resultItems.get(0).getTask().getCourse());
//        assertEquals(scheduleItems.get(1).getTask().getCourse(), resultItems.get(1).getTask().getCourse());
//        assertEquals(scheduleItems.get(2).getTask().getCourse(), resultItems.get(2).getTask().getCourse());

        dropDayScheduleItemsTable(dbc);
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