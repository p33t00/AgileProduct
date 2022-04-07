package com.hkrsdgroup.agileproduct;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class DBApiTest {
    @Test
    public void shouldConnect() {
        ResourceBundle rb = ResourceBundle.getBundle("app");
        DBConnect dba = new DBApi(rb.getString("dsn"));
        Connection cnx = dba.connect();
        assertInstanceOf(Connection.class, cnx);
    }


}