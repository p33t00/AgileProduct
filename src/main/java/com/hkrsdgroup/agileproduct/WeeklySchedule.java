package com.hkrsdgroup.agileproduct;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;


public class WeeklySchedule {

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

    public ArrayList<List> CreateCompleteSchedule(WeeklySchedule courses, DailySchedule myDay){
        ArrayList<List> CompleteSchedule = new ArrayList<>();
        int DaysOnCourse = 0;

            while (courses.getDifficulty() != 0) {
                myDay.setAssignment1(courses.getCourseName());
                List<String> day = myDay.ScheduleDayMixedSession();
                CompleteSchedule.add(day);

                courses.setDifficulty(getDifficulty() - 1);
            }
        return CompleteSchedule;
    }


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
                " Course: " +courseName +
                " Difficulty: " + difficulty +
                " End of course: " + endDate;
    }
}
