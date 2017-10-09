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
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    FloatingActionButton fab;
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
    List<String> intereststags=new ArrayList<>();
    public List<events> eventsList = new ArrayList<>();
//    private RecyclerView mRecyclerView;
//    private RVAdapter adapter;
//    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_recycle);
//        if (student.getcount()==0) {
//            setContentView(R.layout.activity_main);
//        }
//        else {
//                        TextView t= (TextView) findViewById(R.id.t1);
//                        if(student.getName().equals(username))
//            setContentView(R.layout.activity_main3);
//        }

//        RecyclerView mrecycle = (RecyclerView) findViewById(R.id.rv);
//        mrecycle.setLayoutManager(new LinearLayoutManager(this));

//        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
//
//        // use this setting to improve performance if you know that changes
//        // in content do not change the layout size of the RecyclerView
//        mRecyclerView.setHasFixedSize(true);
//
//        // use a linear layout manager
//        mLayoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
//        mAdapter = new MyAdapter(myDataset);
//        mRecyclerView.setAdapter(mAdapter);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("users");
        meventReference = mFirebaseDatabase.getReference().child("events");
//        events event1 = new events("jnnjn","jnon","gurgaon","hello","cricket");
//        events event2 = new events("jnjnj","4:5","gurgaon","hello","football");
//        events event3 = new events("jnjjnj","4:5","gurgaon","hello","tennis");
//        eventsList.add(event1);
//        eventsList.add(event2);
//        eventsList.add(event3);
//        adapter = new RVAdapter(eventsList);
//        mRecyclerView.setAdapter(adapter);
//            GestureActivity myactivity = new GestureActivity();
//            myactivity.organizer(eventsList, student);
//        setContentView(R.layout.activity_recycle);


        mFirebaseAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    username = user.getDisplayName();
                    calllistener();
                    if (student.getcount()==0) {
                        setContentView(R.layout.activity_main);
                    }
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

//
    public void coordinator(View view){
        //code to check if this checkbox is checked!
        boolean checked = ((CheckBox) view).isChecked();
        if(checked) {
            student.settype("coordinator");        }
        else{
            student.settype("");        }
    }

    public void student(View view){
        //code to check if this checkbox is checked!
        boolean checked = ((CheckBox) view).isChecked();
        if(checked) {
            student.settype("student");        }
        else{
            student.settype("");
        }
    }

//    public void coordinator(View view){
//        student.settype("coordinator");
//        //setContentView(R.layout.activity_main2);
//        //startActivity(new Intent(MainActivity.this, Main2Activity.class));
//    }
//
//    public void student(View view){
//        student.settype("student");
////        String usename= Fire;
//        //setContentView(R.layout.activity_main2);
//        //startActivity(new Intent(MainActivity.this, Main2Activity.class));
//    }
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
        CheckBox cordi=(CheckBox) findViewById(R.id.cordi);
        CheckBox stu=(CheckBox) findViewById(R.id.stu);
        if(cordi.isChecked())
        {
            student.settype("coordinator");
        }
        else {
            student.settype("student");
        }
        setContentView(R.layout.activity_main3);
    }

    public void cricket(View view){
            //code to check if this checkbox is checked!
        boolean checked = ((CheckBox) view).isChecked();
        if(checked) {
            interests.add("cricket");
        }
        else{
            interests.remove("cricket");
        }
    }

    public void football(View view){
        //code to check if this checkbox is checked!
        boolean checked = ((CheckBox) view).isChecked();
        if(checked) {
            interests.add("football");
        }
        else{
            interests.remove("football");
        }
    }

    public void tennis(View view){
        //code to check if this checkbox is checked!
        boolean checked = ((CheckBox) view).isChecked();
        if(checked) {
            interests.add("tennis");
        }
        else{
            interests.remove("tennis");
        }
    }

    public void squash(View view) {
        //code to check if this checkbox is checked!
        boolean checked = ((CheckBox) view).isChecked();
        if (checked) {
            interests.add("squash");
        } else {
            interests.remove("squash");

            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            //mRecyclerView.setHasFixedSize(true);

            // use a linear layout manager
        }
    }
    public void swimming(View view){
        //code to check if this checkbox is checked!
        boolean checked = ((CheckBox) view).isChecked();
        if(checked) {
            interests.add("swimming");
        }
        else{
            interests.remove("swimming");
        }
    }

    public void carrom(View view){
        //code to check if this checkbox is checked!
        boolean checked = ((CheckBox) view).isChecked();
        if(checked) {
            interests.add("carrom");
        }
        else{
            interests.remove("carrom");
        }
    }

    public void chess(View view){
        //code to check if this checkbox is checked!
        boolean checked = ((CheckBox) view).isChecked();
        if(checked) {
            interests.add("chess");
        }
        else{
            interests.remove("chess");
        }
    }

    public void music(View view){
        //code to check if this checkbox is checked!
        boolean checked = ((CheckBox) view).isChecked();
        if(checked) {
            interests.add("music");
        }
        else{
            interests.remove("music");
        }
    }

    public void enjoy(View view){
        student.setinterests(interests);
        student.setName(username);
        student.setCount(1);
        mDatabaseReference.push().setValue(student);
        //display();
        calllistener();
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
        //Toast.makeText(getApplicationContext(),"first" , Toast.LENGTH_SHORT).show();
        eventsList=new ArrayList<>();
        meventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //Toast.makeText(getApplicationContext(),"second" , Toast.LENGTH_SHORT).show();
                events eventobtained = dataSnapshot.getValue(events.class);
                event=eventobtained;
                //assert event != null;
                //Toast.makeText(getApplicationContext(),"second" , Toast.LENGTH_SHORT).show();
                List<String> interestsobtained=event.getInterests();
//                for(int i=0;i<interests.size();i++){
//                    if(interests.get(i).equals(eventtag)){
//                        eventsList.add(event);
//                    }
//                }
//                Toast.makeText(getApplicationContext(),"buffalo" , Toast.LENGTH_SHORT).show();
                for(int i=0;i<interestsobtained.size();i++) {
                    if (interests.contains(interestsobtained.get(i))) {
                        //Toast.makeText(getApplicationContext(), "event", Toast.LENGTH_SHORT).show();
                        eventsList.add(event);
                        break;
                    }
                }
                Toast.makeText(getApplicationContext(), "beforedisplayfinal", Toast.LENGTH_SHORT).show();
                display_final(eventsList);
                Toast.makeText(getApplicationContext(), "afterdisplayfinal", Toast.LENGTH_SHORT).show();
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
        //Toast.makeText(getApplicationContext(),"after" , Toast.LENGTH_SHORT).show();
        meventReference.addChildEventListener(meventListener);
        Toast.makeText(getApplicationContext(), "aftelistener", Toast.LENGTH_SHORT).show();
        //display_final(eventsList);
//        if(student.gettype().equals("student")) {
//            Toast.makeText(getApplicationContext(),"hello" , Toast.LENGTH_SHORT).show();
//            RecyclerView mRecyclerView;
//            RVAdapter adapter;
//            RecyclerView.LayoutManager mLayoutManager;
//            setContentView(R.layout.activity_recycle);
//            mRecyclerView = (RecyclerView) findViewById(R.id.rv);
//
//            // use this setting to improve performance if you know that changes
//            // in content do not change the layout size of the RecyclerView
//            //mRecyclerView.setHasFixedSize(true);
//
//            // use a linear layout manager
//            mLayoutManager = new LinearLayoutManager(this);
//            mRecyclerView.setLayoutManager(mLayoutManager);
//            adapter = new RVAdapter(eventsList);
//            mRecyclerView.setAdapter(adapter);
//
//            //         mRecyclerView.setAdapter(adapter);
////            RVAdapter adapter = new RVAdapter(eventsList);
////            rv.setAdapter(adapter);
//////            GestureActivity myactivity = new GestureActivity();
//////            myactivity.organizer(eventsList, student);
//
//
//
//
//        }
//        else if(student.gettype().equals("coordinator")){
//            RecyclerView mRecyclerView;
//            RVadapter_co adapter;
//            RecyclerView.LayoutManager mLayoutManager;
////            GesturecoActivity myactivity = new GesturecoActivity();
////            myactivity.organizer(eventsList, student);
//            //setContentView(R.layout.activity_gesture);
//            setContentView(R.layout.activity_recycle_co);
//            mRecyclerView = (RecyclerView) findViewById(R.id.rv_co);
//
//            // use this setting to improve performance if you know that changes
//            // in content do not change the layout size of the RecyclerView
//            //mRecyclerView.setHasFixedSize(true);
//
//            // use a linear layout manager
//            mLayoutManager = new LinearLayoutManager(this);
//            mRecyclerView.setLayoutManager(mLayoutManager);
//            adapter = new RVadapter_co(eventsList);
//            mRecyclerView.setAdapter(adapter);
//        }
    }

    public void display_final(List<events> eventsList){
        if(student.gettype().equals("student")) {
            //Toast.makeText(getApplicationContext(),"hello" , Toast.LENGTH_SHORT).show();
            RecyclerView mRecyclerView;
            RVAdapter adapter;
            RecyclerView.LayoutManager mLayoutManager;
            setContentView(R.layout.activity_recycle);
            mRecyclerView = (RecyclerView) findViewById(R.id.rv);
            mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);
            adapter = new RVAdapter(eventsList);
            mRecyclerView.setAdapter(adapter);

            //         mRecyclerView.setAdapter(adapter);
//            RVAdapter adapter = new RVAdapter(eventsList);
//            rv.setAdapter(adapter);
////            GestureActivity myactivity = new GestureActivity();
////            myactivity.organizer(eventsList, student);




        }
        else if(student.gettype().equals("coordinator")){
            RecyclerView mRecyclerView;
            RVadapter_co adapter;
            RecyclerView.LayoutManager mLayoutManager;
//            GesturecoActivity myactivity = new GesturecoActivity();
//            myactivity.organizer(eventsList, student);
            //setContentView(R.layout.activity_gesture);
            setContentView(R.layout.activity_recycle_co);
            mRecyclerView = (RecyclerView) findViewById(R.id.rv_co);

            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            //mRecyclerView.setHasFixedSize(true);

            // use a linear layout manager
            mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);
            adapter = new RVadapter_co(eventsList);
            mRecyclerView.setAdapter(adapter);
            fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener()
            {

                @Override
                public void onClick(View v) {
                    setContentView(R.layout.activity_main_cre);
                }
            });
        }
    }

//    public void createevent(View view){
//        setContentView(R.layout.activity_main_cre);
//
//    }

    public void cricket_cre(View view){
        //code to check if this checkbox is checked!
        boolean checked = ((CheckBox) view).isChecked();
        if(checked) {
            intereststags.add("cricket");
        }
        else{
            intereststags.remove("cricket");
        }
    }

    public void football_cre(View view){
        //code to check if this checkbox is checked!
        boolean checked = ((CheckBox) view).isChecked();
        if(checked) {
            intereststags.add("football");
        }
        else{
            intereststags.remove("football");
        }
    }

    public void tennis_cre(View view){
        //code to check if this checkbox is checked!
        boolean checked = ((CheckBox) view).isChecked();
        if(checked) {
            intereststags.add("tennis");
        }
        else{
            intereststags.remove("tennis");
        }
    }

    public void squash_cre(View view){
        //code to check if this checkbox is checked!
        boolean checked = ((CheckBox) view).isChecked();
        if(checked) {
            intereststags.add("squash");
        }
        else{
            intereststags.remove("squash");
        }
    }
    public void swimming_cre(View view){
        //code to check if this checkbox is checked!
        boolean checked = ((CheckBox) view).isChecked();
        if(checked) {
            intereststags.add("swimming");
        }
        else{
            intereststags.remove("swimming");
        }
    }

    public void carrom_cre(View view){
        //code to check if this checkbox is checked!
        boolean checked = ((CheckBox) view).isChecked();
        if(checked) {
            intereststags.add("carrom");
        }
        else{
            intereststags.remove("carrom");
        }
    }

    public void chess_cre(View view){
        //code to check if this checkbox is checked!
        boolean checked = ((CheckBox) view).isChecked();
        if(checked) {
            intereststags.add("chess");
        }
        else{
            intereststags.remove("chess");
        }
    }

    public void music_cre(View view){
        //code to check if this checkbox is checked!
        boolean checked = ((CheckBox) view).isChecked();
        if(checked) {
            intereststags.add("music");
        }
        else{
            intereststags.remove("music");
        }
    }

    public void push_cre(View view){
        EditText name=(EditText) findViewById(R.id.namecre);
        EditText date = (EditText) findViewById(R.id.datecre);
        EditText venue = (EditText) findViewById(R.id.venuecre);
        EditText time = (EditText) findViewById(R.id.timecre);
        EditText description = (EditText) findViewById(R.id.descriptioncre);
        events event = new events();
        event.setName(name.getText().toString());
        event.setDate(date.getText().toString());
        event.setTime(time.getText().toString());
        event.setVenue(venue.getText().toString());
        event.setDescription(description.getText().toString());
        event.setInterests(intereststags);
        meventReference.push().setValue(event);
        eventsList=new ArrayList<>();
        display();
    }

    private void calllistener() {
//        if(mChildEventListener==null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    instistudent studentobtained = dataSnapshot.getValue(instistudent.class);
                    assert studentobtained != null;
                    if ((studentobtained.getName()).equals(username)) {
                        //Toast.makeText(getApplicationContext(),"student" , Toast.LENGTH_SHORT).show();
                        student = studentobtained;
                        if(student.getcount()==1) {
                            display();
//                        display_final(eventsList);
                            Toast.makeText(getApplicationContext(), "aftedisplay", Toast.LENGTH_SHORT).show();
                        }
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
            //Toast.makeText(getApplicationContext(),"stupid" , Toast.LENGTH_SHORT).show();

//        }
    }
    private void deletelistener() {
        if (mChildEventListener != null) {
            mDatabaseReference.removeEventListener(mChildEventListener);
            meventReference.removeEventListener(meventListener);
            mChildEventListener=null;
            meventListener=null;
            student=new instistudent();
            interests=new ArrayList<>();
            intereststags=new ArrayList<>();
            event=new events();
            eventsList=new ArrayList<>();
        }
    }
}
