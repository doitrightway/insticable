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
import android.net.Uri;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {


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
    public List<events> eventsList = new ArrayList<>();
    int state=0;
    int check=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
//        mDatabaseReference = mFirebaseDatabase.getReference().child("users");
        meventReference = mFirebaseDatabase.getReference().child("events");


        mFirebaseAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    if(state==0) {
                        username = user.getDisplayName();
                        student.setName(username);
                        mDatabaseReference = mFirebaseDatabase.getReference().child("users").child(username);

                        mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot snapshot) {
                                if (snapshot.getValue() != null) {
                                    //user exists, do something
                                    //Toast.makeText(getApplicationContext(), "Signed out!", Toast.LENGTH_SHORT).show();
                                    calllistener();

                                } else {
                                    //user does not exist, do something else
                                    //Toast.makeText(getApplicationContext(), "Signed out!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                                    intent.putExtra("mystudent", student);
                                    startActivity(intent);
                                }
                            }
                            @Override
                            public void onCancelled(DatabaseError arg0) {
                            }
                        });


//                        mDatabaseReference.addChildEventListener(mChildEventListener);
                        //mDatabaseReference.push().setValue(student);
                        //Toast.makeText(getApplicationContext(), "Signed in!", Toast.LENGTH_SHORT).show();
                        //calllistener();
                        //Toast.makeText(getApplicationContext(), "Signed paagal!", Toast.LENGTH_SHORT).show();
                        //Toast.makeText(getApplicationContext(), "Signed merge!", Toast.LENGTH_SHORT).show();
                        //display();
                    }
//                    if (student.getcount()==0) {
//                        Toast.makeText(getApplicationContext(), "Signed out!", Toast.LENGTH_SHORT).show();
//                        Intent intent =new Intent(MainActivity.this, Main2Activity.class);
//                        intent.putExtra("mystudent",student);
//                        startActivity(intent);
//                        state=1;
//                    }
                }
                else {
                    deletelistener();
                    startActivityForResult(
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
            case R.id.group_chat_menu:
            {Intent intent =new Intent(MainActivity.this, page1.class);
                intent.putExtra("mystudent",student);
                startActivity(intent);
                return true;
            }
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
        deletelistener();
    }

    public List<events> reverse(List<events> listp)
    {
        Collections.reverse(listp);
        return listp;
    }
    public void display(){
        final List<String> interests = student.getinterests();
        eventsList=new ArrayList<>();
        meventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                event = dataSnapshot.getValue(events.class);
                assert event != null;
                List<String> interestsobtained=event.getInterests();

               for(int i=0;i<interestsobtained.size();i++) {
                    if (interests.contains(interestsobtained.get(i))) {
                        eventsList.add(event);
                        break;
                    }
                }
                display_final(reverse(eventsList));
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
        meventReference.addChildEventListener(meventListener);
    }

    public void display_final(List<events> eventsList){
        if(student.gettype().equals("student")) {
            RecyclerView mRecyclerView;
            RVAdapter adapter;
            RecyclerView.LayoutManager mLayoutManager;
            setContentView(R.layout.activity_recycle);
            mRecyclerView = (RecyclerView) findViewById(R.id.rv);
            mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);
            adapter = new RVAdapter(eventsList,MainActivity.this);
            mRecyclerView.setAdapter(adapter);

        }
        else if(student.gettype().equals("coordinator")){
            RecyclerView mRecyclerView;
            RVAdapter adapter;
            RecyclerView.LayoutManager mLayoutManager;
            setContentView(R.layout.activity_recycle_co);
            mRecyclerView = (RecyclerView) findViewById(R.id.rv_co);
            mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);
            adapter = new RVAdapter(eventsList,MainActivity.this);
            mRecyclerView.setAdapter(adapter);
            fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener()
            {  @Override
                public void onClick(View v) {
                    Intent intent =new Intent(MainActivity.this, Create_event.class);
                    startActivity(intent);
                }
            });
        }
    }


//    public void chat(View view){
//        Intent intent =new Intent(MainActivity.this, page1.class);
//        intent.putExtra("mystudent",student);
//        startActivity(intent);
//    }


    private void calllistener() {
        //Toast.makeText(getApplicationContext(), "Signed info!", Toast.LENGTH_SHORT).show();

            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        instistudent studentobtained = dataSnapshot.getValue(instistudent.class);
                        assert studentobtained != null;
                        if ((studentobtained.getName()).equals(username)) {
                            student = studentobtained;
                            //Toast.makeText(getApplicationContext(), "has child!", Toast.LENGTH_SHORT).show();
                            if(student.getcount()==1) {
                                display();
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
    }
    private void deletelistener() {
//        if (mChildEventListener != null) {
//            mDatabaseReference.removeEventListener(mChildEventListener);
//            meventReference.removeEventListener(meventListener);
//            mChildEventListener=null;
//            meventListener=null;
//            event=new events();
//            eventsList=new ArrayList<>();
//        }
    }
}
