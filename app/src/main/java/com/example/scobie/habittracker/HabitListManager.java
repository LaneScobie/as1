package com.example.scobie.habittracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by LaneScobie on 16-10-03.
 */
public class HabitListManager {
    static final String prefFile = "HabitList";
    static final String hlKey= "habitList";
    Context context;

    public HabitListManager(Context context){
        this.context = context;
    }

    public HabitList loadHabitList() throws IOException, ClassNotFoundException {
        SharedPreferences settings = context.getSharedPreferences(prefFile,Context.MODE_PRIVATE);
        String habitListData= settings.getString(hlKey,"");

        if(habitListData.equals("")){
            return new HabitList();
        }else{
            return habitListFromString(habitListData);
        }
    }
    static public HabitList habitListFromString(String habitListData) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bi= new ByteArrayInputStream(Base64.decode(habitListData,Base64.DEFAULT));
        ObjectInputStream oi = new ObjectInputStream(bi);
        return (HabitList) oi.readObject();
    }


    static public String habitListToString(HabitList hl) throws IOException {
        ByteArrayOutputStream bo= new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(hl);
        oo.close();
        byte bytes[] = bo.toByteArray();
        return Base64.encodeToString(bytes,Base64.DEFAULT);
    }

    public void saveHabitList(HabitList hl) throws IOException {
        SharedPreferences settings = context.getSharedPreferences(prefFile,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(hlKey,habitListToString(hl));
        editor.commit();
    }
}
