package com.example.ankit.insticable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Main 2 activity.
 */
public class Main2Activity extends AppCompatActivity{
    /**
     * The Student.
     */
    instistudent student= new instistudent();
    /**
     * The Degreestring.
     */
    String degreestring;
    /**
     * The Degreesp.
     */
    Spinner degreesp;
    /**
     * The Deptsp.
     */
    Spinner deptsp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



       degreesp = (Spinner)findViewById(R.id.degreespinner);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.Degree_choices, android.R.layout.simple_gallery_item);
// Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.select_dialog_item);
// Apply the adapter to the spinner
        degreesp.setAdapter(adapter1);
//        degreesp.setOnItemSelectedListener(this);


        deptsp = (Spinner)findViewById(R.id.branchspinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.Branch_choices, android.R.layout.simple_gallery_item);
// Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.select_dialog_item);
// Apply the adapter to the spinner
        deptsp.setAdapter(adapter2);



        Intent intent = getIntent();
        student= (instistudent) intent.getExtras().getSerializable("mystudent");
    }

    /**
     * Coordinator.
     * This is checkbox onClick function which sets the student type
     * @param view the view
     */
    public void coordinator(View view){
        boolean checked = ((CheckBox) view).isChecked();
        if(checked) {
            student.settype("coordinator");
        }
        else{
            student.settype("");
        }
    }

    /**
     * Student.
     * This is checkbox onClick function which sets the student type
     * @param view the view
     */
    public void student(View view){
        boolean checked = ((CheckBox) view).isChecked();
        if(checked) {
            student.settype("student");
        }
        else{
            student.settype("");
        }
    }

    /**
     * Continue 1.
     * This function sets the student department, degree and hostel
     * to the values inputted in layout file
     * @param view the view
     */
    public void continue1(View view){
//        EditText dept= (EditText) findViewById(R.id.Dept1);
        String dept1= (deptsp.getSelectedItem()).toString();
        student.setDepartment(dept1);
        EditText hostel= (EditText) findViewById(R.id.Hostel1);
        String hostel1= (hostel.getText()).toString();
        student.setHostel(hostel1);
//        EditText degree= (EditText) findViewById(R.id.Degree1);
        String degree1= (degreesp.getSelectedItem()).toString();
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
