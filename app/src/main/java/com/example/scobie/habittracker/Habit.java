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

    public String getHabitName() {
        return habitName;
    }
}
