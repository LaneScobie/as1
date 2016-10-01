package com.example.scobie.habittracker;

import junit.framework.TestCase;

/**
 * Created by LaneScobie on 16-10-01.
 */
public class TestHabit extends TestCase {
    public void testHabit(){
        String habitName = "a habit";
        Habit habit = new Habit(habitName);
        assertTrue("Habit name is not equal", habitName.equals(habit.getHabitName()));
    }



}
