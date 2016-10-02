package com.example.scobie.habittracker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

/**
 * Created by scobie on 9/29/16.
 */


public class Habit  {
    protected String habitName;
    protected ArrayList<String> record; //record of habit completions
    protected int count; //how many habit completions
    protected ArrayList<Integer> days; //[0,0,0,0,0,0,0] -change to 1 if habit occurs on that day

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
    public void setHabitCount(){
        count= record.size();
    }

    public int getHabitCount(){
        count= record.size(); //might as well set before you get...
        return count;
    }
    public void completeHabit(){//Add record, basically
        Calendar now = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(now.getTime());
        record.add(formattedDate);
    }

    public ArrayList<String> getRecord(){
        return record;
    }

    public void removeRecord(String r){
        record.remove(r);
    }


    @Override
    public String toString() {
        return this.habitName;
    }

}
