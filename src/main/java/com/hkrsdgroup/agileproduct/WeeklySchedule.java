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

    public ArrayList<List> CreateCompleteSchedule(ArrayList<WeeklySchedule> courses, DailySchedule myDay){
        ArrayList<List> CompleteSchedule = new ArrayList<>();
        int DaysOnCourse = 0;

        //while(DaysOnCourse <= courses.size()){  //this fu**er doesn't work
            while (courses.get(DaysOnCourse).getDifficulty() != 0) { // this loop works without outer loop...
                myDay.setAssignment1(courses.get(DaysOnCourse).getCourseName());
                List<String> day = myDay.ScheduleDayMixedSession();
                CompleteSchedule.add(day);

                courses.get(DaysOnCourse).setDifficulty(getDifficulty() - 1);
                System.out.println("inner loop test");
            }
            //DaysOnCourse++;  // this creates infinite loop somehow
            //System.out.println("outer loop test");
        //}

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
