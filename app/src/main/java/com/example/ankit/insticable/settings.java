package com.example.ankit.insticable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 *  Activity responsible for handling settings
 */
public class settings extends AppCompatActivity {
    instistudent student= new instistudent();
    public List <String> interests;
    String mystring;
    String hostel;
    String department;
    String type;
    EditText text1;
    EditText text2;
    CheckBox cordi;
    CheckBox stu;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Intent intent = getIntent();
        student= (instistudent) intent.getExtras().getSerializable("mystudent");
        interests=student.getinterests();
        hostel=student.getHostel();
        department=student.getDepartment();
        type=student.gettype();
        mystring= (String) intent.getExtras().getString("key");
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("users").child(student.getName());
        boolean isChecked =true;
        cordi = (CheckBox)findViewById(R.id.addcordi);
        stu = (CheckBox)findViewById(R.id.addstu);
        CheckBox cricket = (CheckBox)findViewById(R.id.cricket);
        CheckBox football = (CheckBox)findViewById(R.id.football);
        CheckBox tennis = (CheckBox)findViewById(R.id.tennis);
        CheckBox squash = (CheckBox)findViewById(R.id.squash);
        CheckBox swimming = (CheckBox)findViewById(R.id.swimming);
        CheckBox carrom = (CheckBox)findViewById(R.id.carrom);
        CheckBox chess = (CheckBox)findViewById(R.id.chess);
        CheckBox music = (CheckBox)findViewById(R.id.music);
        if(interests.contains("cricket"))
        {
            cricket.setChecked(isChecked);
        }
        if(interests.contains("football"))
        {
            football.setChecked(isChecked);
        }
        if(interests.contains("tennis"))
        {
            tennis.setChecked(isChecked);
        }
        if(interests.contains("squash"))
        {
            squash.setChecked(isChecked);
        }
        if(interests.contains("swimming"))
        {
            swimming.setChecked(isChecked);
        }
        if(interests.contains("carrom"))
        {
            carrom.setChecked(isChecked);
        }
        if(interests.contains("chess"))
        {
            chess.setChecked(isChecked);
        }
        if(interests.contains("music"))
        {
            music.setChecked(isChecked);
        }
        text1=(EditText) findViewById(R.id.addhostel);
        text1.setText(hostel);
        text2=(EditText) findViewById(R.id.adddepartment);
        text2.setText(department);
        if(type.equals("coordinator"))
            cordi.setChecked(isChecked);
        else if(type.equals("student"))
            stu.setChecked(isChecked);

    }


    public void addcordi(View view){
        boolean checked = ((CheckBox) view).isChecked();
        if(checked) {
            type="coordinator";
        }
        else{
            type="";
        }
    }


    public void addstu(View view){
        boolean checked = ((CheckBox) view).isChecked();
        if(checked) {
            type="student";
        }
        else{
            type="";
        }
    }


    public void addcricket(View view){
        boolean checked = ((CheckBox) view).isChecked();
        if(checked) {
            interests.add("cricket");
        }
        else{
            interests.remove("cricket");
        }
    }


    public void addfootball(View view){
        boolean checked = ((CheckBox) view).isChecked();
        if(checked) {
            interests.add("football");
        }
        else{
            interests.remove("football");
        }
    }



    public void addtennis(View view){
        boolean checked = ((CheckBox) view).isChecked();
        if(checked) {
            interests.add("tennis");
        }
        else{
            interests.remove("tennis");
        }
    }


    public void addsquash(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        if (checked) {
            interests.add("squash");
        } else {
            interests.remove("squash");
        }
    }


    public void addswimming(View view){
        boolean checked = ((CheckBox) view).isChecked();
        if(checked) {
            interests.add("swimming");
        }
        else{
            interests.remove("swimming");
        }
    }


    public void addcarrom(View view){
        boolean checked = ((CheckBox) view).isChecked();
        if(checked) {
            interests.add("carrom");
        }
        else{
            interests.remove("carrom");
        }
    }


    public void addchess(View view){
        boolean checked = ((CheckBox) view).isChecked();
        if(checked) {
            interests.add("chess");
        }
        else{
            interests.remove("chess");
        }
    }

    public void addmusic(View view){
        boolean checked = ((CheckBox) view).isChecked();
        if(checked) {
            interests.add("music");
        }
        else{
            interests.remove("music");
        }
    }

    public void done(View view){
        mDatabaseReference.child(mystring).child("interests").setValue(interests);
        hostel=text1.getText().toString();
        department=text2.getText().toString();
        if(cordi.isChecked())
        {
            type="coordinator";
        }
        else {
            type="student";
        }
        mDatabaseReference.child(mystring).child("hostel").setValue(hostel);
        mDatabaseReference.child(mystring).child("department").setValue(department);
        mDatabaseReference.child(mystring).child("type").setValue(type);
        finish();
    }
}
