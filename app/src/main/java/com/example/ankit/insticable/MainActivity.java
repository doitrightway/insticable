/**
 * Copyright Google Inc. All Rights Reserved.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.ankit.insticable;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.FractionRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {


//    private static final String TAG = "MainActivity";
//    public static final String ANONYMOUS = "anonymous";
//    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;
    public static final int RC_SIGN_IN = 1;
    instistudent student = new instistudent();
    events event = new events();
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private ChildEventListener mChildEventListener;
    private DatabaseReference meventReference;
    private ChildEventListener meventListener;
    private String username;
    List<String> interests=new ArrayList<>();
    public List<events> eventsList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (student.getcount()==0) {
//            setContentView(R.layout.activity_main);
//        }
//        else {
//                        TextView t= (TextView) findViewById(R.id.t1);
//                        if(student.getName().equals(username))
//            setContentView(R.layout.activity_main3);
//        }

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("users");
        meventReference = mFirebaseDatabase.getReference().child("events");
        mFirebaseAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
//                username=user.getDisplayName();
                if (user != null) {
                    username = user.getDisplayName();
                    calllistener();
                    if (student.getcount()==0) {
                        setContentView(R.layout.activity_main);
                    }
//                    else {
//                        TextView t= (TextView) findViewById(R.id.t1);
//                        if(student.getName().equals(username))
//                        setContentView(R.layout.activity_main3);
//                    }

                }
                else {
                    deletelistener();
                    startActivityForResult(
                            // Get an instance of AuthUI based on the default app
                            AuthUI.getInstance().createSignInIntentBuilder().build(),
                            RC_SIGN_IN);
                }
            }
        };
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sign_out_menu:
                AuthUI.getInstance().signOut(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);}
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == RC_SIGN_IN){
            if(resultCode == RESULT_OK){
                Toast.makeText(this,"Signed in!" , Toast.LENGTH_SHORT).show();
            }else if(resultCode==RESULT_CANCELED){
                Toast.makeText(this,"Sign in cancelled!" , Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }


    public void coordinator(View view){
        student.settype("coordinator");
        setContentView(R.layout.activity_main2);
        //startActivity(new Intent(MainActivity.this, Main2Activity.class));
    }

    public void student(View view){
        student.settype("student");
//        String usename= Fire;
        setContentView(R.layout.activity_main2);
        //startActivity(new Intent(MainActivity.this, Main2Activity.class));
    }
    public void continue1(View view){
//        student.setDepartment
        EditText dept= (EditText) findViewById(R.id.Dept1);
        String dept1= (dept.getText()).toString();
        student.setDepartment(dept1);
        EditText hostel= (EditText) findViewById(R.id.Hostel1);
        String hostel1= (hostel.getText()).toString();
        student.setHostel(hostel1);
        EditText degree= (EditText) findViewById(R.id.Degree1);
        String degree1= (degree.getText()).toString();
        student.setHostel(degree1);
        setContentView(R.layout.activity_main3);
    }

    public void cricket(View view){
            //code to check if this checkbox is checked!
            CheckBox checkBox = (CheckBox)view;
            if(checkBox.isChecked()){
                interests.add("cricket");
            }
    }

    public void football(View view){
        //code to check if this checkbox is checked!
        CheckBox checkBox = (CheckBox)view;
        if(checkBox.isChecked()){
            interests.add("football");
        }
    }

    public void tennis(View view){
        //code to check if this checkbox is checked!
        CheckBox checkBox = (CheckBox)view;
        if(checkBox.isChecked()){
            interests.add("tennis");
        }
    }

    public void squash(View view){
        //code to check if this checkbox is checked!
        CheckBox checkBox = (CheckBox)view;
        if(checkBox.isChecked()){
            interests.add("squash");
        }
    }
    public void swimming(View view){
        //code to check if this checkbox is checked!
        CheckBox checkBox = (CheckBox)view;
        if(checkBox.isChecked()){
            interests.add("swimming");
        }
    }

    public void carrom(View view){
        //code to check if this checkbox is checked!
        CheckBox checkBox = (CheckBox)view;
        if(checkBox.isChecked()){
            interests.add("carrom");
        }
    }

    public void chess(View view){
        //code to check if this checkbox is checked!
        CheckBox checkBox = (CheckBox)view;
        if(checkBox.isChecked()){
            interests.add("chess");
        }
    }

    public void music(View view){
        //code to check if this checkbox is checked!
        CheckBox checkBox = (CheckBox)view;
        if(checkBox.isChecked()){
            interests.add("music");
        }
    }

    public void enjoy(View view){
        student.setinterests(interests);
        student.setName(username);
        student.setCount(1);
        mDatabaseReference.push().setValue(student);
        display();
    }

    @Override
    protected void onResume(){
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mAuthStateListener != null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
        }
//        student=null;
        deletelistener();
    }
    public void display(){

        final List<String> interests = student.getinterests();
//        meventListener = new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                events eventobtained = dataSnapshot.getValue(events.class);
//                event=eventobtained;
//                String eventtag=event.getInteresttag();
//                for(int i=0;i<interests.size();i++){
//                    if(interests.get(i)==eventtag){
//                        eventsList.add(event);
//                    }
//                }
//            }
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//            }
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//            }
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        };
////        meventReference.addChildEventListener(meventListener);
        if(student.gettype().equals("student")) {
            setContentView(R.layout.activity_main_stu);
//            GestureActivity myactivity = new GestureActivity();
//            myactivity.organizer(eventsList, student);
            //setContentView(R.layout.activity_gesture);
        }
        else if(student.gettype().equals("coordinator")){
//            GesturecoActivity myactivity = new GesturecoActivity();
//            myactivity.organizer(eventsList, student);
            //setContentView(R.layout.activity_gesture);
        }
    }


    private void calllistener() {
//        if(mChildEventListener==null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    instistudent studentobtained = dataSnapshot.getValue(instistudent.class);
                    if ((studentobtained.getName()).equals(username)) {
                        student = studentobtained;
                        if(student.getcount()==1)
                            display();
                    }
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            };
            mDatabaseReference.addChildEventListener(mChildEventListener);
//        }
    }
    private void deletelistener() {
        if (mChildEventListener != null) {
            mDatabaseReference.removeEventListener(mChildEventListener);
            mChildEventListener = null;

        }
            if (meventListener != null) {
                meventReference.removeEventListener(meventListener);
                meventListener = null;
            }
                student=new instistudent();
            interests=new ArrayList<>();
            event=new events();
            eventsList=new ArrayList<>();

    }
}
