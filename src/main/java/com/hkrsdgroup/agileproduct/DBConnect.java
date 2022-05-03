package com.hkrsdgroup.agileproduct;

import com.hkrsdgroup.agileproduct.beans.DayScheduleItemBean;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

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



    public void insertDailyScheduleItems(List<DayScheduleItemBean> scheduleItems) {
        Connection conn = null;
        try {
            conn = getDataSource().getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO day_schedule_items (activity, time) VALUES (?,?);");
            conn.setAutoCommit(false);

            for (DayScheduleItemBean i : scheduleItems) {
                stmt.setString(1, i.getActivity());
                stmt.setString(2, i.getTime());
                stmt.addBatch();
            }

            stmt.executeBatch();
            conn.commit();
        } catch (BatchUpdateException b) {
            System.err.println("Butch insert error.");
            b.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }






}
