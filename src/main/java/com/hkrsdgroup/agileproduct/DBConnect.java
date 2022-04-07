package com.hkrsdgroup.agileproduct;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    private static Connection instance = null;

    public static Connection getConnection(String dsn) {
        if (instance == null) {
            instance = new DBConnect().connect(dsn);
        }
        return instance;
    }

    private Connection connect(String dsn) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dsn);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }
}
