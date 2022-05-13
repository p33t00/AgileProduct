package com.hkrsdgroup.agileproduct.beans;

public class TaskBean implements AgileBean{
    private final String table = "course_tasks";
    private String course = null;
    private String difficulty = null;
    private int deadline = 0;
    private String task = null;

    public TaskBean() {}

    @Override
    public String getTable() {
        return this.table;
    }

    public TaskBean(String course, String difficulty, int deadline, String task) {
        this.course = course;
        this.difficulty = difficulty;
        this.deadline = deadline;
        this.task = task;
    }

    public String getCourse() {
        return course;
    }
    public String getDifficulty() {
        return difficulty;
    }
    public int getDeadline() {
        return deadline;
    }
    public String getTask() {
        return task;
    }

    public void setCourse(String course) {
        this.course = course;
    }
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }
    public void setTask(String task) {
        this.task = task;
    }
}
