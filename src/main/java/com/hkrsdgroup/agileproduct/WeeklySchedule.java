package com.hkrsdgroup.agileproduct;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;


public class WeeklySchedule {
    private final ResourceBundle rb = ResourceBundle.getBundle("app");

    private String courseName;
    private String assignment;
    private int difficultyNumber;
    private int endDate;
    private String difficultyName;

    public WeeklySchedule(String courseName, String difficulty, int endDate, String assignment){
        this.courseName = courseName;
        this.difficultyNumber = AssignNumberToDifficulty(difficulty);
        this.difficultyName = difficulty;
        this.endDate = endDate;
        this.assignment = assignment;
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

    public String CourseToString(){
        this.difficultyNumber = this.difficultyNumber - 1;
        return this.courseName;
    }

    public String todayDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return formatter.format(date);
    }

    public String incrementDateOneDay(String inputDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(inputDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 1);
        return sdf.format(c.getTime());
    }

    // feature 1
    public ArrayList<ArrayList> createWeeklyOneTask(ArrayList<WeeklySchedule> listOfCourses){
        ArrayList<ArrayList> completeScheduleOneTask = new ArrayList<>();
        int day = 1;
        int innerLoop = 0;
        String firstDate = todayDate();
        String nextDate = incrementDateOneDay(firstDate);

        while ( innerLoop < listOfCourses.size()){
            while(listOfCourses.get(innerLoop).difficultyNumber >= 1){
                ArrayList<String> oneDay = new ArrayList<>();
                oneDay.add("day" + day);
                oneDay.add(nextDate);
                oneDay.add(listOfCourses.get(innerLoop).CourseToString());
                oneDay.add(listOfCourses.get(innerLoop).getAssignment());
                completeScheduleOneTask.add(oneDay);
                nextDate = incrementDateOneDay(nextDate);
                day++;
            }
            innerLoop++;
        }
        return completeScheduleOneTask;
    }

    //feature 2
    public ArrayList<ArrayList> createWeeklyTwoTasks(ArrayList<WeeklySchedule> listOfCourses){

        incrementDaysForTwoTaskSchedule(listOfCourses);
        ArrayList<ArrayList> completeWeek = new ArrayList<>();
        int day = 1;
        int innerLoop = 0;
        String firstDate = todayDate();
        String nextDate = incrementDateOneDay(firstDate);

        while ( innerLoop < listOfCourses.size()){
            while(listOfCourses.get(innerLoop).difficultyNumber >= 1){
                ArrayList<String> oneDay = new ArrayList<>();
                oneDay.add("day" + day);
                oneDay.add(nextDate);
                oneDay.add(listOfCourses.get(innerLoop).getAssignment());
                oneDay.add(listOfCourses.get(innerLoop).CourseToString());
                oneDay.add(retrieveSecondTaskForDay(listOfCourses));
                oneDay.add(retrieveSecondCourseForDay(listOfCourses));
                completeWeek.add(oneDay);
                nextDate = incrementDateOneDay(nextDate);
                day++;
            }
            innerLoop++;
        }
        return completeWeek;
    }

    public String retrieveSecondCourseForDay(ArrayList<WeeklySchedule> listOfCourses){
        String secondCourse = null;

        for (WeeklySchedule listOfCourse : listOfCourses) {
            if (listOfCourse.difficultyNumber > 0 &&
                    Objects.equals(listOfCourse.difficultyName, "hard")) {
                secondCourse = listOfCourse.CourseToString();
            }
        }
        if (secondCourse == null){
            for(int i = 0; i < listOfCourses.size();i++){
                if( listOfCourses.get(i).difficultyNumber > 0 &&
                        Objects.equals(listOfCourses.get(i).difficultyName, "medium")){
                    secondCourse = listOfCourses.get(i).CourseToString();
                }
            }
        }
        if (secondCourse == null){
            for(int i = 0; i < listOfCourses.size();i++){
                if( listOfCourses.get(i).difficultyNumber > 0 &&
                        Objects.equals(listOfCourses.get(i).difficultyName, "easy")){
                    secondCourse = listOfCourses.get(i).CourseToString();
                }
            }
        }
        return secondCourse;
    }


    public String retrieveSecondTaskForDay(ArrayList<WeeklySchedule> listOfCourses) {

        String secondCourse = null;
        for (int i = 0; i < listOfCourses.size(); i++) {
            if (listOfCourses.get(i).difficultyNumber > 0 &&
                    Objects.equals(listOfCourses.get(i).difficultyName, "hard")) {
                secondCourse = listOfCourses.get(i).getAssignment();
            }
        }
            if (secondCourse == null) {
                for (int i = 0; i < listOfCourses.size(); i++) {
                    if (listOfCourses.get(i).difficultyNumber > 0 &&
                            Objects.equals(listOfCourses.get(i).difficultyName, "medium")) {
                        secondCourse = listOfCourses.get(i).getAssignment();
                    }
                }
            }
            if (secondCourse == null) {
                for (int i = 0; i < listOfCourses.size(); i++) {
                    if (listOfCourses.get(i).difficultyNumber > 0 &&
                            Objects.equals(listOfCourses.get(i).difficultyName, "easy")) {
                        secondCourse = listOfCourses.get(i).getAssignment();
                    }
                }
            }
            return secondCourse;
    }

    public void incrementDaysForTwoTaskSchedule(ArrayList<WeeklySchedule> listOfCourses){
        for(int i = 0; i < listOfCourses.size();i++){
            listOfCourses.get(i).difficultyNumber = listOfCourses.get(i).difficultyNumber * 2;
        }
    }



    public String getCourseName() {
        return courseName;
    }

    public int getDifficultyNumber() {
        return difficultyNumber;
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

    public void setDifficulty(int difficulty) { this.difficultyNumber = difficulty; }

    public String getAssignment() {
        return assignment;
    }

    public void setAssignment(String assignment) {
        this.assignment = assignment;
    }

    public String getDifficultyName() {
        return difficultyName;
    }

    @Override
    public String toString() {
        return courseName + " " + difficultyNumber + " " +
                endDate + " " + difficultyName;
    }
}
