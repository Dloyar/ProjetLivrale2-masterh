package com.example.rayold.everydayneeds.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rayold.everydayneeds.Fournisseur;
import com.example.rayold.everydayneeds.R;
import com.example.rayold.everydayneeds.ServiceEdit;
import com.example.rayold.everydayneeds.admin;

public class Login extends AppCompatActivity {

    EditText emailUser,passwordLogin;
    TextView register;
    Button boutonLogin;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DatabaseHelper(this);
        emailUser= (EditText)findViewById(R.id.etEmailUser);
        passwordLogin = (EditText)findViewById(R.id.etPassword);

        register = (TextView)findViewById(R.id.tvRegisterLink);
        boutonLogin = (Button)findViewById(R.id.bLogin);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
            }
        });
        boutonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailUser.getText().toString();;
                String password = passwordLogin.getText().toString();
                Boolean chkmailpass = db.emailpasword(email, password);
                User user = db.findUser(email);
                if (chkmailpass == true) {
                    Toast.makeText(getApplicationContext(),"Successful login", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Login.this, Activity_LoggedIn.class);
                    i.putExtra("EMAIL", email);
                    i.putExtra("NAME",user.getName());
                    i.putExtra("ROLE",user.getRole());
                    if(db.isAdministrator(email)==true){
                        Intent j = new Intent(Login.this, admin.class);
                        startActivity(j);
                    }else if(db.isFournisseur(email)==true){
                        if(db.fournisseurHasPersonalInformation(email)){
                            Intent a = new Intent(Login.this, Fournisseur.class);
                            a.putExtra("EMAIL", user.getEmail());
                            startActivity(a);
                        }
                        else{
                            Intent a = new Intent(Login.this, ServiceEdit.class);
                            startActivity(a);
                        }

                    }else{
                        startActivity(i);}
                } else {
                    Toast.makeText(getApplicationContext(),"Wrong email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
