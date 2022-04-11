package com.hkrsdgroup.agileproduct;

import com.hkrsdgroup.agileproduct.beans.TestBean;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        TestBean tb = dbc.getEntity("SELECT * FROM testing", new BeanHandler<TestBean>(TestBean.class));
        assertEquals(assertVal, tb.getTest_field());
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
