package com.threepointo.studentmanagementsystem;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class Student_list extends ArrayAdapter<Student> {
    private Activity context;
    private List<Student> students;

    public Student_list(Activity context, List<Student> studentList){
        super(context,R.layout.list_layout,studentList);
        this.context = context;
        this.students = studentList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Student student=students.get(position);
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_layout,null,true);
        TextView name  = (TextView) listViewItem.findViewById(R.id.name);
        TextView roll  = (TextView) listViewItem.findViewById(R.id.roll);
        TextView date   =(TextView)listViewItem.findViewById(R.id.dob);
        TextView dept  = (TextView) listViewItem.findViewById(R.id.dept_cls);
        TextView phone   =(TextView)listViewItem.findViewById(R.id.phone);

        name.setText(student.getName());
        roll.setText(student.getRoll());
        date.setText(student.getDate());
        dept.setText(student.getDept()+" "+student.getClss());
        phone.setText(student.getPhone());
        return listViewItem;
    }

}
