package com.hkrsdgroup.agileproduct;

import com.hkrsdgroup.agileproduct.beans.DayScheduleItemBean;
import org.apache.commons.dbutils.handlers.BeanListHandler;

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
        updateRawQuery("DELETE FROM day_schedule_items;");
    }

    public void resetIdDailyScheduleDB(){
        updateRawQuery("UPDATE SQLITE_SEQUENCE SET SEQ=0 WHERE NAME='day_schedule_items';");
    }

    public List<DayScheduleItemBean> retrieveDailyScheduleFromDB() {
        return this.getEntity("SELECT * FROM day_schedule_items;",
                new BeanListHandler<>(DayScheduleItemBean.class));
    }

    public void updateDailyItemState(int id, boolean state) {
        updateRawQuery(String.format("UPDATE day_schedule_items SET state = %b WHERE id = %d;", state, id));
    }
}
