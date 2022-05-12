package com.hkrsdgroup.agileproduct.beans;

public class TaskBean implements AgileBean{
    private final String table = "tasks";
    private String course_name = null;
    private String difficulty = null;
    private int deadline = 0;
    private String task_name = null;

    public TaskBean() {}

    @Override
    public String getTable() {
        return this.table;
    }

    public TaskBean(String course_name, String difficulty, int deadline, String task_name) {
        this.course_name = course_name;
        this.difficulty = difficulty;
        this.deadline = deadline;
        this.task_name = task_name;
    }

    public String getCourse_name() {
        return course_name;
    }
    public String getDifficulty() {
        return difficulty;
    }
    public int getDeadline() {
        return deadline;
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
    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }
    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }
}
