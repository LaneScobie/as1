package com.example.scobie.habittracker;

import junit.framework.TestCase;

import java.util.Collection;

/**
 * Created by LaneScobie on 16-10-01.
 */
public class TestHabitList extends TestCase{
    public void  testHabitList(){
        HabitList habitList= new HabitList();
        Collection<Habit> habits = habitList.getHabits();
        assertTrue("Empty HabitList", habits.size()==0);
    }

    public void testAddHabitList(){
        HabitList habitList = new HabitList();
        String habitName = "a habit";
        Habit testHabit = new Habit(habitName);
        habitList.addHabit(testHabit);
        assertTrue("Habit List Size", habitList.size()==1);
        assertTrue("Test habit contains", habitList.contains(testHabit));
    }

    public void testGetHabits(){
        HabitList habitList = new HabitList();
        String habitName = "a habit";
        Habit testHabit = new Habit(habitName);
        habitList.addHabit(testHabit);
        assertTrue("Habit List Size", habitList.size()==1);
        assertTrue("Test habit contains", habitList.contains(testHabit));
    }

    public void testRemoveHabit(){
        HabitList habitList = new HabitList();
        String habitName = "a habit";
        Habit testHabit = new Habit(habitName);
        habitList.addHabit(testHabit);
        Collection<Habit> habits = habitList.getHabits();
        assertTrue("Habit List Size did not add", habits.size()==1);
        assertTrue("", habits.contains(testHabit));
        habitList.removeHabit(testHabit);
        assertTrue("Habit List Size did not remove", habits.size()==0);
        assertFalse("Test habit not contains", habits.contains(testHabit));
    }


}
