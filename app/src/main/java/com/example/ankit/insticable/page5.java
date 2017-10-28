package com.example.ankit.insticable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * The type Page 5.
 */
public class page5 extends AppCompatActivity {
    /**
     * The Mystudent.
     */
    instistudent mystudent=new instistudent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Toast.makeText(getApplicationContext(), "muchbefore!", Toast.LENGTH_SHORT).show();
        super.onCreate(savedInstanceState);
        //Toast.makeText(getApplicationContext(), "before!", Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_page5);
        //Toast.makeText(getApplicationContext(), "now!", Toast.LENGTH_SHORT).show();
        Intent intent = getIntent();
        mystudent = (instistudent) intent.getExtras().getSerializable("mystudent");
        RecyclerView mRecyclerView;
        Myadapter adapter;
        RecyclerView.LayoutManager mLayoutManager;
        mRecyclerView = (RecyclerView) findViewById(R.id.interrv);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        adapter = new Myadapter(mystudent,page5.this);
        mRecyclerView.setAdapter(adapter);
        Button button1= (Button) findViewById(R.id.sixth);
        button1.setOnClickListener(new View.OnClickListener()
        {  @Override
        public void onClick(View v) {
            Intent intent =new Intent(page5.this, chat_interface.class);
            intent.putExtra("mystudent",mystudent);
            intent.putExtra("id1",3);
            startActivity(intent);
        }
        });
    }
}
