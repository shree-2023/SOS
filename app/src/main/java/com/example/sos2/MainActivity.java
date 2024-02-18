package com.example.sos2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // on below line we are configuring our window to full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
TextView textView= findViewById(R.id.textView3);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this, Login.class);
                startActivity(i);
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // creating a new intent
//                Intent i = new Intent(MainActivity.this, SendVoicemsg.class);
                Intent i = new Intent(MainActivity.this, Home.class);
                // starting a new activity.
                startActivity(i);
                finish();
            }
        }, 2000);

    }
}
