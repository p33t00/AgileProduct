package com.hkrsdgroup.agileproduct;

import com.hkrsdgroup.agileproduct.beans.DayScheduleItemBean;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;
import java.util.ResourceBundle;

public class DBApi extends DBConnect {
    public DBApi(String dsn) { super(dsn); }

    public void initDB() {
        updateRawQuery("CREATE TABLE IF NOT EXISTS day_schedule_items (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE," +
                "activity VARCHAR(45)," +
                "time VARCHAR(5)," +
                "done TINYINT DEFAULT 0);");
    }

    public void truncateDailySchedule(){
        updateRawQuery("TRUNCATE TABLE IF EXISTS day_schedule_items;");
    }

    public List<DayScheduleItemBean> retrieveDailyScheduleFromDB() {
        ResourceBundle rb = ResourceBundle.getBundle("app");
        DBApi dbc = new DBApi(rb.getString("dsn"));

        List<DayScheduleItemBean> resultItems = dbc.getEntity("SELECT * FROM day_schedule_items;",
                new BeanListHandler<>(DayScheduleItemBean.class));

        return resultItems;
    }

}
