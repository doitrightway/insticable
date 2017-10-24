package com.example.ankit.insticable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class page6 extends AppCompatActivity {
    instistudent mystudent= new instistudent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page6);
        Intent intent = getIntent();
        mystudent= (instistudent) intent.getExtras().getSerializable("mystudent");
        Button button1 = (Button) findViewById(R.id.addsecond);
        Button button2 = (Button) findViewById(R.id.addthird);
        button2.setText(mystudent.getDegree());
        button1.setOnClickListener(new View.OnClickListener()
        {  @Override
        public void onClick(View v) {
            Intent intent =new Intent(page6.this, chat_interface.class);
            intent.putExtra("mystudent",mystudent);
            intent.putExtra("id1",-1);
            startActivity(intent);
        }
        });
        button2.setOnClickListener(new View.OnClickListener()
        {  @Override
        public void onClick(View v) {
            Intent intent =new Intent(page6.this, page7.class);
            intent.putExtra("mystudent",mystudent);
            startActivity(intent);
        }
        });
    }
}
