package com.threepointo.studentmanagementsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class Add_student extends AppCompatActivity {
    EditText name,roll,dept,clss,phone,dob;
    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        name  = (EditText) findViewById(R.id.name);
        roll  = (EditText) findViewById(R.id.roll);
        dept  = (EditText) findViewById(R.id.dept);
        phone = (EditText) findViewById(R.id.phone);
        clss  = (EditText) findViewById(R.id.clss);
        dob   = (EditText) findViewById(R.id.date);
        add   = (Button)   findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Student");
                String peyar  = name.getText().toString().toUpperCase();
                String rollno = roll.getText().toString().toUpperCase();
                String deprt  = dept.getText().toString().toUpperCase();
                String phno   = phone.getText().toString();
                String clas   = clss.getText().toString().toUpperCase();
                String date   = dob.getText().toString();
                if(peyar.length()==0 || rollno.length()==0 || deprt.length()==0 || date.length()==0 ||  phno.length()!=10 || clas.length()==0 || date.length()!=10 || date.charAt(2)!='/' || date.charAt(5)!='/')
                {
                    String message="Invalid ";
                    if(peyar.length()==0)
                        message+="Name ";
                    if(rollno.length()==0)
                        message+="Roll no. ";
                    if(deprt.length()==0)
                        message+="Dept. ";
                    if(date==null || date.length()!=10 || date.charAt(2)!='/' || date.charAt(5)!='/')
                        message+="Date ";
                    if( phno.length()!=10)
                        message+="Phone Number ";
                    if(clas.length()==0)
                        message+="Class ";
                    AlertDialog.Builder builder= new AlertDialog.Builder(Add_student.this);
                    builder.setTitle(message)
                            .setCancelable(true)
                            .setPositiveButton("Try Again!",null);
                    AlertDialog ad=builder.create();
                    ad.show();
                }
                else
                {
                    Student student=new Student(peyar,date,rollno,deprt,phno,clas);
                    try {
                        myRef.child(student.getRoll()).setValue(student);
                        Toast.makeText(getApplicationContext(), "Successfully Added!", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(Add_student.this, hp_admin.class));
                    }
                    catch(Exception e)
                    {
                        AlertDialog.Builder builder= new AlertDialog.Builder(Add_student.this);
                        builder.setMessage("Roll No.exists")
                                .setCancelable(true)
                                .setPositiveButton("Try Again!",null);
                        AlertDialog ad=builder.create();
                        ad.show();
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Add_student.this,hp_admin.class));
    }
}
