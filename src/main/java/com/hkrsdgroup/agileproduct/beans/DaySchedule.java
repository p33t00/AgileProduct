package com.hkrsdgroup.agileproduct.beans;

public class DaySchedule implements AgileBean{
    private final String table = "day_schedule";
    private int id = 0;
    private String activity = null;
    private String time = null;

    public DaySchedule() {
    }

    public String getTable() {
        return this.table;
    }

    public DaySchedule(int id, String activity, String time) {
        this.id = id;
        this.activity = activity;
        this.time = time;
    }

    public int getId() {
        return id;
    }
    public String getActivity() {
        return activity;
    }
    public String getTime() { return time; }
    public void setId(int id) {
        this.id = id;
    }
    public void setActivity(String activity) {
        this.activity = activity;
    }
    public void setTime(String time) { this.time = time; }
}
