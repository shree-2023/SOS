package com.example.sos2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class Home extends AppCompatActivity {

    private ImageButton button1;
    private ImageButton button2;
    private ImageButton button3;
    private ImageButton button4;

ImageButton Logout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Logout=findViewById(R.id.logout);
Logout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent =new Intent(Home.this,Login.class);
        startActivity(intent);
    }
});
        button1=(ImageButton)findViewById(R.id.sosbutton);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openpinfo();
            }
        });


        button2=(ImageButton)findViewById(R.id.pinfo);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openpinfo1();
            }
        });
        button3=(ImageButton)findViewById(R.id.voice);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openpinfo3();
            }
        });


        button4=(ImageButton)findViewById(R.id.safetrack);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openpinfo4();
            }
        });



        button3=(ImageButton)findViewById(R.id.settings);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home.this,more.class);
                startActivity(intent);
            }
        });

        Intent service= new Intent(Home.this,Contact.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(service);
        } else{
            startService(service);
        }
    }

    public void openpinfo(){
        Intent intent=new Intent(this,Contact.class);
        startActivity(intent);
    }

    public void openpinfo1(){
        Intent intent=new Intent(this, sosactivated.class);
        startActivity(intent);
    }
    public void openpinfo3(){
        Intent intent=new Intent(this, SendVoicemsg.class);
        startActivity(intent);
    }
    public void openpinfo4(){
        Intent intent=new Intent(this,safetrack.class);
        startActivity(intent);
    }


}