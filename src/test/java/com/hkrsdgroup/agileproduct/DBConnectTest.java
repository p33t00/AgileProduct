package com.hkrsdgroup.agileproduct;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

public class DBConnectTest {
    @Test
    public void shouldConnect() {
        ResourceBundle rb = ResourceBundle.getBundle("app");
        DBConnect dbc = new DBConnect(rb.getString("dsn"));
        Connection cnx = dbc.connect();
        assertInstanceOf(Connection.class, cnx);
    }
}
