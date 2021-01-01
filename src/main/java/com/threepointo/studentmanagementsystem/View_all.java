package com.threepointo.studentmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class View_all extends AppCompatActivity {
    DatabaseReference myRef;
    List<Student> students;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);
        lv = (ListView) findViewById(R.id.student_list);
        lv.setDivider(null);
        lv.setFooterDividersEnabled(false);
        lv.setHeaderDividersEnabled(false);

        students = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Student");
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

                ArrayAdapter adapter = new Student_list(View_all.this, students);
                lv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {  }
        });
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(View_all.this,hp_admin.class));
    }
}
