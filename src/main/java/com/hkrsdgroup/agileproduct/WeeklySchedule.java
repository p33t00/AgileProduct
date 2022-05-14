package com.hkrsdgroup.agileproduct;

import com.hkrsdgroup.agileproduct.beans.CourseScheduleTaskBean;
import com.hkrsdgroup.agileproduct.beans.TaskBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class WeeklySchedule {
    private List<String> difficultyLevels = List.of("Easy", "Medium", "Hard");

    public int difficultyToNumber(String difficulty){
        return difficultyLevels.indexOf(difficulty) + 1;
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

    public ArrayList<CourseScheduleTaskBean> createWeeklyOneTask(List<TaskBean> listOfTask){
        ArrayList<CourseScheduleTaskBean> completeScheduleOneTask = new ArrayList<>();
        String firstDate = todayDate();
        String nextDate = incrementDateOneDay(firstDate);

        for (TaskBean w: listOfTask) {
            for (int i = 0; i < difficultyToNumber(w.getDifficulty()); i++) {
                CourseScheduleTaskBean task = new CourseScheduleTaskBean(w.getId(), nextDate);
                completeScheduleOneTask.add(task);
                nextDate = incrementDateOneDay(nextDate);
            }
        }
        return completeScheduleOneTask;
    }
}
