package com.hkrsdgroup.agileproduct;

import com.hkrsdgroup.agileproduct.beans.DayScheduleItemBean;

import java.util.List;

public class DBApi extends DBConnect {
    public DBApi(String dsn) { super(dsn); }

    public void initDB() {
        updateRawQuery("CREATE TABLE IF NOT EXISTS day_schedule_items (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE," +
                "activity VARCHAR(45)," +
                "time VARCHAR(5)," +
                "done TINYINT DEFAULT 0);");
    }
}
