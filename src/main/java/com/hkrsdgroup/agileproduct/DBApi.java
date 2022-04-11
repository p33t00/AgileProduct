package com.hkrsdgroup.agileproduct;

public class DBApi extends DBConnect {
    public DBApi(String dsn) { super(dsn); }

    public void initDB() {
        updateRawQuery("CREATE TABLE IF NOT EXISTS schedule (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE," +
                "date DATE," +
                "event VARCHAR(35)," +
                "status TINYINT DEFAULT 0);");
    }
}
