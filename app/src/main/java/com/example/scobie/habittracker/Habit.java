package com.example.scobie.habittracker;

import java.util.Date;

/**
 * Created by scobie on 9/29/16.
 */


public class Habit  {
    protected String habitName;



    public Habit(String name){
        this.habitName= name;
    }

    public Habit() {

    }

    public void setHabitName(String habitName) {
        this.habitName = habitName;
    }

    public String getHabitName() {
        return habitName;
    }
    @Override
    public String toString() {
        return this.habitName;
    }

}
