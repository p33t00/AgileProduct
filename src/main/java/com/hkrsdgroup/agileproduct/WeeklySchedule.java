package com.hkrsdgroup.agileproduct;

import com.hkrsdgroup.agileproduct.beans.DayScheduleItemBean;

import java.util.*;


public class WeeklySchedule {
    private final ResourceBundle rb = ResourceBundle.getBundle("app");

    private String courseName;
    private int difficulty;
    private int endDate;

    public WeeklySchedule(String courseName, String difficulty, int endDate){
        this.courseName = courseName;
        this.difficulty = AssignNumberToDifficulty(difficulty);
        this.endDate = endDate;
    }

    public ArrayList<WeeklySchedule> sortAddOnEndDate(ArrayList<WeeklySchedule> myWeekList, WeeklySchedule course){
        myWeekList.add(course);
        myWeekList.sort(Comparator.comparing(WeeklySchedule::getEndDate));
        return myWeekList;
    }

    public int AssignNumberToDifficulty(String difficulty){
        int difficultyInDays = 0;

        if(Objects.equals(difficulty, "hard")){
            difficultyInDays = 3;
        }else if(Objects.equals(difficulty, "medium")){
            difficultyInDays = 2;
        }else if(Objects.equals(difficulty, "easy")){
            difficultyInDays = 1;
        }
        return difficultyInDays;
    }

    /*
    public void sendCoursesToDB(WeeklySchedule course){
        DBApi dbc = new DBApi(rb.getString("dsn"));
        dbc.insertWeeklyScheduleItems(course);
    }

     */

    public String getCourseName() {
        return courseName;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getEndDate() {
        return endDate;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setEndDate(int endDate) {
        this.endDate = endDate;
    }

    public void setDifficulty(int difficulty) { this.difficulty = difficulty; }

    @Override
    public String toString() {
        return
                courseName + difficulty + endDate;
    }
}
