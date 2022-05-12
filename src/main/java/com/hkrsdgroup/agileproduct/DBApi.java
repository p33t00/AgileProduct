package com.hkrsdgroup.agileproduct;

import com.hkrsdgroup.agileproduct.beans.DayScheduleItemBean;
import com.hkrsdgroup.agileproduct.beans.TaskBean;
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

    public void initDBWeeklyOneTask(){
        updateRawQuery("CREATE TABLE IF NOT EXISTS course_schedule_tasks (" +
                "dayID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE," +
                "dayCount VARCHAR(5)," +
                "date VARCHAR(15)," +
                "course VARCHAR(45)," +
                "assignment VARCHAR(45)," +
                "doneOrNot BOOLEAN DEFAULT false);");
    }

    public void initDBCourseTask(){
        updateRawQuery("CREATE TABLE IF NOT EXISTS course_tasks (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE," +
                "course VARCHAR(45)," +
                "task VARCHAR(45)," +
                "difficulty VARCHAR(45)," +
                "deadline INTEGER);");
    }

    public void removeWeeklyScheduleFromDB(){
        updateRawQuery("DELETE FROM course_schedule_tasks;");
    }

    public void resetIdWeeklyScheduleDB(){
        updateRawQuery("UPDATE SQLITE_SEQUENCE SET SEQ=0 WHERE NAME='course_schedule_tasks';");
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

    public List<TaskBean> retrieveCourseTaskFromDB() {
        return this.getEntity("SELECT * FROM course_tasks;",
                new BeanListHandler<>(TaskBean.class));
    }

    public void updateDailyItemState(int id, boolean state) {
        updateRawQuery(String.format("UPDATE day_schedule_items SET state = %b WHERE id = %d;", state, id));
    }
}
