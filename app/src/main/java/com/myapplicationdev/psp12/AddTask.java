package com.myapplicationdev.psp12;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AddTask extends AppCompatActivity {

    TextView name, desc, time;
    int reqCode = 1122334455;
    AlarmManager am;
    Button add,cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        name = findViewById(R.id.etName);
        desc = findViewById(R.id.etDesc);
        time = findViewById(R.id.etNoti);
        add = findViewById(R.id.btnAdd);
        cancel = findViewById(R.id.btnCancel);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name1 = name.getText().toString();
                String description1 = desc.getText().toString();
                String time1 = time.getText().toString();


                DBHelper dbh = new DBHelper(AddTask.this);
                long row_affected = dbh.insertTask(name1 , description1);

                if (row_affected != -1){
                    Toast.makeText(AddTask.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }


                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND,Integer.parseInt(time1));

                Intent intent = new Intent(AddTask.this, NotificationReceiver.class);
                intent.putExtra("name", name1);
                intent.putExtra("desc", description1);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(AddTask.this, reqCode, intent, PendingIntent.FLAG_CANCEL_CURRENT);

                AlarmManager am = (AlarmManager)getSystemService(Activity.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
                finish();


            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

