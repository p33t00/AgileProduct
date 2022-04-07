package com.hkrsdgroup.agileproduct;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    final private String dsn;

    public DBConnect(String dsn) {
        this.dsn = dsn;
    }
    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dsn);
        } catch (SQLException e) {
            System.err.println("DB Connections error:");
            e.printStackTrace();
            System.exit(0);
        }
        return conn;
    }
}
