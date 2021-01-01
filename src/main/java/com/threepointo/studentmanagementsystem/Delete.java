package com.threepointo.studentmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Delete extends AppCompatActivity {
    DatabaseReference myRef;
    List<Student> students;
    Button del;
    EditText roll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        del=(Button) findViewById(R.id.delete);
        roll=(EditText) findViewById(R.id.roll);
        students = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Student");
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String roll_no = roll.getText().toString().toUpperCase();
                try {
                    myRef.child(roll_no).removeValue();
                    Toast.makeText(getApplicationContext(), "Deleted!", Toast.LENGTH_SHORT).show();
                    roll.setText("");
                }
                catch (Exception e)
                {
                    AlertDialog.Builder builder= new AlertDialog.Builder(Delete.this);
                    builder.setTitle("Enter Valid Roll_number")
                            .setCancelable(true)
                            .setPositiveButton("Try Again!",null);
                    AlertDialog ad=builder.create();
                    ad.show();
                }

            }
        });

    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(Delete.this,hp_admin.class));
    }
}
