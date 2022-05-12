package com.hkrsdgroup.agileproduct.beans;


import javafx.beans.property.SimpleBooleanProperty;

public class WeeklyScheduleBean implements AgileBean{
    private final String table = "course_schedule_tasks";
    private String course_name = null;
    private String difficulty = null;
    private String date = null;
    private String task_name = null;
    private final SimpleBooleanProperty stateBox = new SimpleBooleanProperty();

    public WeeklyScheduleBean() {}

    @Override
    public String getTable() {
        return this.table;
    }

    public WeeklyScheduleBean(String course_name, String difficulty, String date, String task_name) {
        this.course_name = course_name;
        this.difficulty = difficulty;
        this.date = date;
        this.task_name = task_name;
    }

    public SimpleBooleanProperty getStateProperty() { return stateBox; }

    public String getCourse_name() {
        return course_name;
    }
    public String getDifficulty() {return difficulty;}
    public String getDate() {
        return date;
    }
    public String getTask_name() {
        return task_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

}
