package com.hkrsdgroup.agileproduct;

import java.util.ArrayList;
import java.util.Comparator;


public class weeklySchedule {

    private String courseName;
    private String difficulty;
    private int endDate;

    public weeklySchedule(String courseName, String difficulty, int endDate){
        this.courseName = courseName;
        this.difficulty = difficulty;
        this.endDate = endDate;
    }


    public ArrayList<weeklySchedule> sortAddOnPriority(ArrayList<weeklySchedule> myWeekList, weeklySchedule course){
        myWeekList.add(course);
        myWeekList.sort(Comparator.comparing(weeklySchedule::getEndDate));
        return myWeekList;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public int getEndDate() {
        return endDate;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setEndDate(int endDate) {
        this.endDate = endDate;
    }



    @Override
    public String toString() {
        return
                " Course: " +courseName +
                " Difficulty: " + difficulty +
                " End of course: " + endDate;
    }
}
