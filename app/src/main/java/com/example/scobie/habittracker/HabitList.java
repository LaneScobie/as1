package com.example.scobie.habittracker;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by LaneScobie on 16-10-01.
 */
public class HabitList {
    protected  ArrayList<Habit> habitList;

    public HabitList(){
        habitList = new ArrayList<Habit>();
    }

    public Collection<Habit> getHabits(){
        return habitList;
    }

    public void addHabit(Habit testHabit) {
        habitList.add(testHabit);
    }

    public void removeHabit(Habit testHabit) {
        habitList.remove(testHabit);
    }

    public boolean contains(Habit testHabit) {
        return habitList.contains(testHabit);
    }

    public Habit getHabit(int index){
        return habitList.get(index);
    }

    public int size() {
        return habitList.size();
    }
}
