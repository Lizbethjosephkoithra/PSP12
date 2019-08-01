package com.myapplicationdev.psp12;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<Task> alTask;
    ArrayAdapter aaTask;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.lv);
        add = findViewById(R.id.btn);

        DBHelper db = new DBHelper(MainActivity.this);
        alTask = db.getAllTask();
        aaTask = new ArrayAdapter<Task>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, alTask);
        lv.setAdapter(aaTask);
        aaTask.notifyDataSetChanged();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, AddTask.class);
                startActivityForResult(intent, 1);

            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent dataIntent) {
        super.onActivityResult(requestCode, resultCode, dataIntent);
        DBHelper db = new DBHelper(MainActivity.this);

        alTask.clear();
        alTask = db.getAllTask();
        aaTask = new ArrayAdapter<Task>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, alTask);
        lv.setAdapter(aaTask);


    }

}

