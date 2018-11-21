package com.example.rayold.everydayneeds.activities;

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

import com.example.rayold.everydayneeds.R;

public class Register extends AppCompatActivity {
    DatabaseHelper db;
    EditText name, email, password;
    TextView haveAccount;
    Button register;
    Spinner mySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = new DatabaseHelper(this);
        name = (EditText) findViewById(R.id.etName);
        email = (EditText) findViewById(R.id.etEmail);
        password = (EditText) findViewById(R.id.etPassword);
        haveAccount = (TextView) findViewById(R.id.bhaveAcount);
        mySpinner = (Spinner) findViewById(R.id.role);

        final ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Register.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        register = (Button) findViewById(R.id.bRegister);

        haveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this, Login.class);
                startActivity(i);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = name.getText().toString();
                String s2 = email.getText().toString();
                String s3 = password.getText().toString();
                String s4 = mySpinner.getSelectedItem().toString();

                if (s1.equals("") || s2.equals("") || s3.equals("") || s4.equals("--Choisir role--")) {
                    Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean chkemail = db.checkmail(s2);
                    if (chkemail == true) {
                        if(s4.equals("Administrateur")&&db.countAdmin()==false){
                                Toast.makeText(getApplicationContext(), "Admin Already exists", Toast.LENGTH_SHORT).show();
                        }else {
                            Boolean insert = db.insert(s1, s2, s3, s4);
                            if (insert == true) {
                                Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(Register.this, Activity_LoggedIn.class);
                                i.putExtra("EMAIL", s2);
                                i.putExtra("NAME",s1);
                                i.putExtra("ROLE",s4);
                                startActivity(i);
                            } else {
                                Toast.makeText(getApplicationContext(), "Registered Failing", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Email Already exists", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
    }
}
