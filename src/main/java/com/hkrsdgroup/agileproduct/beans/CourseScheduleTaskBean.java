package com.hkrsdgroup.agileproduct.beans;

public class CourseScheduleTaskBean implements AgileBean{
    private final String table = "course_schedule_tasks";
    private String course = null;
    private String difficulty = null;
    private String taskDate = null;
    private String task = null;

    public CourseScheduleTaskBean() {}

    @Override
    public String getTable() {
        return this.table;
    }

    public CourseScheduleTaskBean(String course, String difficulty, String taskDate, String task) {
        this.course = course;
        this.difficulty = difficulty;
        this.taskDate = taskDate;
        this.task = task;
    }

    public String getCourse() {
        return course;
    }
    public String getDifficulty() {
        return difficulty;
    }
    public String getTaskDate() {
        return taskDate;
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
    public void setTaskDate(String task_date) {
        this.taskDate = task_date;
    }
    public void setTask(String task) {
        this.task = task;
    }
}
