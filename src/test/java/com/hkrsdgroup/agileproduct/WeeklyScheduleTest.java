package com.hkrsdgroup.agileproduct;

import com.hkrsdgroup.agileproduct.beans.CourseScheduleTaskBean;
import com.hkrsdgroup.agileproduct.beans.TaskBean;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WeeklyScheduleTest {

    @Test
    void createWeeklyOneTask() {
        WeeklySchedule week = new WeeklySchedule();

        List<TaskBean> taskList = new ArrayList<>();
        taskList.add(new TaskBean("Agile", "Medium", 230514, "Project Work"));
        taskList.add(new TaskBean("Math", "Hard", 230614, "Assignment"));

        ArrayList<CourseScheduleTaskBean> completeSchedule = week.createWeeklyOneTask(taskList);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String today = formatter.format(date);

        Calendar c = Calendar.getInstance();
        try {
            c.setTime(formatter.parse(today));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        c.add(Calendar.DATE, 1);
        String nxtDay = formatter.format(c.getTime());
        c.add(Calendar.DATE, 1);
        String dayAfter = formatter.format(c.getTime());

        assertEquals(today, completeSchedule.get(0).getTaskDate());
        assertEquals(nxtDay, completeSchedule.get(1).getTaskDate());

    }

}
