package com.hkrsdgroup.agileproduct;

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
import java.util.List;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

public class DBConnectTest {
    private final ResourceBundle rb = ResourceBundle.getBundle("app");

    @Test
    @Order(1)
    void shouldGetDataSource() {
        DBConnect dbc = new DBConnect(rb.getString("dsn"));
        SQLiteDataSource ds = dbc.getDataSource();
        assertInstanceOf(SQLiteDataSource.class, ds);
    }

    @Test
    @Order(2)
    void shouldRunUpdateQuery() throws SQLException {
        DBConnect dbc = new DBConnect(rb.getString("dsn"));
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
        DBConnect dbc = new DBConnect(rb.getString("dsn"));
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
        DBConnect dbc = new DBConnect(rb.getString("dsn"));
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
        DBConnect dbc = new DBConnect(rb.getString("dsn"));
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

    private void createTestTable(DBConnect dbc) {
        String q = "CREATE TABLE IF NOT EXISTS testing (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE," +
                "test_field VARCHAR);";
        dbc.updateRawQuery(q);
    }

    private void dropTestTable(DBConnect dbc) {
        dbc.updateRawQuery("DROP TABLE IF EXISTS testing;");
    }
}
