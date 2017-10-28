package com.example.ankit.insticable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by ankit on 14/10/17.
 */
public class page1 extends AppCompatActivity {
    /**
     * The Mystudent.
     */
    instistudent mystudent= new instistudent();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page1);
        Intent intent = getIntent();
        mystudent= (instistudent) intent.getExtras().getSerializable("mystudent");
        Button button1 = (Button) findViewById(R.id.first);
        Button button2 = (Button) findViewById(R.id.addfirst);
        String hostel=mystudent.getHostel();
        String department=mystudent.getDepartment();
        button1.setText("Hostel "+hostel);
        button2.setText(department);
        button1.setOnClickListener(new View.OnClickListener()
        {  @Override
        public void onClick(View v) {
            Intent intent =new Intent(page1.this, page2.class);
            intent.putExtra("mystudent",mystudent);
            startActivity(intent);
        }
        });
        button2.setOnClickListener(new View.OnClickListener()
        {  @Override
        public void onClick(View v) {
            Intent intent =new Intent(page1.this, page6.class);
            intent.putExtra("mystudent",mystudent);
            startActivity(intent);
        }
        });
    }
}
