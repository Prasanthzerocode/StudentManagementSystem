package com.threepointo.studentmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText user,pass;
    DatabaseReference myRef;
    List<Student> students;
    CheckBox cb;
    RadioGroup rg;
    RadioButton rb;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Student");
        user = (EditText) findViewById(R.id.user);
        pass = (EditText) findViewById(R.id.password);
        cb   = (CheckBox) findViewById(R.id.show);
        rg   = (RadioGroup) findViewById(R.id.grp);
        login= (Button) findViewById(R.id.login);
        students = new ArrayList<>();
        final Intent intent=new Intent(MainActivity.this,hp_admin.class);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = rg.getCheckedRadioButtonId();
                rb     = (RadioButton) findViewById(id);
                String user_type= rb.getText().toString();
                String user_id,pwd;
                user_id = user.getText().toString().toUpperCase();
                pwd     = pass.getText().toString().toUpperCase();
                if(user_id==null || pwd==null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Invalid Login")
                            .setCancelable(true)
                            .setPositiveButton("Try Again", null);
                    AlertDialog ad = builder.create();
                    ad.show();
                }
                else if (user_type.equals("Admin")) {
                        if (user_id.equals("ADMIN") && pwd.equals("12345")) {
                            Toast.makeText(getApplicationContext(), "Logging In!!", Toast.LENGTH_LONG).show();
                            startActivity(intent);
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setTitle("Invalid Login")
                                    .setCancelable(true)
                                    .setPositiveButton("Try Again", null);
                            AlertDialog ad = builder.create();
                            ad.show();
                        }
                    } else {
                        int f=0;
                        for(Student s:students)
                        {
                            if(user_id.equals(s.getRoll()))
                            {
                                if(pwd.equals(s.getDate()))
                                {
                                    f=1;
                                    Intent i=new Intent(MainActivity.this,Details.class);
                                    i.putExtra("Name",s.getName());
                                    i.putExtra("Roll",s.getRoll());
                                    i.putExtra("Date",s.getDate());
                                    i.putExtra("Class",s.getClss());
                                    i.putExtra("Dept",s.getDept());
                                    i.putExtra("Phone",s.getPhone());
                                    startActivity(i);
                                }
                            }
                        }
                        if(f==0) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setTitle("Invalid Login")
                                    .setCancelable(true)
                                    .setPositiveButton("Try Again", null);
                            AlertDialog ad = builder.create();
                            ad.show();
                        }
                    }
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(students!=null)
                    students.clear();

                for (DataSnapshot studentSnapshot : dataSnapshot.getChildren()) {
                    Student student= studentSnapshot.getValue(Student.class);
                    students.add(student);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {  }
        });
    }

    @Override
    public void onBackPressed() {
    }
}
