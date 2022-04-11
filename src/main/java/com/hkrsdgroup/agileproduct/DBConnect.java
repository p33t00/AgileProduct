package com.hkrsdgroup.agileproduct;

import org.apache.commons.dbutils.ResultSetHandler;
import org.sqlite.SQLiteDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbutils.QueryRunner;

public class DBConnect {
    final private String dsn;

    public DBConnect(String dsn) {
        this.dsn = dsn;
    }

    public SQLiteDataSource getDataSource() {
        SQLiteDataSource ds = new SQLiteDataSource();
        ds.setUrl(dsn);
        return ds;
    }

    public final <T> T getEntity(String query, ResultSetHandler<T> handler, Object... vArgs) {
        T result = null;
        var run = new QueryRunner(getDataSource());
        try {
            if (vArgs.length == 0) {
                result = run.query (query, handler);
            } else {
                result = run.query(query, handler, vArgs);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int updateRawQuery(String q) {
        Connection conn = null;
        Statement stmt = null;
        int result = 0;
        try {
            conn = getDataSource().getConnection();
            stmt = conn.createStatement();
            result = stmt.executeUpdate(q);
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
        return result;
    }
}
