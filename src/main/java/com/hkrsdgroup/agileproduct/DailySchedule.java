package com.hkrsdgroup.agileproduct;

import com.hkrsdgroup.agileproduct.beans.DayScheduleItemBean;

import java.util.ArrayList;
import java.util.List;

public class DailySchedule {
    private DBApi dbc = new DBApi();

    private int sleepAmount;

    private int clock;
    private int startDay;
    private int endDay;

    private String assignment1;
    private String selfActivity;
    private final int mealGap = 4;
    private final int afterLunchStudy = 5;
    private final int breakfast = 30;
    private final int lunch = 60;
    private final int shortSession = 30;
    private final int longSession = 60;
    private final int shortBreak = 5;
    private final int longBreak = 20;


    public DailySchedule(int sleep,String course ,String selfActivity, int hour, int minutes){
        this.sleepAmount = sleep*60;
        this.selfActivity = selfActivity;
        this.clock = calculateConstructorTime(hour,minutes);
        this.startDay = calculateConstructorTime(hour, minutes);
        this.endDay = (24*60 + calculateConstructorTime(hour, minutes)) - sleepAmount- breakfast;
        this.assignment1 = course;
    }

    public String breakfast(){
        int breakFastTime = this.clock - breakfast;
        String start = converter(breakFastTime) + " - Breakfast";
        return start;
    }

    public String smallSession(){
        String smallStudySession = converter(this.clock) + " - " +  this.assignment1;
        this.clock += this.shortSession;
        return smallStudySession;
    }

    public String smallBreak(){
        String smallBreakTime = converter(this.clock) + " - Small break";
        this.clock += this.shortBreak;
        return smallBreakTime;
    }

    public String longBreak(){
        String longBreakTime = converter(this.clock) + " - break";
        this.clock += this.longBreak;
        return longBreakTime;
    }

    public String longSession(){
        String longStudySession = converter(this.clock) + " - " + this.assignment1;
        this.clock += this.longSession;
        return longStudySession;
    }

    public String lunch(){
        String lunchTime = converter(this.clock) + " - Lunch";
        this.clock += this.lunch;
        return lunchTime;
    }

    public String free(){
        String freeTime = converter(this.clock) + "- Done for the day";
        return freeTime;
    }

    public String activity(){
        String selfActivity = converter(this.clock) + "- Activity that you like";
        this.clock += 60;
        return selfActivity;
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
        daily.add(converter(this.endDay) + " - Goodnight!");
        this.clock = startDay;

        return daily;
    }

    public List<String> ScheduleDayOnlyLongSession(){
        List<String> daily = new ArrayList<String>();

        daily.add(breakfast());
        while(this.clock < this.startDay  +(mealGap*60)){
            daily.add(longSession());
            daily.add(longBreak());
        }
        String temp = daily.get(daily.size()-1);
        if(temp.matches("(.*)break")){
            daily.set(daily.size()-1, lunch());
        }
        else {
            daily.add(lunch());
        }
        int secondHalf = this.clock + (afterLunchStudy*60);
        while(this.clock < secondHalf) {

            daily.add(longSession());
            daily.add(longBreak());
        }
        String temp1 = daily.get(daily.size()-1);
        if(temp1.matches("(.*)break")){
            daily.set(daily.size()-1, activity());
        }
        else {
            daily.add(activity());
        }
        daily.add(free());
        daily.add(converter(this.endDay) + " - Must sleep to get the required sleep!");
        this.clock = startDay;

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
        daily.add(converter(this.endDay) + " - Goodnight!");
        this.clock = startDay;

        return daily;
    }

    public String converter(int number){
        int hour = (number / 60);
        int minutes = (number % 60);
        if(hour > 23){
          hour =  hour - 24;
        }

        return String.format("%02d:%02d", hour, minutes);
    }

    public int calculateConstructorTime(int hour, int minutes){
        return (hour * 60) + minutes;
    }

    public void sendDailyScheduleToDB(List<String> schedule){
        List<DayScheduleItemBean> scheduleItems = new ArrayList<>();

        for(int i = 0; i < schedule.size();i++){
            String timeAndActivity = schedule.get(i);
            String[] splitTimeAndActivity = timeAndActivity.split("-");

            scheduleItems.add(new DayScheduleItemBean(splitTimeAndActivity[1], splitTimeAndActivity[0]));
        }

        dbc.insertDailyScheduleItems(scheduleItems);
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

    public void setSelfActivity(String selfActivity) {
        this.selfActivity = selfActivity;
    }

    public int getSleepAmount() {
        return sleepAmount;
    }

    public String getAssignment1() {
        return assignment1;
    }

    public String getSelfActivity() {
        return selfActivity;
    }

    public int getEndDay() {
        return endDay;
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
                ", workout='" + selfActivity + '\'' +
                ", breakfast=" + breakfast +
                ", lunch=" + lunch +
                ", shortSession=" + shortSession +
                ", longSession=" + longSession +
                ", shortBreak=" + shortBreak +
                ", longBreak=" + longBreak +
                '}';
    }
}
