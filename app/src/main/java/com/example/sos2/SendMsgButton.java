// SendMsgButton.java
package com.example.sos2;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sos2.model.Contact_Model;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SendMsgButton extends AppCompatActivity {

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendmsg);

        databaseReference = FirebaseDatabase.getInstance().getReference("contacts");

        Button btnSendMessage = findViewById(R.id.sendmsgbutton);
        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessageToContacts();
            }
        });
    }

    private void sendMessageToContacts() {
        // Fetch contacts directly from Firebase Realtime Database
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Contact_Model> contactList = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Contact_Model contact = snapshot.getValue(Contact_Model.class);

                    if (contact != null) {
                        contactList.add(contact);
                    }
                }

                // Check if there are any contacts
                if (contactList.isEmpty()) {
                    Toast.makeText(SendMsgButton.this, "No contacts available to send messages.", Toast.LENGTH_SHORT).show();
                } else {
                    // Replace this with your desired message content
                    String messageContent = "This is your emergency message.";

                    // Iterate through the contacts and send the message
                    for (Contact_Model contact : contactList) {
                        String phoneNumber = String.valueOf(contact.getNumber());
                        sendSMS(messageContent, phoneNumber);
                    }

                    Toast.makeText(SendMsgButton.this, "Emergency message sent to all contacts.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
                Toast.makeText(SendMsgButton.this, "Failed to fetch contacts from Firebase.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendSMS(String message, String phoneNumber) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
        } catch (Exception e) {
            Toast.makeText(this, "Failed to send emergency message. Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
