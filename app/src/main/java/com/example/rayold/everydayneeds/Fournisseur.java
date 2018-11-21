package com.example.rayold.everydayneeds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rayold.everydayneeds.activities.Activity_LoggedIn;
import com.example.rayold.everydayneeds.activities.DatabaseHelper;
import com.example.rayold.everydayneeds.activities.Login;
import com.example.rayold.everydayneeds.activities.Register;

public class Fournisseur extends AppCompatActivity {
    DatabaseHelper db;
    EditText companyName, phoneNumber, address, generalDescription;
    Button save;
    Spinner mySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fournisseur);
        db = new DatabaseHelper(this);
        companyName = (EditText) findViewById(R.id.company);
        phoneNumber = (EditText) findViewById(R.id.phone);
        address = (EditText) findViewById(R.id.adress);
        generalDescription = (EditText) findViewById(R.id.description);
        mySpinner = (Spinner) findViewById(R.id.licence);

        final ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Fournisseur.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.licenseds));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        save = (Button) findViewById(R.id.btnSave);



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = companyName.getText().toString();
                String s2 = phoneNumber.getText().toString();
                String s3 = address.getText().toString();
                String s4 = generalDescription.getText().toString();
                String s5 = mySpinner.getSelectedItem().toString();

                if (s1.equals("") || s2.equals("") || s3.equals("") || s4.equals("")|| s5.equals("--Choisir Licensed--")) {
                    Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean insert = db.insertFournisseur(s1, s2, s3, s4, s5, getIntent().getStringExtra("EMAIL"));
                    if (insert == true) {
                        Toast.makeText(getApplicationContext(), "information saved: Successfully", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Fournisseur.this, serviceList.class);
                        i.putExtra("EMAIL",getIntent().getStringExtra("EMAIL") );
                        startActivity(i);
                    } else {
                        Toast.makeText(getApplicationContext(), "information saved: Failing", Toast.LENGTH_SHORT).show();
                    }
                }

            }

        });
    }
}
