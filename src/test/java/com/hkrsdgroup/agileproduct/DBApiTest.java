package com.hkrsdgroup.agileproduct;

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

        dbc.initDB();
        dbc.insertDailyScheduleItems(scheduleItems);

        List<DayScheduleItemBean> resultItems = dbc.retrieveDailyScheduleFromDB();

        assertEquals(3, resultItems.size());

        assertEquals(scheduleItems.get(0).getActivity(), resultItems.get(0).getActivity());
        assertEquals(scheduleItems.get(1).getActivity(), resultItems.get(1).getActivity());
        assertEquals(scheduleItems.get(2).getActivity(), resultItems.get(2).getActivity());

        assertEquals(scheduleItems.get(0).getTime(), resultItems.get(0).getTime());
        assertEquals(scheduleItems.get(1).getTime(), resultItems.get(1).getTime());
        assertEquals(scheduleItems.get(2).getTime(), resultItems.get(2).getTime());

//        dropDayScheduleItemsTable(dbc);
        dbc.removeDailyScheduleFromDB();
    }

    @Test
    void retrieveCourseTaskFromDB() {
        DBApi dbc = new DBApi(rb.getString("dsn-test"));
        dropCourseTaskTable(dbc);

        TaskBean task = new TaskBean("Agile", "Medium", 220625, "Project1");
        TaskBean task1 = new TaskBean("Mathematics", "Hard", 2201009, "Examination 1");

        dbc.initDBCourseTask();
        dbc.insertTaskItems(task);
        dbc.insertTaskItems(task1);

        List<TaskBean> resultItems = dbc.retrieveCourseTaskFromDB();

        assertEquals(2, resultItems.size());

        assertEquals(task.getCourse(), resultItems.get(0).getCourse());
        assertEquals(task.getDeadline(), resultItems.get(0).getDeadline());
        assertEquals(task.getDifficulty(), resultItems.get(0).getDifficulty());
        assertEquals(task.getTask(), resultItems.get(0).getTask());

        assertEquals(task1.getCourse(), resultItems.get(1).getCourse());
        assertEquals(task1.getDeadline(), resultItems.get(1).getDeadline());
        assertEquals(task1.getDifficulty(), resultItems.get(1).getDifficulty());
        assertEquals(task1.getTask(), resultItems.get(1).getTask());
    }

//    private void createDayScheduleItemsTable(DBConnect dbc) {
//        dbc.updateRawQuery("CREATE TABLE IF NOT EXISTS day_schedule_items (" +
//                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE," +
//                "activity VARCHAR(45)," +
//                "time VARCHAR(5)," +
//                "done TINYINT DEFAULT 0);");
//    }

//    private void dropDayScheduleItemsTable(DBConnect dbc) {
//        dbc.updateRawQuery("DROP TABLE IF EXISTS day_schedule_items;");
//    }

//    public void createCourseTaskTable(DBConnect dbc){
//        dbc.updateRawQuery("CREATE TABLE IF NOT EXISTS course_tasks (" +
//                "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE," +
//                "course VARCHAR(45)," +
//                "task VARCHAR(45)," +
//                "difficulty VARCHAR(45)," +
//                "deadline INTEGER);");
//    }

    private void dropCourseTaskTable(DBConnect dbc) {
        dbc.updateRawQuery("DROP TABLE IF EXISTS course_tasks;");
    }

}