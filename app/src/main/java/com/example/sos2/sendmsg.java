//package com.example.sos2;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//
//public class sendmsg extends AppCompatActivity {
//    Button b;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home);
//
//        // Initialize the button inside the onCreate method
//        b = findViewById(R.id.button);
//
//        b.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(sendmsg.this, Contact.class);
//                startActivity(i);
//            }
//        });
//    }
//}

// sendmsg.java
package com.example.sos2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class sendmsg extends AppCompatActivity {
    Button b;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendmsg);

        // Initialize the button inside the onCreate method
        b = findViewById(R.id.button);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessageToContacts(); // Call the method to send messages
            }
        });
    }

    private void sendMessageToContacts() {
        Intent intent = new Intent(sendmsg.this, Contact.class);
        intent.setAction("SEND_MESSAGES");
        startActivity(intent);
    }
}
