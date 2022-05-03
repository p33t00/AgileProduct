package com.hkrsdgroup.agileproduct;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner myScan = new Scanner(System.in);
        Main myMain = new Main();
        // DBApi myConnection = new DBApi("dsn");

        DailySchedule myDay = null;
        WeeklySchedule myWeek = null;
        List<String> mySchedule = null;
        ArrayList<WeeklySchedule> myWeekList = new ArrayList<>();
        int choice = 1337;
        while(choice != 0) {
            myMain.menu();
            choice = myScan.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("how many hours to sleep?");
                    int sleepTime = myScan.nextInt();
                    System.out.println("what are you studying?");
                    String lecture = myScan.next();
                    System.out.println("your type of workout");
                    String workout = myScan.next();
                    System.out.println("What hour to start?");
                    int dayStartHour = myScan.nextInt();
                    System.out.println("What minute to start?");
                    int dayStartMin = myScan.nextInt();

                    myDay = new DailySchedule(sleepTime, lecture, workout, dayStartHour, dayStartMin);

                    break;
                case 2:
                    /*
                    System.out.println("Course name:");
                    String course = myScan.next();
                    System.out.println("How difficult is it? easy/medium/hard");
                    String diffLevel = myScan.next();
                    System.out.println("When does course end? ex:220421");
                    int endDate = myScan.nextInt();

                    myWeek = new WeeklySchedule(course, diffLevel, endDate);
                    myWeekList = myWeek.sortAddOnEndDate(myWeekList, myWeek);

                     */
                    break;
                case 3:
                    System.out.println("Select schedule options:");
                    System.out.println("1) for short sessions");
                    System.out.println("2) for mixed sessions");
                    System.out.println("3) for long sessions");

                    int scheduleOption = myScan.nextInt();
                    switch (scheduleOption){
                        case 1:
                            mySchedule = myDay.ScheduleDayOnlyShortSession();
                            break;
                        case 2:
                           mySchedule = myDay.ScheduleDayMixedSession();
                            break;
                        case 3:
                           mySchedule = myDay.ScheduleDayOnlyLongSession();
                            break;
                    }
                    for(String s : mySchedule){
                        System.out.println(s);
                    }
                    break;
                case  4:
                    /*
                    for (WeeklySchedule weeklySchedule : myWeekList) {
                        System.out.println(weeklySchedule);
                    }

                     */
                    break;
            }
        }
    }

    public void menu(){
        System.out.println("1. enter profile info");
        System.out.println("2. Enter weekly info");
        System.out.println("3. Select daily schedule type");
        System.out.println("4. see weekly schedule");
        System.out.println("0. to end");
    }

}
