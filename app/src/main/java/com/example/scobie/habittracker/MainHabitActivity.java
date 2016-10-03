package com.example.scobie.habittracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
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
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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
        registerForContextMenu(oldHabitList);

        Button AddMenuButton = (Button) findViewById(R.id.AddMenuButton);


        AddMenuButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainHabitActivity.this, AddHabitActivity.class);
                startActivityForResult(intent, 1);
            }
        });

    }
    //Make contextMenu on ListView items
    //Code from:http://stackoverflow.com/questions/18632331/using-contextmenu-with-listview-in-android Oct.1/2016
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        if (v.getId()==R.id.oldHabitList){
            //ListView  = (ListView) v;

            menu.add("Complete");
            menu.add("Record");
            menu.add("Delete");
        }
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle() == "Complete") {
            //Add to record (which adds to count)
            Toast.makeText(MainHabitActivity.this, "Habit Completed", Toast.LENGTH_SHORT).show();
            AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            int position= info.position;
            Habit obj = habitList.get(position);
            obj.completeHabit();

            //adapter.notifyDataSetChanged();
            //saveInFile();

        } else if (item.getTitle()=="Record") {
            Toast.makeText(MainHabitActivity.this, "Accessing Records", Toast.LENGTH_SHORT).show();
            //opens habitREcordActivity--
            //have to pass which habit...
            Intent intent = new Intent(MainHabitActivity.this, HabitRecordActivity.class);
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            int position= info.position;
            Habit obj = habitList.get(position);
            intent.putExtra("Habit", obj);


            startActivity(intent);
            //finish(); --This Kills this activity... don't make that mistake twice...

        } else if(item.getTitle()=="Delete"){
            Toast.makeText(MainHabitActivity.this, "Habit Deleted", Toast.LENGTH_SHORT).show();
            habitList.remove(item.getItemId());
            adapter.notifyDataSetChanged();
            saveInFile();

        } else {
            return false;
        }
        return true;
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

    //Code from lonelyTwitter app
    private void loadFromFile() {
        ArrayList<String> habitList = new ArrayList<String>();
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

