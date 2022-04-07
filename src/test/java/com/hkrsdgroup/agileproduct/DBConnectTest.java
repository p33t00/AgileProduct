package com.hkrsdgroup.agileproduct;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

public class DBConnectTest {
    @Test
    public void shouldConnect() {
        ResourceBundle rb = ResourceBundle.getBundle("app");
        Connection cnx = DBConnect.getConnection(rb.getString("dsn"));
        assertInstanceOf(Connection.class, cnx);
    }

    @Test
    public void shouldConnectExactlyOnce() {
        ResourceBundle rb = ResourceBundle.getBundle("app");
        Connection cnx = DBConnect.getConnection(rb.getString("dsn"));
        Connection cnxSame = DBConnect.getConnection(rb.getString("dsn"));

        assertSame(cnx, cnxSame);
    }

}
