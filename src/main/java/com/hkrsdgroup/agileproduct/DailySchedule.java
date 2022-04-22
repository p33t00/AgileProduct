package com.hkrsdgroup.agileproduct;

import com.hkrsdgroup.agileproduct.beans.DayScheduleItemBean;

import java.util.ArrayList;
import java.util.List;

public class DailySchedule {

    private int sleepAmount;
    private int clock;
    private int startDay;
    private int endDay;
    private String assignment1;
    private String workout;
    private final int breakfast = 30;
    private final int lunch = 60;
    private final int shortSession = 30;
    private final int longSession = 120;
    private final int shortBreak = 5;
    private final int longBreak = 30;

    public DailySchedule(int sleep, String assignment, String workout, int beginDayHour){
        this.sleepAmount = sleep*60;
        this.assignment1 = assignment;
        this.workout = workout;
        this.clock = beginDayHour;
        this.startDay = beginDayHour;
        this.endDay = (24*60 + beginDayHour) - sleepAmount;
    }

    public String breakfast(){
        String start = converter(this.clock) + " : Breakfast";
        this.clock += breakfast;
        return start;
    }

    public String smallSession(){
        String smallStudySession = converter(this.clock) + " : " +  this.assignment1;
        this.clock += this.shortSession;
        return smallStudySession;
    }

    public String smallBreak(){
        String smallBreakTime = converter(this.clock) + " : Small break";
        this.clock += this.shortBreak;
        return smallBreakTime;
    }

    public String longBreak(){
        String longBreakTime = converter(this.clock) + " : long break";
        this.clock += this.longBreak;
        return longBreakTime;
    }

    public String longSession(){
        String longStudySession = converter(this.clock) + " : " + this.assignment1;
        this.clock += this.longSession;
        return longStudySession;
    }


    public String lunch(){
        String lunchTime = converter(this.clock) + " : Lunch";
        this.clock += this.lunch;
        return lunchTime;
    }

    public List<String> ScheduleDayOnlyShortSession(){
        List<String> daily = new ArrayList<String>();

        daily.add(breakfast());
        while(this.clock < 12*60){
            daily.add(smallSession());
            daily.add(smallBreak());
        }
        daily.add(lunch());
        while(this.clock < 16*60){
            daily.add(smallSession());
            daily.add(smallBreak());
        }
        daily.add(converter(this.endDay) + " : Goodnight!");
        return daily;
    }

    public List<String> ScheduleDayOnlyLongSession(){
        List<String> daily = new ArrayList<String>();

        daily.add(breakfast());
        while(this.clock < 12*60){
            daily.add(longSession());
            daily.add(longBreak());
        }
        daily.add(lunch());
        while(this.clock < 16*60){
            daily.add(longSession());
            daily.add(longBreak());
        }
        daily.add(converter(this.endDay) + " : Goodnight!");
        return daily;
    }

    public List<String> ScheduleDayMixedSession(){
        List<String> daily = new ArrayList<String>();

        daily.add(breakfast());
        while(this.clock < 12*60){
            daily.add(longSession());
            daily.add(longBreak());
            daily.add(smallSession());
            daily.add(smallBreak());
        }
        daily.add(lunch());
        while(this.clock < 16*60){
            daily.add(longSession());
            daily.add(longBreak());
            daily.add(smallSession());
            daily.add(smallBreak());
        }
        daily.add(converter(this.endDay) + " : Goodnight!");
        return daily;
    }



    public String converter(int number){
        int hour = (number / 60);
        int minutes = (number % 60);
        String converted;

        if(hour > 23){
          hour =  hour - 24;
        }

        if(minutes < 10 && hour < 10){
            converted = "0" + hour + ":0" + minutes;
        }else if(minutes <10){
            converted = hour + ":0" + minutes;
        }else if(hour < 10){
            converted = "0" + hour + ":" + minutes;
        }else{
            converted = hour + ":"+ minutes;
        }

        return converted;
    }

    public int getClock() {
        return clock;
    }

    public int getStartDay() {
        return startDay;
    }

    public void setClock(int clock) {
        this.clock = clock * 60;
    }

    public void setSleepAmount(int sleepAmount) {
        this.sleepAmount = sleepAmount * 60;
    }

    public void setAssignment1(String assignment1) {
        this.assignment1 = assignment1;
    }

    public void setWorkout(String workout) {
        this.workout = workout;
    }

    public int getSleepAmount() {
        return sleepAmount;
    }

    public String getAssignment1() {
        return assignment1;
    }

    public String getWorkout() {
        return workout;
    }

    public int getEndDay() {
        return endDay;
    }

    public List<DayScheduleItemBean> getDayScheduleItems() {
        List<DayScheduleItemBean> scheduleItems = new ArrayList<>();
        scheduleItems.add(new DayScheduleItemBean("Eat food", "01:20"));
        scheduleItems.add(new DayScheduleItemBean("Feed Cat", "02:30"));
        scheduleItems.add(new DayScheduleItemBean("Study Math", "03:00"));
        return scheduleItems;
    }

    public void setEndDay(int endDay) {
        this.endDay = endDay;
    }

    @Override
    public String toString() {
        return "dailySchedule{" +
                "sleepAmount=" + sleepAmount +
                ", startDay=" + clock +
                ", assigment1='" + assignment1 + '\'' +
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
