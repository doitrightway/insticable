package com.example.ankit.insticable;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Main 4 activity.
 */
public class Main4Activity extends AppCompatActivity {
    /**
     * The Fab.
     */
    FloatingActionButton fab;
    /**
     * The Student.
     */
    instistudent student= new instistudent();
    /**
     * The Event.
     */
    events event = new events();
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    /**
     * The Interests.
     */
    List<String> interests=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Intent intent = getIntent();
        student= (instistudent) intent.getExtras().getSerializable("mystudent");


        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("users").child(student.getName());
    }

    /**
     * Cricket.
     *
     * @param view the view
     */
    public void cricket(View view){
        boolean checked = ((CheckBox) view).isChecked();
        if(checked) {
            interests.add("cricket");
        }
        else{
            interests.remove("cricket");
        }
    }

    /**
     * Football.
     *
     * @param view the view
     */
    public void football(View view){
        boolean checked = ((CheckBox) view).isChecked();
        if(checked) {
            interests.add("football");
        }
        else{
            interests.remove("football");
        }
    }


    /**
     * Tennis.
     *
     * @param view the view
     */
    public void tennis(View view){
        boolean checked = ((CheckBox) view).isChecked();
        if(checked) {
            interests.add("tennis");
        }
        else{
            interests.remove("tennis");
        }
    }

    /**
     * Squash.
     *
     * @param view the view
     */
    public void squash(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        if (checked) {
            interests.add("squash");
        } else {
            interests.remove("squash");
        }
    }

    /**
     * Swimming.
     *
     * @param view the view
     */
    public void swimming(View view){
        boolean checked = ((CheckBox) view).isChecked();
        if(checked) {
            interests.add("swimming");
        }
        else{
            interests.remove("swimming");
        }
    }

    /**
     * Carrom.
     *
     * @param view the view
     */
    public void carrom(View view){
        boolean checked = ((CheckBox) view).isChecked();
        if(checked) {
            interests.add("carrom");
        }
        else{
            interests.remove("carrom");
        }
    }

    /**
     * Chess.
     *
     * @param view the view
     */
    public void chess(View view){
        boolean checked = ((CheckBox) view).isChecked();
        if(checked) {
            interests.add("chess");
        }
        else{
            interests.remove("chess");
        }
    }

    /**
     * Music.
     *
     * @param view the view
     */
    public void music(View view){
        boolean checked = ((CheckBox) view).isChecked();
        if(checked) {
            interests.add("music");
        }
        else{
            interests.remove("music");
        }
    }

    /**
     * Enjoy.
     *
     * @param view the view
     */
    public void enjoy(View view){
        student.setinterests(interests);
        student.setCount(1);
        mDatabaseReference.push().setValue(student);
        finish();
    }
}
