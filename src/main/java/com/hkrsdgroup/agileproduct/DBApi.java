package com.hkrsdgroup.agileproduct;

import com.hkrsdgroup.agileproduct.beans.DayScheduleItemBean;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class DBApi extends DBConnect {
    public DBApi() { super(ResourceBundle.getBundle("app").getString("dsn")); }

    public DBApi(String dsn) { super(dsn); }

    public void initDB() {
        updateRawQuery("CREATE TABLE IF NOT EXISTS day_schedule_items (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE," +
                "activity VARCHAR(45)," +
                "time VARCHAR(5)," +
                "state BOOLEAN DEFAULT false);");
    }

    public void removeDailyScheduleFromDB(){
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getDataSource().getConnection();
            pstmt = conn.prepareStatement("DELETE FROM day_schedule_items;");
            pstmt.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                pstmt.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void resetIdDailyScheduleDB(){
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getDataSource().getConnection();
            pstmt = conn.prepareStatement("UPDATE SQLITE_SEQUENCE SET SEQ=0 WHERE NAME='day_schedule_items';");
            pstmt.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                pstmt.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<DayScheduleItemBean> retrieveDailyScheduleFromDB() {
        return this.getEntity("SELECT * FROM day_schedule_items;",
                new BeanListHandler<>(DayScheduleItemBean.class));
    }

    public void updateDailyItemState(int id, boolean state) {
        updateRawQuery(String.format("UPDATE day_schedule_items SET state = %b WHERE id = %d;", state, id));
    }
}
