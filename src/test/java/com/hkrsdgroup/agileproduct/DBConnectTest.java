package com.hkrsdgroup.agileproduct;

import com.hkrsdgroup.agileproduct.beans.DayScheduleItemBean;
import com.hkrsdgroup.agileproduct.beans.TestBean;
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

        // Clean up
        dropTestTable(dbc);
    }

    @Test
    @Order(3)
    void shouldGetEntity() {
        String assertVal = "Hallo world";
        DBConnect dbc = new DBConnect(rb.getString("dsn-test"));
        // init DB
        createTestTable(dbc);
        // inserting dummy data
        dbc.updateRawQuery("INSERT INTO testing (test_field) VALUES ('" + assertVal + "');");
        // Testing getEntity()
        TestBean tb = dbc.getEntity("SELECT * FROM testing LIMIT 1;", new BeanHandler<>(TestBean.class));
        assertEquals(assertVal, tb.getTest_field());
        // Clean up
        dropTestTable(dbc);
    }

    @Test
    @Order(4)
    void shouldGetEntityByArgs() {
        String assertVal = "Hallo world";
        DBConnect dbc = new DBConnect(rb.getString("dsn-test"));
        // init DB
        createTestTable(dbc);
        // inserting dummy data
        dbc.updateRawQuery("INSERT INTO testing (test_field) VALUES ('" + assertVal + "');");
        // Testing getEntity()
        TestBean tb = dbc.getEntity(
                "SELECT * FROM testing WHERE id = ? LIMIT 1;",
                new BeanHandler<>(TestBean.class),
                1);
        assertEquals(assertVal, tb.getTest_field());
        // Clean up
        dropTestTable(dbc);
    }

    @Test
    @Order(5)
    void shouldGetEntityList() {
        String assertVal1 = "Hallo world";
        String assertVal2 = "Bye world";
        DBConnect dbc = new DBConnect(rb.getString("dsn-test"));
        // init DB
        createTestTable(dbc);
        // inserting dummy data
        dbc.updateRawQuery("INSERT INTO testing (test_field) VALUES ('" + assertVal1 + "');");
        dbc.updateRawQuery("INSERT INTO testing (test_field) VALUES ('" + assertVal2 + "');");
        // Testing getEntity() List
        List<TestBean> tb = dbc.getEntity("SELECT * FROM testing;", new BeanListHandler<>(TestBean.class));
        assertEquals(assertVal1, tb.get(0).getTest_field());
        assertEquals(assertVal2, tb.get(1).getTest_field());

        // Clean up
        dropTestTable(dbc);
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
}
