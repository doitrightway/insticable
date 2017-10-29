package com.example.ankit.insticable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

/**
 * The type Page 7.
 * This class is  used to diaplay page 3 of the
 * interconnected chat groups without the hostel being considered
 */
public class page7 extends AppCompatActivity {
    /**
     * The Mystudent.
     */
    instistudent mystudent = new instistudent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page7);
        Intent intent = getIntent();
        mystudent = (instistudent) intent.getExtras().getSerializable("mystudent");
        RecyclerView mRecyclerView;
        Mynewadapter adapter;
        RecyclerView.LayoutManager mLayoutManager;
        mRecyclerView = (RecyclerView) findViewById(R.id.addinterrv);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        adapter = new Mynewadapter(mystudent, page7.this);
        mRecyclerView.setAdapter(adapter);
        Button button1 = (Button) findViewById(R.id.addfourth);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(page7.this, chat_interface.class);
                intent.putExtra("mystudent", mystudent);
                intent.putExtra("id1", -2);
                startActivity(intent);
            }
        });
    }
}

