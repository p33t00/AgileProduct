package com.hkrsdgroup.agileproduct;

import java.util.ArrayList;
import java.util.List;

public class dailySchedule {

    private int sleepAmount;
    private double startDay;
    private double endDay;
    private String assigment1;
    private String workout;
    private final double breakfast = 30;
    private final int lunch = 60;
    private final double shortSession = 30;
    private final int longSession = 120;
    private final double shortBreak = 5;
    private final double longBreak = 30;

    public dailySchedule(int sleep, String assignment, String workout, int beginDayHour){
        this.sleepAmount = sleep*60;
        this.assigment1 = assignment;
        this.workout = workout;
        this.startDay = beginDayHour;
        this.endDay = (24*60 + beginDayHour) - sleepAmount;
    }

    public String breakfast(){
        String start = converter(this.startDay) + " : Breakfast";
        this.startDay += breakfast;
        return start;
    }

    public String smallSession(){
        String smallStudySession = converter(this.startDay) + " : " +  this.assigment1;
        this.startDay += this.shortSession;
        return smallStudySession;
    }

    public String smallBreak(){
        String smallBreakTime = converter(this.startDay) + " : Small break";
        this.startDay += this.shortBreak;
        return smallBreakTime;
    }

    public String longBreak(){
        String longBreakTime = converter(this.startDay) + " : long break";
        this.startDay += this.longBreak;
        return longBreakTime;
    }

    public String longSession(){
        String longStudySession = converter(this.startDay) + " : " + this.assigment1;
        this.startDay += this.longSession;
        return longStudySession;
    }


    public String lunch(){
        String lunchTime = converter(this.startDay) + " : Lunch";
        this.startDay += this.lunch;
        return lunchTime;
    }

    public List<String> ScheduleDayOnlyShortSession(){
        List<String> daily = new ArrayList<String>();

        daily.add(breakfast());
        while(this.startDay < 12*60){
            daily.add(smallSession());
            daily.add(smallBreak());
        }
        daily.add(lunch());
        while(this.startDay < 16*60){
            daily.add(smallSession());
            daily.add(smallBreak());
        }
        daily.add(converter(this.endDay) + " : Goodnight!");
        return daily;
    }

    public String converter(double number){

        int hour = (int) (number / 60);
        int minutes = (int) (number % 60);

        String converted;

        if(minutes == 0){
            converted = Integer.toString(hour);
        }else{
            converted = hour + ":" + minutes;
        }

        return converted;
    }


    public double getStartDay() {
        return startDay;
    }

    public void setStartDay(int startDay) {
        this.startDay = startDay;
    }

    public void setSleepAmount(int sleepAmount) {
        this.sleepAmount = sleepAmount;
    }

    public void setAssigment1(String assigment1) {
        this.assigment1 = assigment1;
    }

    public void setWorkout(String workout) {
        this.workout = workout;
    }

    public int getSleepAmount() {
        return sleepAmount;
    }

    public String getAssigment1() {
        return assigment1;
    }

    public String getWorkout() {
        return workout;
    }

    public double getEndDay() {
        return endDay;
    }

    public void setStartDay(double startDay) {
        this.startDay = startDay;
    }

    public void setEndDay(double endDay) {
        this.endDay = endDay;
    }

    @Override
    public String toString() {
        return "dailySchedule{" +
                "sleepAmount=" + sleepAmount +
                ", startDay=" + startDay +
                ", assigment1='" + assigment1 + '\'' +
                ", workout='" + workout + '\'' +
                ", breakfast=" + breakfast +
                ", lunch=" + lunch +
                ", shortSession=" + shortSession +
                ", longSession=" + longSession +
                ", shortBreak=" + shortBreak +
                ", longBreak=" + longBreak +
                '}';
    }
}
