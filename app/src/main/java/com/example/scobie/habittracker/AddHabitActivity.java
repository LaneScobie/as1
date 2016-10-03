package com.example.scobie.habittracker;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by scobie on 9/30/16.
 */
public class AddHabitActivity extends Activity {
    private EditText habitInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_habit);

        habitInput = (EditText)findViewById(R.id.AddHabitTextInput);
        Button FinishAddButton= (Button) findViewById(R.id.FinishAddButton);

        FinishAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text= habitInput.getText().toString();

                //Pass back information to mainActivity where Habit is made
                Intent intent= new Intent();
                intent.putExtra("name",text);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

    }



}
