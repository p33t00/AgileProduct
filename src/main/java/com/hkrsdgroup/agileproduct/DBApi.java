package com.hkrsdgroup.agileproduct;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.QueryRunner;

public class DBApi extends DBConnect {
    public DBApi(String dsn) { super(dsn); }

    public void initDB() {

        Connection conn = null;
        String q = "CREATE TABLE IF NOT EXISTS schedule (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE," +
                "date DATE," +
                "event VARCHAR(35)," +
                "done TINYINT DEFAULT 0);";

        Statement stmt = null;
        try {
            conn = connect();
            stmt = conn.createStatement();
            stmt.executeUpdate(q);
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private <T> getEntity(String query, ResultSetHandler<T> handler, Object vararg) {
        T result = null;
        var run = QueryRunner(connect().getDataSource());
        try {
            result = if (vararg == null) run.query(query, handler) else run.query(query, handler, vararg)
        } catch(e: SQLException) {
            e.printStackTrace()
        }
        return result
    }
}
