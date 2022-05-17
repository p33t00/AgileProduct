package com.hkrsdgroup.agileproduct;

import com.hkrsdgroup.agileproduct.beans.CourseScheduleTaskBean;
import com.hkrsdgroup.agileproduct.beans.TaskBean;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WeeklyScheduleTest {

<<<<<<< HEAD
    @Test
    void todayDate() {
        WeeklySchedule week = new WeeklySchedule();
        String todayDate = week.todayDate();

        assertEquals("2022-05-14", todayDate);
   }

=======
>>>>>>> ab061a411bb9deebb25e07911481bda8c5d5638f

    @Test
    void createWeeklyOneTask() {
        WeeklySchedule week = new WeeklySchedule();

        List<TaskBean> taskList = new ArrayList<>();
        taskList.add(new TaskBean("Agile", "Medium", 230514, "Project Work"));
        taskList.add(new TaskBean("Math", "Hard", 230614, "Assignment"));

        ArrayList<CourseScheduleTaskBean> completeSchedule = week.createWeeklyOneTask(taskList);
        String today = week.incrementDateOneDay(week.todayDate());
        String nxtDay = week.incrementDateOneDay(today);

        assertEquals(today, completeSchedule.get(0).getTaskDate());
        assertEquals(nxtDay, completeSchedule.get(1).getTaskDate());

    }

}
