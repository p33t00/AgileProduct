package com.hkrsdgroup.agileproduct;

import com.hkrsdgroup.agileproduct.beans.DayScheduleItemBean;
import com.hkrsdgroup.agileproduct.beans.TaskBean;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

public class DBConnectTest {
    private final ResourceBundle rb = ResourceBundle.getBundle("app");

    @Test
    @Order(1)
    void shouldGetDataSource() {
        DBConnect dbc = new DBConnect(rb.getString("dsn-test"));
        SQLiteDataSource ds = dbc.getDataSource();
        assertInstanceOf(SQLiteDataSource.class, ds);
    }

    @Test
    @Order(2)
    void shouldRunUpdateQuery() throws SQLException {
        DBConnect dbc = new DBConnect(rb.getString("dsn-test"));
        // init DB
        createTestTable(dbc);

        Connection conn = dbc.getDataSource().getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT name FROM sqlite_schema WHERE type ='table' AND name = 'testing';");
        rs.next();
        assertEquals("testing", rs.getString("name"));
        rs.close();
        stmt.close();
        conn.close();

        // Clean up
        dropTestTable(dbc);
    }

    @Test
    @Order(4)
    void shouldGetEntityByArgs() {
        String assertVal = "Clean room";
        DBConnect dbc = new DBConnect(rb.getString("dsn-test"));
        // init DB
        createDayScheduleItemsTable(dbc);
        // inserting dummy data
        dbc.updateRawQuery("INSERT INTO day_schedule_items (activity, time) VALUES ('" + assertVal + "', '02:30');");
        // Testing getEntity()
        DayScheduleItemBean tb = dbc.getEntity(
                "SELECT * FROM day_schedule_items WHERE id = ? LIMIT 1;",
                new BeanHandler<>(DayScheduleItemBean.class),
                1);
        assertEquals(assertVal, tb.getActivity());
        // Clean up
        dropDayScheduleItemsTable(dbc);
    }

    @Test
    void shouldInsertDailyScheduleItems() {
        DBConnect dbc = new DBConnect(rb.getString("dsn-test"));
        List<DayScheduleItemBean> scheduleItems = new ArrayList<>();
        scheduleItems.add(new DayScheduleItemBean("Eat food", "01:20"));
        scheduleItems.add(new DayScheduleItemBean("Feed Cat", "02:30"));
        scheduleItems.add(new DayScheduleItemBean("Study Math", "03:00"));

        createDayScheduleItemsTable(dbc);
        dbc.insertDailyScheduleItems(scheduleItems);

        List<DayScheduleItemBean> resultItems = dbc.getEntity("SELECT * FROM day_schedule_items;",
                new BeanListHandler<>(DayScheduleItemBean.class));

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
    void retrieveCourseTaskFromDB() {
        DBConnect dbc = new DBConnect(rb.getString("dsn-test"));
        dropCourseTaskTable(dbc);

        TaskBean task = new TaskBean("Agile", "Medium", 220625, "Project1");
        TaskBean task1 = new TaskBean("Mathematics", "Hard", 2201009, "Examination 1");

        initDBCourseTask(dbc);
        dbc.insertTaskItems(task);
        dbc.insertTaskItems(task1);

        List<TaskBean> resultItems = dbc.getEntity("SELECT * FROM course_tasks;",
                new BeanListHandler<>(TaskBean.class));

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

    private void initDBCourseTask(DBConnect dbc) {
        dbc.updateRawQuery("CREATE TABLE IF NOT EXISTS course_tasks (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE," +
                "course VARCHAR(45)," +
                "task VARCHAR(45)," +
                "difficulty VARCHAR(45)," +
                "deadline INTEGER);");
    }

    private void createDayScheduleItemsTable(DBConnect dbc) {
        dbc.updateRawQuery("CREATE TABLE IF NOT EXISTS day_schedule_items (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE," +
                "activity VARCHAR(45)," +
                "time VARCHAR(5)," +
                "done TINYINT DEFAULT 0);");
    }

    private void createTestTable(DBConnect dbc) {
        dbc.updateRawQuery("CREATE TABLE IF NOT EXISTS testing (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE," +
                "test_field VARCHAR);");

    }

    private void dropTestTable(DBConnect dbc) {
        dbc.updateRawQuery("DROP TABLE IF EXISTS testing;");
    }

    private void dropDayScheduleItemsTable(DBConnect dbc) { dbc.updateRawQuery("DROP TABLE IF EXISTS day_schedule_items;"); }

    private void dropCourseTaskTable(DBConnect dbc) {
        dbc.updateRawQuery("DROP TABLE IF EXISTS course_tasks;");
    }
}
