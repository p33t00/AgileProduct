package com.hkrsdgroup.agileproduct;

import com.hkrsdgroup.agileproduct.beans.CourseScheduleTaskBean;
import com.hkrsdgroup.agileproduct.beans.DayScheduleItemBean;
import com.hkrsdgroup.agileproduct.beans.TaskBean;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class DBApiTest {
    private final ResourceBundle rb = ResourceBundle.getBundle("app");

    @Test
    void retrieveDailyScheduleFromDB() {
        DBApi dbc = new DBApi(rb.getString("dsn-test"));
        List<DayScheduleItemBean> scheduleItems = new ArrayList<>();
        scheduleItems.add(new DayScheduleItemBean("Eat food", "01:20"));
        scheduleItems.add(new DayScheduleItemBean("Feed Cat", "02:30"));
        scheduleItems.add(new DayScheduleItemBean("Study Math", "03:00"));

        dbc.initDB();
        dbc.insertDailyScheduleItems(scheduleItems);

        List<DayScheduleItemBean> resultItems = dbc.retrieveDailyScheduleFromDB();

        assertEquals(3, resultItems.size());

        assertEquals(scheduleItems.get(0).getActivity(), resultItems.get(0).getActivity());
        assertEquals(scheduleItems.get(1).getActivity(), resultItems.get(1).getActivity());
        assertEquals(scheduleItems.get(2).getActivity(), resultItems.get(2).getActivity());

        assertEquals(scheduleItems.get(0).getTime(), resultItems.get(0).getTime());
        assertEquals(scheduleItems.get(1).getTime(), resultItems.get(1).getTime());
        assertEquals(scheduleItems.get(2).getTime(), resultItems.get(2).getTime());

        dropDBTables(dbc);
    }

    @Test
    void retrieveCourseScheduleTaskFromDB() {
        DBApi dbc = new DBApi(rb.getString("dsn-test"));
        // init db
        dbc.initDBWeeklyOneTask();
        dbc.initDBCourseTask();

        List<TaskBean> tasks = new ArrayList<>();
        List<CourseScheduleTaskBean> assumedScheduleItems = new ArrayList<>();

        tasks.add(new TaskBean("Math", "Hard", 220602, "Exam"));
        tasks.add(new TaskBean("Agile", "Medium", 220529, "Final Report"));
        tasks.add(new TaskBean("Databases", "Easy", 220615, "Homework"));

        assumedScheduleItems.add(new CourseScheduleTaskBean(1, "2022-05-14", tasks.get(0)));
        assumedScheduleItems.add(new CourseScheduleTaskBean(2, "2022-05-15", tasks.get(1)));
        assumedScheduleItems.add(new CourseScheduleTaskBean(3, "2022-05-16", tasks.get(2)));

        dbc.insertTaskItems(tasks.get(0));
        dbc.insertTaskItems(tasks.get(1));
        dbc.insertTaskItems(tasks.get(2));

        dbc.insertWeeklyScheduleItems(assumedScheduleItems);

        List<CourseScheduleTaskBean> resultItems = dbc.retrieveCourseScheduleTaskFromDB();

        assertEquals(3, resultItems.size());

        assertEquals(assumedScheduleItems.get(0).getTaskId(), resultItems.get(0).getTaskId());
        assertEquals(assumedScheduleItems.get(1).getTaskId(), resultItems.get(1).getTaskId());
        assertEquals(assumedScheduleItems.get(2).getTaskId(), resultItems.get(2).getTaskId());

        assertEquals(assumedScheduleItems.get(0).getCourse(), resultItems.get(0).getCourse());
        assertEquals(assumedScheduleItems.get(1).getCourse(), resultItems.get(1).getCourse());
        assertEquals(assumedScheduleItems.get(2).getCourse(), resultItems.get(2).getCourse());

        assertEquals(assumedScheduleItems.get(0).getTask().getCourse(), resultItems.get(0).getTask().getCourse());
        assertEquals(assumedScheduleItems.get(1).getTask().getCourse(), resultItems.get(1).getTask().getCourse());
        assertEquals(assumedScheduleItems.get(2).getTask().getCourse(), resultItems.get(2).getTask().getCourse());

        dropDBTables(dbc);
    }

    @Test
    void retrieveCourseTaskFromDB() {
        DBApi dbc = new DBApi(rb.getString("dsn-test"));

        TaskBean task = new TaskBean("Agile", "Medium", 220625, "Project1");
        TaskBean task1 = new TaskBean("Mathematics", "Hard", 2201009, "Examination 1");

        dbc.initDBCourseTask();
        dbc.insertTaskItems(task);
        dbc.insertTaskItems(task1);

        List<TaskBean> resultItems = dbc.retrieveCourseTaskFromDB();

        assertEquals(2, resultItems.size());

        assertEquals(task.getCourse(), resultItems.get(0).getCourse());
        assertEquals(task.getDeadline(), resultItems.get(0).getDeadline());
        assertEquals(task.getDifficulty(), resultItems.get(0).getDifficulty());
        assertEquals(task.getTask(), resultItems.get(0).getTask());

        assertEquals(task1.getCourse(), resultItems.get(1).getCourse());
        assertEquals(task1.getDeadline(), resultItems.get(1).getDeadline());
        assertEquals(task1.getDifficulty(), resultItems.get(1).getDifficulty());
        assertEquals(task1.getTask(), resultItems.get(1).getTask());

        dropDBTables(dbc);
    }

    private void dropDBTables(DBConnect dbc) {
        dbc.updateRawQuery("DROP TABLE IF EXISTS day_schedule_items;");
        dbc.updateRawQuery("DROP TABLE IF EXISTS course_tasks;");
        dbc.updateRawQuery("DROP TABLE IF EXISTS course_schedule_tasks;");
    }
}