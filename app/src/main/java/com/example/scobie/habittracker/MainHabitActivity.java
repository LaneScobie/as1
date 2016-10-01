package com.example.scobie.habittracker;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainHabitActivity extends Activity {
    private ListView oldHabitsList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_habit);

        oldHabitsList = (ListView) findViewById(R.id.oldHabitList);
        Button AddMenuButton= (Button) findViewById(R.id.AddMenuButton);

        AddMenuButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainHabitActivity.this, AddHabitActivity.class);
                startActivity(intent);
            }
        });



    }





}

