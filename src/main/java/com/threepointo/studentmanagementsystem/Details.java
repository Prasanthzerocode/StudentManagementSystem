package com.threepointo.studentmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Details extends AppCompatActivity {
    TextView name,roll,dept,clss,phone,dob;
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent i=getIntent();
        logout = (Button) findViewById(R.id.logout);
        name= (TextView) findViewById(R.id.name);
        roll= (TextView) findViewById(R.id.roll);
        dept= (TextView) findViewById(R.id.dept);
        dob= (TextView) findViewById(R.id.dob);
        phone= (TextView) findViewById(R.id.phone);
        clss= (TextView) findViewById(R.id.clss);
        name.setText((String)i.getSerializableExtra("Name"));
        roll.setText((String)i.getSerializableExtra("Roll"));
        dept.setText((String)i.getSerializableExtra("Dept"));
        phone.setText((String)i.getSerializableExtra("Phone"));
        clss.setText((String)i.getSerializableExtra("Class"));
        dob.setText((String)i.getSerializableExtra("Date"));
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Details.this,MainActivity.class));
            }
        });
    }
    @Override
    public void onBackPressed() {
    }
}
