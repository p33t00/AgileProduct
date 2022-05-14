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

        dbc.removeDailyScheduleFromDB();
    }

    @Test
    void retrieveCourseScheduleTaskFromDB(){
        DBApi dbc = new DBApi(rb.getString("dsn-test"));
        dbc.removeWeeklyScheduleFromDB();
        ArrayList<WeeklySchedule> myCourses = new ArrayList<>();
        ArrayList<ArrayList> complete = new ArrayList<>();

        WeeklySchedule course = new WeeklySchedule("database","Easy",220622,"assignment1");
        WeeklySchedule course2 = new WeeklySchedule("agile","Easy",220723,"assignment2");
        myCourses.add(course);
        myCourses.add(course2);
        complete.add(myCourses);
        ArrayList<ArrayList> completed = course.createWeeklyOneTask(myCourses);

        dbc.initDBWeeklyOneTask();
        dbc.insertWeeklyScheduleItems(completed);

        List<CourseScheduleTaskBean> resultItems = dbc.retrieveCourseScheduleTaskFromDB();

        assertEquals(2, resultItems.size());
        assertEquals(completed.get(0).get(0), resultItems.get(0).getTaskDate());
        assertEquals(completed.get(0).get(1), resultItems.get(0).getCourse());
        assertEquals(completed.get(0).get(2), resultItems.get(0).getTask());
        assertEquals(completed.get(0).get(3), resultItems.get(0).getDifficulty());


        assertEquals(completed.get(1).get(0), resultItems.get(1).getTaskDate());
        assertEquals(completed.get(1).get(1), resultItems.get(1).getCourse());
        assertEquals(completed.get(1).get(2), resultItems.get(1).getTask());
        assertEquals(completed.get(1).get(3), resultItems.get(1).getDifficulty());
    }

    @Test
    void retrieveCourseScheduleTaskFromDB() {
        DBApi dbc = new DBApi(rb.getString("dsn-test"));
        // init db
        dbc.initDBWeeklyOneTask();
        dbc.initDBCourseTask();

        List<CourseScheduleTaskBean> scheduleItems = new ArrayList<>();
        scheduleItems.add(new CourseScheduleTaskBean(1, "2022-05-14"));
        scheduleItems.add(new CourseScheduleTaskBean(2, "2022-05-15"));
        scheduleItems.add(new CourseScheduleTaskBean(3, "2022-05-16"));

//        scheduleItems.add(new CourseScheduleTaskBean(1, "2022-05-14",
//                new TaskBean("Math", "Hard", 220602, "Exam")));
//        scheduleItems.add(new CourseScheduleTaskBean(2, "2022-05-15",
//                new TaskBean("Agile", "Medium", 220529, "Final Report")));
//        scheduleItems.add(new CourseScheduleTaskBean(3, "2022-05-16",
//                new TaskBean("Databases", "Easy", 220615, "Homework")));

        dbc.insertWeeklyScheduleItems(scheduleItems);

        List<TmpCourseScheduleTaskBean> resultItems = dbc.test_retrieveCourseScheduleTaskFromDB();

        assertEquals(3, resultItems.size());

        assertEquals(scheduleItems.get(0).getTaskId(), resultItems.get(0).getTaskId());
        assertEquals(scheduleItems.get(1).getTaskId(), resultItems.get(1).getTaskId());
        assertEquals(scheduleItems.get(2).getTaskId(), resultItems.get(2).getTaskId());

//        assertEquals(scheduleItems.get(0).getTask().getCourse(), resultItems.get(0).getTask().getCourse());
//        assertEquals(scheduleItems.get(1).getTask().getCourse(), resultItems.get(1).getTask().getCourse());
//        assertEquals(scheduleItems.get(2).getTask().getCourse(), resultItems.get(2).getTask().getCourse());

        dropDayScheduleItemsTable(dbc);
    }

    @Test
    void retrieveCourseTaskFromDB() {
        DBApi dbc = new DBApi(rb.getString("dsn-test"));
        dropCourseTaskTable(dbc);

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
    }

    private void dropDayScheduleItemsTable(DBConnect dbc) {
        dbc.updateRawQuery("DROP TABLE IF EXISTS day_schedule_items;");
    }

        private void dropCourseTaskTable(DBConnect dbc) {
        dbc.updateRawQuery("DROP TABLE IF EXISTS course_tasks;");
    }
}