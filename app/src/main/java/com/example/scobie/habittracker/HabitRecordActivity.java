package com.example.scobie.habittracker;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by scobie on 9/30/16.
 */
public class HabitRecordActivity extends Activity {
    private static final String FILENAME= "records.sav";
    private ListView recordList;
    private TextView habitName;
    private TextView count;
    private ArrayList<Date> records = new ArrayList<Date>();
    private ArrayAdapter<Date> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.habit_record);
        habitName = (TextView) findViewById(R.id.HabitRecordTextView);
        count = (TextView) findViewById(R.id.countTextView);

        recordList = (ListView) findViewById(R.id.HabitRecordListView);

        registerForContextMenu(recordList);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.HabitRecordListView) {
            ListView recordList = (ListView) v;
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) menuInfo;
            Date obj = (Date) recordList.getItemAtPosition(acmi.position);
            menu.add("Delete");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if (item.getTitle() == "Delete") {
            Toast.makeText(HabitRecordActivity.this, "Record Deleted", Toast.LENGTH_SHORT).show();
            records.remove(item.getItemId());
            adapter.notifyDataSetChanged();
            saveInRecords();
        }else{
            return false;
        }
        return true;
    }


    @Override
    protected void onStart() {
        super.onStart();
        //loadFromRecords();

        Habit habit= (Habit) getIntent().getSerializableExtra("Habit");
        records = habit.getRecord();
        habitName.setText(habit.getHabitName());
        count.setText(String.valueOf(habit.getHabitCount()));

        adapter = new ArrayAdapter<Date>(this,
                R.layout.list_item, records);
        recordList.setAdapter(adapter);

    }
//adapted from lonelyTwitter
    private void loadFromRecords() {
        ArrayList<Date> records = new ArrayList<Date>();
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            //Code taken from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt Sept,22. 2016
            Type listType = new TypeToken<ArrayList<Date>>(){}.getType();

            records = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    private void saveInRecords() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, 0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(records, writer);
            writer.flush();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

}
