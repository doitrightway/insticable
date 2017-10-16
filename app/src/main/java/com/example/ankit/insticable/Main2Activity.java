package com.example.ankit.insticable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    instistudent student= new instistudent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        student= (instistudent) intent.getExtras().getSerializable("mystudent");
    }
    public void coordinator(View view){
        boolean checked = ((CheckBox) view).isChecked();
        if(checked) {
            student.settype("coordinator");
        }
        else{
            student.settype("");
        }
    }

    public void student(View view){
        boolean checked = ((CheckBox) view).isChecked();
        if(checked) {
            student.settype("student");
        }
        else{
            student.settype("");
        }
    }
    public void continue1(View view){
        EditText dept= (EditText) findViewById(R.id.Dept1);
        String dept1= (dept.getText()).toString();
        student.setDepartment(dept1);
        EditText hostel= (EditText) findViewById(R.id.Hostel1);
        String hostel1= (hostel.getText()).toString();
        student.setHostel(hostel1);
        EditText degree= (EditText) findViewById(R.id.Degree1);
        String degree1= (degree.getText()).toString();
        student.setDegree(degree1);
        CheckBox cordi=(CheckBox) findViewById(R.id.cordi);
        CheckBox stu=(CheckBox) findViewById(R.id.stu);
        if(cordi.isChecked())
        {
            student.settype("coordinator");
        }
        else {
            student.settype("student");
        }
        Intent intent =new Intent(Main2Activity.this, Main4Activity.class);
        intent.putExtra("mystudent",student);
        startActivity(intent);
        finish();
    }

}
