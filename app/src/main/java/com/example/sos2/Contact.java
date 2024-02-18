package com.example.sos2;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sos2.model.Contact_Model;
import com.example.sos2.recyclerViewController.ContactAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Contact extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private FloatingActionButton btn;
    private ContactAdapter adapter;
    private ArrayList<Contact_Model> arrContact = new ArrayList<>();
    private RecyclerView recyclerView; // Declare recyclerView as a class-level variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        databaseReference = FirebaseDatabase.getInstance().getReference("contacts");

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclercontact);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ContactAdapter(this, arrContact);
        recyclerView.setAdapter(adapter);
Button gotohome=findViewById(R.id.btnreg);
gotohome.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(Contact.this, Home.class);
        startActivity(intent);
    }
});
        // Initialize FloatingActionButton
        btn = findViewById(R.id.floatingActionButton2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogToAddContact();
            }
        });
        // Check if the activity was started with the SEND_MESSAGES action
        if ("SEND_MESSAGES".equals(getIntent().getAction())) {
            sendMessagesToMultipleContacts();
        }
        // Register SMS delivery broadcast receiver
        registerReceiver(sentReceiver, new IntentFilter("SMS_SENT"));
    }

    private void showDialogToAddContact() {
        Dialog dialog = new Dialog(Contact.this);
        dialog.setContentView(R.layout.update);

        EditText editText = dialog.findViewById(R.id.editName);
        EditText editText1 = dialog.findViewById(R.id.editNum);
        Button btnadd = dialog.findViewById(R.id.btnadd);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();
                String number = editText1.getText().toString();

                if (!name.isEmpty() && !number.isEmpty()) {
                    String contactId = databaseReference.push().getKey();
                    Contact_Model contact = new Contact_Model(name, number);
                    databaseReference.child(contactId).setValue(contact);
                    arrContact.add(contact);
                    adapter.notifyItemInserted(arrContact.size() - 1);
                    recyclerView.scrollToPosition(arrContact.size() - 1);
                    dialog.dismiss();
                } else {
                    Toast.makeText(Contact.this, "Please enter a valid 10-digit number", Toast.LENGTH_SHORT).show();
                                }
            }
        });

        dialog.show();
    }

    private void sendMessagesToMultipleContacts() {
        // Check if there are contacts to send messages to
        if (arrContact.isEmpty()) {
            Toast.makeText(Contact.this, "No contacts to send messages to.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Construct a message to be sent
        String message = "Emergency.";
        for (Contact_Model contact : arrContact) {
            String phoneNumber = contact.getNumber();
            sendMessage(phoneNumber, message);
        }

        // Log a message for debugging
        Log.d("Database", "Contacts sent to Firebase");

        Toast.makeText(Contact.this, "Messages sent to multiple contacts.", Toast.LENGTH_SHORT).show();
    }


    // Construct a message to be sent


    private void sendMessage(String phoneNumber, String message) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
        } catch (Exception e) {
            Toast.makeText(Contact.this, "Failed to send message.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    // BroadcastReceiver to handle SMS delivery status
    private final BroadcastReceiver sentReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (getResultCode()) {
                case AppCompatActivity.RESULT_OK:
                    Toast.makeText(context, "SMS sent successfully!", Toast.LENGTH_SHORT).show();
                    break;
                case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                    Toast.makeText(context, "SMS sending failed.", Toast.LENGTH_SHORT).show();
                    break;
                case SmsManager.RESULT_ERROR_NO_SERVICE:
                    Toast.makeText(context, "No service available.", Toast.LENGTH_SHORT).show();
                    break;
                case SmsManager.RESULT_ERROR_NULL_PDU:
                    Toast.makeText(context, "Null PDU.", Toast.LENGTH_SHORT).show();
                    break;
                case SmsManager.RESULT_ERROR_RADIO_OFF:
                    Toast.makeText(context, "Radio off.", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Unregister the BroadcastReceiver
        unregisterReceiver(sentReceiver);
    }
}
