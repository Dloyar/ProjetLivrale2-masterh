package com.example.rayold.everydayneeds;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rayold.everydayneeds.activities.DatabaseHelper;
import com.example.rayold.everydayneeds.activities.User;

import java.util.ArrayList;

public class serviceList extends AppCompatActivity {
    DatabaseHelper db;
    String serviceName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_list);
        final ListView listView = (ListView) findViewById(R.id.serviceListView);
        db = new DatabaseHelper(this);

        ArrayList<String> theList = new ArrayList<>();
        Cursor data = db.getService();
        if(data.getCount() == 0) {
            Toast.makeText(this, "There are no service available. Admin didn't add any yet", Toast.LENGTH_LONG).show();
        }else{
            while(data.moveToNext()){
                theList.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
                listView.setAdapter(listAdapter);
            }
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                serviceName =(String) listView.getItemAtPosition(position);
                boolean insert = db.insertServiceFournisseur(getIntent().getStringExtra("EMAIL"),(String) listView.getItemAtPosition(position));
                if (insert = true ){
                    Intent j = new Intent (serviceList.this,AvailabilityFournisseur.class );
                    Toast.makeText(getApplicationContext(), "service saved: successful ", Toast.LENGTH_SHORT).show();
                    startActivity(j);
                }
                else{
                    Toast.makeText(getApplicationContext(), "service saved: fail", Toast.LENGTH_SHORT).show();
                }

            }
});
}
}