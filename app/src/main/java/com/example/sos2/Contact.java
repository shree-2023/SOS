//package com.example.sos2;
//import android.app.Dialog;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.os.Bundle;
//import android.telephony.SmsManager;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//import android.util.Log;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//
//import java.util.ArrayList;
//
//public class Contact extends AppCompatActivity {
//    private DatabaseReference databaseReference;
//    private FloatingActionButton btn;
//    private ContactAdapter adapter;
//    private ArrayList<Contact_Model> arrContact = new ArrayList<>();
//    private RecyclerView recyclerView; // Declare recyclerView as a class-level variable
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_contact);
//        databaseReference = FirebaseDatabase.getInstance().getReference("contacts");
//
//        // Initialize RecyclerView
//        recyclerView = findViewById(R.id.recyclercontact);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new ContactAdapter(this, arrContact);
//        recyclerView.setAdapter(adapter);
//
//        // Initialize FloatingActionButton
//        btn = findViewById(R.id.floatingActionButton2);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showDialogToAddContact();
//            }
//        });
//        // Check if the activity was started with the SEND_MESSAGES action
//        if ("SEND_MESSAGES".equals(getIntent().getAction())) {
//            sendMessagesToMultipleContacts();
//        }
//        // Register SMS delivery broadcast receiver
//        registerReceiver(sentReceiver, new IntentFilter("SMS_SENT"));
//    }
//
//    private void showDialogToAddContact() {
//        Dialog dialog = new Dialog(Contact.this);
//        dialog.setContentView(R.layout.update_contact);
//
//        EditText editText = dialog.findViewById(R.id.editName);
//        EditText editText1 = dialog.findViewById(R.id.editNum);
//        Button btnadd = dialog.findViewById(R.id.btnadd);
//
//        btnadd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String name = editText.getText().toString();
//                String number = editText1.getText().toString();
//
//                if (!name.isEmpty() && !number.isEmpty()) {
//                    // arrContact.add(new Contact_Model(name, number));
//                    // adapter.notifyItemInserted(arrContact.size() - 1);
//                    //recyclerView.scrollToPosition(arrContact.size() - 1);
//                    //dialog.dismiss();
//                    String contactId = databaseReference.push().getKey();
//                    Contact_Model contact = new Contact_Model(name, number);
//                    databaseReference.child(contactId).setValue(contact);
//                    arrContact.add(contact);
//                    adapter.notifyItemInserted(arrContact.size() - 1);
//                    recyclerView.scrollToPosition(arrContact.size() - 1);
//                    dialog.dismiss();
//                } else {
//                    Toast.makeText(Contact.this, "Please enter the contact name and number", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        dialog.show();
//    }
//
//    private void sendMessagesToMultipleContacts() {
//        // Check if there are contacts to send messages to
//        if (arrContact.isEmpty()) {
//            Toast.makeText(Contact.this, "No contacts to send messages to.", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        // Construct a message to be sent
//        String message = "Emergency.";
//        for (Contact_Model contact : arrContact) {
//            String phoneNumber = contact.getNumber();
//            sendMessage(phoneNumber, message);
//        }
//
//        // Log a message for debugging
//        Log.d("Database", "Contacts sent to Firebase");
//
//        Toast.makeText(Contact.this, "Messages sent to multiple contacts.", Toast.LENGTH_SHORT).show();
//    }
//
//
//    // Construct a message to be sent
//
//
//    private void sendMessage(String phoneNumber, String message) {
//        try {
//            SmsManager smsManager = SmsManager.getDefault();
//            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
//        } catch (Exception e) {
//            Toast.makeText(Contact.this, "Failed to send message.", Toast.LENGTH_SHORT).show();
//            e.printStackTrace();
//        }
//    }
//
//    // BroadcastReceiver to handle SMS delivery status
//    private final BroadcastReceiver sentReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            switch (getResultCode()) {
//                case AppCompatActivity.RESULT_OK:
//                    Toast.makeText(context, "SMS sent successfully!", Toast.LENGTH_SHORT).show();
//                    break;
//                case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
//                    Toast.makeText(context, "SMS sending failed.", Toast.LENGTH_SHORT).show();
//                    break;
//                case SmsManager.RESULT_ERROR_NO_SERVICE:
//                    Toast.makeText(context, "No service available.", Toast.LENGTH_SHORT).show();
//                    break;
//                case SmsManager.RESULT_ERROR_NULL_PDU:
//                    Toast.makeText(context, "Null PDU.", Toast.LENGTH_SHORT).show();
//                    break;
//                case SmsManager.RESULT_ERROR_RADIO_OFF:
//                    Toast.makeText(context, "Radio off.", Toast.LENGTH_SHORT).show();
//                    break;
//            }
//        }
//    };
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        // Unregister the BroadcastReceiver
//        unregisterReceiver(sentReceiver);
//    }
//}
// Contact.java
//package com.example.sos2;
//
//import android.app.Dialog;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.os.Bundle;
//import android.telephony.SmsManager;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//import java.util.ArrayList;
//
//public class Contact extends AppCompatActivity {
//
//    private DatabaseReference databaseReference;
//    private FloatingActionButton btn;
//    private ContactAdapter adapter;
//    private ArrayList<Contact_Model> arrContact = new ArrayList<>();
//    private RecyclerView recyclerView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_contact);
//        databaseReference = FirebaseDatabase.getInstance().getReference("contacts");
//
//        recyclerView = findViewById(R.id.recyclercontact);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new ContactAdapter(this, arrContact);
//        recyclerView.setAdapter(adapter);
//
//        btn = findViewById(R.id.floatingActionButton2);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showDialogToAddContact();
//            }
//        });
//
//        if ("SEND_MESSAGES".equals(getIntent().getAction())) {
//            sendMessagesToMultipleContacts();
//        }
//
//        registerReceiver(sentReceiver, new IntentFilter("SMS_SENT"));
//    }
//
//    private void showDialogToAddContact() {
//        Dialog dialog = new Dialog(Contact.this);
//        dialog.setContentView(R.layout.update_contact);
//
//        EditText editText = dialog.findViewById(R.id.editName);
//        EditText editText1 = dialog.findViewById(R.id.editNum);
//        Button btnadd = dialog.findViewById(R.id.btnadd);
//
//        btnadd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String name = editText.getText().toString();
//                String number = editText1.getText().toString();
//
//                if (!name.isEmpty() && !number.isEmpty()) {
//                    String contactId = databaseReference.push().getKey();
//                    Contact_Model contact = new Contact_Model(name, number);
//                    databaseReference.child(contactId).setValue(contact);
//                    arrContact.add(contact);
//                    adapter.notifyItemInserted(arrContact.size() - 1);
//                    recyclerView.scrollToPosition(arrContact.size() - 1);
//                    dialog.dismiss();
//                } else {
//                    Toast.makeText(Contact.this, "Please enter the contact name and number", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        dialog.show();
//    }
//
//    private void sendMessagesToMultipleContacts() {
//        if (arrContact.isEmpty()) {
//            Toast.makeText(Contact.this, "No contacts to send messages to.", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        String message = "Emergency.";
//        for (Contact_Model contact : arrContact) {
//            String phoneNumber = contact.getNumber();
//            sendSmsWithLocation(phoneNumber, message);
//        }
//
//        Log.d("Database", "Contacts sent to Firebase with location");
//
//        Toast.makeText(Contact.this, "Messages sent to multiple contacts with location.", Toast.LENGTH_SHORT).show();
//    }
//
//    private void sendSmsWithLocation(String phoneNumber, String message) {
//        try {
//            LocationData locationData = getCurrentLocation();
//            double latitude = locationData.getLatitude();
//            double longitude = locationData.getLongitude();
//
//            String locationMessage = "My current location is: " + "Latitude = " + latitude + ", Longitude = " + longitude;
//            message = message + "\n" + locationMessage;
//
//            SmsManager smsManager = SmsManager.getDefault();
//            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
//        } catch (Exception e) {
//            Toast.makeText(Contact.this, "Failed to send message with location.", Toast.LENGTH_SHORT).show();
//            e.printStackTrace();
//        }
//    }
//
//    private LocationData getCurrentLocation() {
//        // Implement this method to get the current location
//        // For now, let's assume a default location for testing
//        return new LocationData(37.7749, -122.4194, "TestPhoneNumber");
//    }
//
//    private final BroadcastReceiver sentReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            switch (getResultCode()) {
//                case AppCompatActivity.RESULT_OK:
//                    Toast.makeText(context, "SMS sent successfully!", Toast.LENGTH_SHORT).show();
//                    break;
//                case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
//                    Toast.makeText(context, "SMS sending failed.", Toast.LENGTH_SHORT).show();
//                    break;
//                case SmsManager.RESULT_ERROR_NO_SERVICE:
//                    Toast.makeText(context, "No service available.", Toast.LENGTH_SHORT).show();
//                    break;
//                case SmsManager.RESULT_ERROR_NULL_PDU:
//                    Toast.makeText(context, "Null PDU.", Toast.LENGTH_SHORT).show();
//                    break;
//                case SmsManager.RESULT_ERROR_RADIO_OFF:
//                    Toast.makeText(context, "Radio off.", Toast.LENGTH_SHORT).show();
//                    break;
//            }
//        }
//    };
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        unregisterReceiver(sentReceiver);
//    }
//}


// Contact.java




package com.example.sos2;

import android.app.Dialog;
//import android.content.BroadcastReceiver;
import android.content.SharedPreferences;
import android.content.Context;
import android.content.Intent;
//import android.content.IntentFilter;
import android.os.Bundle;
//import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sos2.model.Contact_Model;
import com.example.sos2.recyclerViewController.ContactAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Contact extends AppCompatActivity {

    private DatabaseReference databaseReference;
    public FloatingActionButton btnAddContact;
    //private Button btnSendMessage;
    private ContactAdapter adapter;
    public ArrayList<Contact_Model> arrContact = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        // Check for saved contacts and display them


        databaseReference = FirebaseDatabase.getInstance().getReference("contacts");


        recyclerView = findViewById(R.id.recyclercontact);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ContactAdapter(this, arrContact);
        recyclerView.setAdapter(adapter);

        btnAddContact = findViewById(R.id.floatingActionButton2);
        //   btnSendMessage = findViewById(R.id.btnSendMessage);
        Button GotoReg = findViewById(R.id.btnreg);
        GotoReg.setOnClickListener(v -> {
            Intent i = new Intent(Contact.this, Home.class);
            startActivity(i);
        });

        btnAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogToAddContact();
            }
        });
        // Firebase data retrieval code
        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrContact.clear(); // Clear the existing contacts list

                // Iterate through the dataSnapshot to retrieve contacts
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Contact_Model contact = snapshot.getValue(Contact_Model.class);
                    arrContact.add(contact);
                }

                adapter.notifyDataSetChanged(); // Notify the adapter that the data has changed
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Contact.this, "Failed to retrieve contacts from Firebase.", Toast.LENGTH_SHORT).show();
            }
        });

    }
//    DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users/userId1");


        private void showDialogToAddContact() {
            Dialog dialog = new Dialog(Contact.this);
            dialog.setContentView(R.layout.update_contact);

            EditText editText = dialog.findViewById(R.id.editName);
            EditText editText1 = dialog.findViewById(R.id.editNum);
            Button btnAdd = dialog.findViewById(R.id.btnadd);

            btnAdd.setOnClickListener(new View.OnClickListener() {
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
                        // Save the contact information in SharedPreferences
                        SharedPreferences sharedPreferences = getSharedPreferences("MyContacts", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("SAVED_CONTACT_NUMBER", number);
                        editor.apply();
                        dialog.dismiss();
                        //Log.d("MyTag", arrContact.get(0).getName());
                    } else {
                        Toast.makeText(Contact.this, "Please enter the contact name and number", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            dialog.show();
        }

    }



//
//package com.example.sos2;
//
//import android.annotation.SuppressLint;
//import android.app.Dialog;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.os.Bundle;
//import android.telephony.SmsManager;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//
//public class Contact extends AppCompatActivity {
//
//    private DatabaseReference databaseReference;
//    private FloatingActionButton btnAddContact;
// //   private Button btnSendMessage;
//    private ContactAdapter adapter;
//    private ArrayList<Contact_Model> arrContact = new ArrayList<>();
//    private RecyclerView recyclerView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_contact);
//        @SuppressLint("MissingInflatedId") Button GotoReg=findViewById(R.id.btnreg);
//        GotoReg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i=new Intent(Contact.this, Home.class);
//                startActivity(i);
//            }
//        });
//        // Initialize Firebase Database
//        databaseReference = FirebaseDatabase.getInstance().getReference("contacts");
//
//        recyclerView = findViewById(R.id.recyclercontact);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new ContactAdapter(this, arrContact);
//        recyclerView.setAdapter(adapter);
//
//
//        btnAddContact = findViewById(R.id.floatingActionButton2);
//   //     btnSendMessage = findViewById(R.id.btnSendMessage);
//
//        btnAddContact.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showDialogToAddContact();
//            }
//        });
//
////        btnSendMessage.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                sendMessagesToMultipleContacts();
////            }
////        });
////
////        if ("SEND_MESSAGES".equals(getIntent().getAction())) {
////            // If the activity was started with the SEND_MESSAGES action, show the Send Messages button
////            btnSendMessage.setVisibility(View.VISIBLE);
////        }
//
//        registerReceiver(sentReceiver, new IntentFilter("SMS_SENT"));
//
//        // Load existing contacts from Firebase Database
//        loadContactsFromFirebase();
//    }
//
//    private void showDialogToAddContact() {
//        Dialog dialog = new Dialog(Contact.this);
//        dialog.setContentView(R.layout.update_contact);
//
//        EditText editText = dialog.findViewById(R.id.editName);
//        EditText editText1 = dialog.findViewById(R.id.editNum);
//        Button btnAdd = dialog.findViewById(R.id.btnadd);
//
//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String name = editText.getText().toString();
//                String number = editText1.getText().toString();
//
//                if (!name.isEmpty() && !number.isEmpty()) {
//                    String contactId = databaseReference.push().getKey();
//                    Contact_Model contact = new Contact_Model(name, number);
//                    databaseReference.child(contactId).setValue(contact);
//                    arrContact.add(contact);
//                    adapter.notifyItemInserted(arrContact.size() - 1);
//                    recyclerView.scrollToPosition(arrContact.size() - 1);
//                    dialog.dismiss();
//                } else {
//                    Toast.makeText(Contact.this, "Please enter the contact name and number", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        dialog.show();
//    }
//
////    private void sendMessagesToMultipleContacts() {
////        if (arrContact.isEmpty()) {
////            Toast.makeText(Contact.this, "No contacts to send messages to.", Toast.LENGTH_SHORT).show();
////            return;
////        }
////
////        String message = "Emergency.";
////        for (Contact_Model contact : arrContact) {
////            String phoneNumber = contact.getNumber();
////            sendSmsWithLocation(phoneNumber, message);
////        }
////
////        Log.d("Database", "Contacts sent to Firebase with location");
////
////        Toast.makeText(Contact.this, "Messages sent to multiple contacts with location.", Toast.LENGTH_SHORT).show();
////    }
//
////    private void sendSmsWithLocation(String phoneNumber, String message) {
////        try {
////            LocationData locationData = getCurrentLocation();
////            double latitude = locationData.getLatitude();
////            double longitude = locationData.getLongitude();
////
////            String locationMessage = "My current location is: " + "Latitude = " + latitude + ", Longitude = " + longitude;
////            message = message + "\n" + locationMessage;
////
////            SmsManager smsManager = SmsManager.getDefault();
////            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
////        } catch (Exception e) {
////            Toast.makeText(Contact.this, "Failed to send message with location.", Toast.LENGTH_SHORT).show();
////            e.printStackTrace();
////        }
////    }
////
////    private LocationData getCurrentLocation() {
////        return new LocationData(37.7749, -122.4194, "TestPhoneNumber");
////    }
//
//    private final BroadcastReceiver sentReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            switch (getResultCode()) {
//                case AppCompatActivity.RESULT_OK:
//                    Toast.makeText(context, "SMS sent successfully!", Toast.LENGTH_SHORT).show();
//                    break;
//                case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
//                    Toast.makeText(context, "SMS sending failed.", Toast.LENGTH_SHORT).show();
//                    break;
//                case SmsManager.RESULT_ERROR_NO_SERVICE:
//                    Toast.makeText(context, "No service available.", Toast.LENGTH_SHORT).show();
//                    break;
//                case SmsManager.RESULT_ERROR_NULL_PDU:
//                    Toast.makeText(context, "Null PDU.", Toast.LENGTH_SHORT).show();
//                    break;
//                case SmsManager.RESULT_ERROR_RADIO_OFF:
//                    Toast.makeText(context, "Radio off.", Toast.LENGTH_SHORT).show();
//                    break;
//            }
//        }
//    };
//
//    private void loadContactsFromFirebase() {
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                arrContact.clear(); // Clear existing contacts
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    Contact_Model contact = snapshot.getValue(Contact_Model.class);
//                    arrContact.add(contact);
//                }
//                adapter.notifyDataSetChanged(); // Notify the adapter that data has changed
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Log.e("Firebase", "Error loading contacts from Firebase: " + databaseError.getMessage());
//            }
//        });
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        unregisterReceiver(sentReceiver);
//    }
//
//}
