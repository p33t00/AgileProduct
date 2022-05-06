package com.hkrsdgroup.agileproduct.beans;

import javafx.beans.property.SimpleBooleanProperty;

public class DayScheduleItemBean implements AgileBean{
    private final String table = "day_schedule_items";
    private int id = 0;
    private String activity = null;
    private String time = null;
    private boolean state = false;
    private final SimpleBooleanProperty stateBox = new SimpleBooleanProperty();

    public DayScheduleItemBean() {}

    public String getTable() {
        return this.table;
    }

    public DayScheduleItemBean(String activity, String time) {
        this.activity = activity;
        this.time = time;
    }

    public DayScheduleItemBean(Integer id,String activity, String time, boolean state) {
        this.id = id;
        this.activity = activity;
        this.time = time;
        this.stateBox.setValue(true);
    }

    public int getId() { return id; }
    public String getActivity() { return activity; }
    public String getTime() { return time; }
    public SimpleBooleanProperty getStateProperty() { return stateBox; }
    public void setId(int id) { this.id = id; }
    public void setActivity(String activity) { this.activity = activity; }
    public void setTime(String time) { this.time = time; }
    public void setState(boolean state) {
        this.stateBox.setValue(state);
        this.state = state;
    }
    public void setStateProperty(boolean state) {
        this.stateBox.setValue(state);
        this.state = state;
    }
}
