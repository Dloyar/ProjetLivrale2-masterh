package com.example.rayold.everydayneeds;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.rayold.everydayneeds.activities.DatabaseHelper;
import com.example.rayold.everydayneeds.activities.Register;

public class fournisseurService extends AppCompatActivity {

    private static final String TAG = "fournisseurService";
    DatabaseHelper db;
    private Button buttonSaveDate;
    Spinner mySpinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fournisseur_service);
        buttonSaveDate = (Button) findViewById(R.id.buttonSaveDate);
        mySpinner = (Spinner) findViewById(R.id.spinner);
        final EditText chooseTimedebut = findViewById(R.id.etChooseTimeDebut);
        final EditText chooseTimefin = findViewById(R.id.etChooseTimeFin);
        final ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(fournisseurService.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.semaine));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);


        chooseTimedebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(fournisseurService.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        chooseTimedebut.setText(hourOfDay + ":" + minutes);
                    }
                }, 0, 0, false);
                timePickerDialog.show();
            }
        });
        chooseTimefin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(fournisseurService.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        chooseTimefin.setText(hourOfDay + ":" + minutes);
                    }
                }, 0, 0, false);
                timePickerDialog.show();
            }
        });

        buttonSaveDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = chooseTimedebut.getText().toString();
                String s2 = chooseTimefin.getText().toString();
                String s3 = mySpinner.getSelectedItem().toString();

                if (s1.equals("Click here to choose time beginning") || s2.equals("Click here to choose time ending") || s3.equals("--Choisir jour--")) {
                    Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
                } else {
                    boolean insert = db.insertAvailable(s3, s3, s1, s2);
                    if (insert == true) {
                        Toast.makeText(getApplicationContext(), "information saved: Successfully", Toast.LENGTH_SHORT).show();
                        Intent j = new Intent(fournisseurService.this, serviceList.class);
                        startActivity(j);
                    } else {
                        Toast.makeText(getApplicationContext(), "information saved: Failing", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


}
