package com.example.ankit.insticable;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EdgeEffect;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by ankit on 4/10/17.
 */

public class creator extends Activity{
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference meventreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        meventreference = mFirebaseDatabase.getReference().child("events");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cre);
        EditText date = (EditText) findViewById(R.id.datecre);
        EditText venue = (EditText) findViewById(R.id.venuecre);
        EditText time = (EditText) findViewById(R.id.timecre);
        events event = new events();
        event.setDate(date.toString());
        event.setTime(time.toString());
        event.setVenue(venue.toString());
        meventreference.push().setValue(event);
    }
}
