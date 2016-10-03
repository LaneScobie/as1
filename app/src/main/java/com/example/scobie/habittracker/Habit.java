package com.example.scobie.habittracker;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by scobie on 9/29/16.
 */


public class Habit implements Serializable{
    protected String habitName;
    protected ArrayList<Date> record = new ArrayList<Date>(); //record of habit completions
    protected Integer count; //how many habit completions
    protected ArrayList<Integer> days= new ArrayList<Integer>(); //[0,0,0,0,0,0,0] -change to 1 if habit occurs on that day
    Date thisDate= new Date();

    public Habit(String name){
        this.habitName= name;
    }

    //Name is set on create-- did not do an Edit Habit option
    public void setHabitName(String habitName) {
        this.habitName = habitName;
    }

    public String getHabitName() {
        return habitName;
    }

    //Admittedly Unneccesary
    public void setHabitCount(){
        count= record.size();
    }

    public int getHabitCount(){
        count= record.size();
        return count;
    }
    public void completeHabit(){//Add record, basically
        record.add(thisDate);
    }

    public ArrayList<Date> getRecord(){
        return record;
    }

    public void removeRecord(Date r){
        record.remove(r);
    }

    @Override
    public String toString() {
        return this.habitName;
    }

}
