package com.example.ankit.insticable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

/**
 * Created by ankit on 14/10/17.
 */

public class page1 extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page1);
        Intent intent = getIntent();
        instistudent mystudent= (instistudent) intent.getExtras().getSerializable("mystudent");
//        Button button = (Button) findViewById(R.id.first);
//        String hostel=mystudent.getHostel();
//        button.setText(hostel);
    }
}
