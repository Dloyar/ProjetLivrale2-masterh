package com.example.rayold.everydayneeds;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class displayAvailability extends AppCompatActivity {
    String day, hour1,hour2;
    TextView rDay,rHour1,rHour2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_availability);

        day = getIntent().getStringExtra("DAY");
        hour1 = getIntent().getStringExtra("HOUR1");
        hour2 = getIntent().getStringExtra("HOUR2");

        rDay = (TextView)findViewById(R.id.textView7);
        rHour1 = (TextView)findViewById(R.id.textView13);
        rHour2 = (TextView)findViewById(R.id.textView14);
        rDay.setText(day);
        rHour1.setText(hour1);
        rHour2.setText(hour2);
    }
}
