package com.example.scobie.habittracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainHabitActivity extends Activity {
    private static final String FILENAME= "file.sav";
    private ListView oldHabitList;
    private ArrayList<Habit> habitList = new ArrayList<Habit>();
    private ArrayAdapter<Habit> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_habit);

        oldHabitList = (ListView) findViewById(R.id.oldHabitList);
        Button AddMenuButton = (Button) findViewById(R.id.AddMenuButton);


        AddMenuButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainHabitActivity.this, AddHabitActivity.class);
                //startActivity(intent);
                startActivityForResult(intent, 1);
            }
        });

    }
    public boolean onCreateContextMenu(MenuItem menu){
        getMenuInflater().inflate(R.menu.main, (Menu) menu);
        return true;
    }
    public void CompleteHabit(MenuItem menu){
        Toast.makeText(this,"Complete Habit", Toast.LENGTH_SHORT).show();
    }
    public void habitRecord(MenuItem menu){
        Toast.makeText(this,"Habit Record", Toast.LENGTH_SHORT).show();
    }
    public void DeleteHabit(MenuItem menu){
        Toast.makeText(this,"Delete Habit", Toast.LENGTH_SHORT).show();
    }

    //Open menu on click of listView
    //code from:http://stackoverflow.com/questions/6435073/android-context-menu-on-single-click Oct. 1 2016
    private void addOnClickListener(){
        oldHabitList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                view.showContextMenu();
            }
        });
    }


    //code taken from:http://stackoverflow.com/questions/10407159/how-to-manage-startactivityforresult-on-android Oct. 1/2016
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode==1){
            if(resultCode==Activity.RESULT_OK){
                //Get data from AddMenu modify habitList
                String name= data.getStringExtra("name");
                Habit newHabit = new Habit(name);
                habitList.add(newHabit);
                adapter.notifyDataSetChanged();
                saveInFile();
            }
            if(resultCode==Activity.RESULT_CANCELED){
                //i guess nothing happened
            }
        }


    }


    @Override
    protected void onStart() {
        super.onStart();
        adapter = new ArrayAdapter<Habit>(this,
                R.layout.list_item, habitList);
        oldHabitList.setAdapter(adapter);
    }

    private void loadFromFile() {
        ArrayList<String> tweets = new ArrayList<String>();
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            //Code taken from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt Sept,22. 2016
            Type listType = new TypeToken<ArrayList<Habit>>(){}.getType();
            habitList = gson.fromJson(in, listType);

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

    private void saveInFile() {
        try {

            FileOutputStream fos = openFileOutput(FILENAME, 0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(habitList, writer);
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

