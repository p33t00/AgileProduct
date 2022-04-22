package com.hkrsdgroup.agileproduct.beans;

public class DayScheduleItemBean implements AgileBean{
    private final String table = "day_schedule_items";
    private int id = 0;
    private String activity = null;
    private String time = null;
    private byte state = 0;

    public DayScheduleItemBean() {}

    public String getTable() {
        return this.table;
    }

    public DayScheduleItemBean(String activity, String time) {
        this.id = id;
        this.activity = activity;
        this.time = time;
        this.state = state;
    }

    public int getId() {
        return id;
    }
    public String getActivity() {
        return activity;
    }
    public String getTime() { return time; }
    public byte getState() { return state; }
    public void setId(int id) { this.id = id; }
    public void setActivity(String activity) {
        this.activity = activity;
    }
    public void setTime(String time) { this.time = time; }
    public void setState(byte state) { this.state = state; }
}
