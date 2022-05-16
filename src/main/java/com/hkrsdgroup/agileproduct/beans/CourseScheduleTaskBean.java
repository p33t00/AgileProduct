package com.hkrsdgroup.agileproduct.beans;

public class CourseScheduleTaskBean implements AgileBean{
    private final String table = "course_schedule_tasks";

    private int id = 0;
    private int taskId = 0;
    private String taskDate = null;
    private TaskBean task = null;

    @Override
    public String getTable() {
        return this.table;
    }

    public CourseScheduleTaskBean() {}

    public CourseScheduleTaskBean(int taskId, String taskDate, TaskBean task) {
        this.taskId = taskId;
        this.taskDate = taskDate;
        this.task = task;
    }

    public CourseScheduleTaskBean(int taskId, String taskDate) {
        this.taskId = taskId;
        this.taskDate = taskDate;
        this.task = new TaskBean();
    }

    public int getId() { return id; }
    public int getTaskId() { return taskId; }
    public TaskBean getTask() { return task; }
    public String getTaskDate() { return taskDate; }
    public String getCourse() { return task.getCourse(); }
    public String getTaskName() { return task.getTask(); }
    public String getDifficulty() { return task.getDifficulty(); }

    public void setId(int id) { this.id = id; }
    public void setTaskId(int taskId) { this.taskId = taskId; }
    public void setTaskDate(String taskDate) { this.taskDate = taskDate; }
    public void setTask(TaskBean task) { this.task = task; }
}
