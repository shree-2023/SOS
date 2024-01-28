//package com.example.sos2;
//
//import static androidx.core.location.LocationManagerCompat.getCurrentLocation;
//
//import androidx.appcompat.app.AppCompatActivity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.telephony.SmsManager;
//import android.util.Log;
//import android.widget.Toast;
//import android.widget.Button;
//import android.view.View;
//import java.util.ArrayList;
//
//public class SendVoicemsg extends AppCompatActivity {
//    private ArrayList<Contact_Model> arrContact = new ArrayList<>();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_send_voicemsg);
//        // Retrieve saved contacts and send messages
//        sendMessagesToSavedContacts();
//
//        Intent intent = getIntent();
//        if (intent != null) {
//            String contactName = intent.getStringExtra("CONTACT_NAME");
//            String contactNumber = intent.getStringExtra("CONTACT_NUMBER");
//
//            // Now you have the contact information, you can use it to send a message
//            // For example, you can use the contactNumber to send an SMS
//            sendSmsWithLocation(contactNumber, "Your message content");
//        } else {
//            Toast.makeText(this, "No contact information received.", Toast.LENGTH_SHORT).show();
//        }
//
//        // Add the button click listener
//        Button GotoReg=findViewById(R.id.regbtn);
//        GotoReg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i=new Intent(SendVoicemsg.this, Register.class);
//                startActivity(i);
//            }
//        });
//        Button sendmsg = findViewById(R.id.sendmsg);
//        sendmsg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Retrieve saved contacts and send messages
//                sendMessagesToAllContacts();
//            }
//        });
//    }
//
//    private void sendMessagesToSavedContacts() {
//        // Retrieve saved contacts from SharedPreferences
//        SharedPreferences sharedPreferences = getSharedPreferences("MyContacts", Context.MODE_PRIVATE);
//        String savedContactNumber = sharedPreferences.getString("SAVED_CONTACT_NUMBER", null);
//
//        if (savedContactNumber != null) {
//            // Send message to the saved contact
//            sendSmsWithLocation(savedContactNumber, "Your message content");
//
//            // Clear the saved contact after sending the message
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.remove("SAVED_CONTACT_NUMBER");
//            editor.apply();
//        }
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
//        }
//        catch (Exception e) {
//            Toast.makeText(SendVoicemsg.this, "Failed to send message with location.", Toast.LENGTH_SHORT).show();
//            e.printStackTrace();
//        }
//    }
//
//    private LocationData getCurrentLocation() {
//        return new LocationData(37.7749, -122.4194, "TestPhoneNumber");
//    }
//
//    private void sendMessagesToAllContacts() {
//        if (arrContact.isEmpty()) { } else {
//            //   requestPermissions(new String[]{Manifest.permission.SEND_SMS}, SMS_PERMISSION_REQUEST_CODE);
//            // }
//            Toast.makeText(SendVoicemsg.this, "No contacts to send messages to.", Toast.LENGTH_SHORT).show();
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
//        Toast.makeText(SendVoicemsg.this, "Messages sent to multiple contacts with location.", Toast.LENGTH_SHORT).show();
//    }
//}
//package com.example.sos2;
//
//import android.Manifest;
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.content.pm.PackageManager;
//import android.location.Location;
//import android.location.LocationListener;
//import android.location.LocationManager;
//import android.os.Bundle;
//import android.telephony.SmsManager;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//
//import java.util.ArrayList;
//
//public class SendVoicemsg extends AppCompatActivity {
//    public ArrayList<Contact_Model> arrContact = new ArrayList<>();
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_send_voicemsg);
//        Log.d("Array Type", "Type of arrContact: " + arrContact.getClass().getComponentType().getName());
//    }
//
//        // Retrieve saved contacts and send messages
//        sendMessagesToSavedContacts();
//
//        Intent intent = getIntent();
//        if (intent != null) {
//            String contactName = intent.getStringExtra("CONTACT_NAME");
//            String contactNumber = intent.getStringExtra("CONTACT_NUMBER");
//
//            // Now you have the contact information, you can use it to send a message
//            // For example, you can use the contactNumber to send an SMS
//            sendSmsWithLocation(contactNumber, "Your message content");
//        } else {
//            Toast.makeText(this, "No contact information received.", Toast.LENGTH_SHORT).show();
//        }
//
//        // Add the button click listener
//        Button GotoReg = findViewById(R.id.regbtn);
//        GotoReg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(SendVoicemsg.this, Register.class);
//                startActivity(i);
//            }
//        });
//
//        Button sendmsg = findViewById(R.id.sendmsg);
//        sendmsg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Retrieve saved contacts and send messages
//                sendMessagesToAllContacts();
//            }
//        });
//    }
//
//    private void sendMessagesToSavedContacts() {
//        // Retrieve saved contacts from SharedPreferences
//        SharedPreferences sharedPreferences = getSharedPreferences("MyContacts", Context.MODE_PRIVATE);
//        String savedContactNumber = sharedPreferences.getString("SAVED_CONTACT_NUMBER", null);
//
//        if (savedContactNumber != null) {
//            // Send message to the saved contact
//            sendSmsWithLocation(savedContactNumber, "Your message content");
//
//            // Clear the saved contact after sending the message
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.remove("SAVED_CONTACT_NUMBER");
//            editor.apply();
//        }
//    }
//
//    private void sendSmsWithLocation(String phoneNumber, String originalMessage) {
//        try {
//            final String[] message = {originalMessage}; // Declare as a final array
//
//            // Get the location manager
//            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//
//            // Check for location permission
//            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//                    == PackageManager.PERMISSION_GRANTED) {
//                // Request location updates
//                locationManager.requestLocationUpdates(
//                        LocationManager.GPS_PROVIDER,
//                        0,
//                        0,
//                        new LocationListener() {
//                            @Override
//                            public void onLocationChanged(Location location) {
//                                // Get latitude and longitude
//                                double latitude = location.getLatitude();
//                                double longitude = location.getLongitude();
//
//                                // Create location message
//                                String locationMessage = "My current location is: " +
//                                        "Latitude = " + latitude + ", Longitude = " + longitude;
//
//                                // Append location message to the original message
//                                message[0] = message[0] + "\n" + locationMessage;
//
//                                // Send SMS
//                                SmsManager smsManager = SmsManager.getDefault();
//                                smsManager.sendTextMessage(phoneNumber, null, message[0], null, null);
//
//                                // Remove location updates after sending the message (optional)
//                                locationManager.removeUpdates(this);
//                            }
//
//                            @Override
//                            public void onStatusChanged(String provider, int status, Bundle extras) {
//                            }
//
//                            @Override
//                            public void onProviderEnabled(String provider) {
//                            }
//
//                            @Override
//                            public void onProviderDisabled(String provider) {
//                            }
//                        }
//                );
//            } else {
//                // Handle the case when location permission is not granted
//                Toast.makeText(SendVoicemsg.this, "Location permission not granted.", Toast.LENGTH_SHORT).show();
//            }
//        } catch (Exception e) {
//            Toast.makeText(SendVoicemsg.this, "Failed to send message with location.", Toast.LENGTH_SHORT).show();
//            e.printStackTrace();
//        }
//    }
//
//    private void sendMessagesToAllContacts() {
//        if (arrContact.isEmpty()) {
//            // Handle the case when there are no contacts
//            Toast.makeText(SendVoicemsg.this, "No contacts to send messages to.", Toast.LENGTH_SHORT).show();
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
//        Toast.makeText(SendVoicemsg.this, "Messages sent to multiple contacts with location.", Toast.LENGTH_SHORT).show();
//    }
//}

package com.example.sos2;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.sos2.model.Contact_Model;

import java.util.ArrayList;

public class SendVoicemsg extends AppCompatActivity {
    private ArrayList<Contact_Model> arrContact = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_voicemsg);
        // Retrieve saved contacts and send messages
        sendMessagesToSavedContacts();

        Intent intent = getIntent();
        if (intent != null) {
            String contactName = intent.getStringExtra("CONTACT_NAME");
            String contactNumber = intent.getStringExtra("CONTACT_NUMBER");

            // Now you have the contact information, you can use it to send a message
            // For example, you can use the contactNumber to send an SMS
            sendSmsWithLocation(contactNumber, "Your message content");
        } else {
            Toast.makeText(this, "No contact information received.", Toast.LENGTH_SHORT).show();
        }

        // Add the button click listener
        Button GotoReg = findViewById(R.id.regbtn);
        GotoReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SendVoicemsg.this, Register.class);
                startActivity(i);
            }
        });

        Button sendmsg = findViewById(R.id.sendmsg);
        sendmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve saved contacts and send messages
                sendMessagesToAllContacts();
            }
        });
    }

    private void sendMessagesToSavedContacts() {
        // Retrieve saved contacts from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyContacts", Context.MODE_PRIVATE);
        String savedContactNumber = sharedPreferences.getString("SAVED_CONTACT_NUMBER", null);

        if (savedContactNumber != null) {
            // Send message to the saved contact
            sendSmsWithLocation(savedContactNumber, "Your message content");

            // Clear the saved contact after sending the message
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("SAVED_CONTACT_NUMBER");
            editor.apply();
        }
    }

    private void sendSmsWithLocation(String phoneNumber, String originalMessage) {
        try {
            final String[] message = {originalMessage}; // Declare as a final array

            // Get the location manager
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

            // Check for location permission
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                // Request location updates
                locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        0,
                        0,
                        new LocationListener() {
                            @Override
                            public void onLocationChanged(Location location) {
                                // Get latitude and longitude
                                double latitude = location.getLatitude();
                                double longitude = location.getLongitude();

                                // Create location message
                                String locationMessage = "My current location is: " +
                                        "Latitude = " + latitude + ", Longitude = " + longitude;

                                // Append location message to the original message
                                message[0] = message[0] + "\n" + locationMessage;

                                // Send SMS
                                SmsManager smsManager = SmsManager.getDefault();
                                smsManager.sendTextMessage(phoneNumber, null, message[0], null, null);

                                // Remove location updates after sending the message (optional)
                                locationManager.removeUpdates(this);
                            }

                            @Override
                            public void onStatusChanged(String provider, int status, Bundle extras) {
                            }

                            @Override
                            public void onProviderEnabled(String provider) {
                            }

                            @Override
                            public void onProviderDisabled(String provider) {
                            }
                        }
                );
            }
        } catch (Exception e) {
            Toast.makeText(SendVoicemsg.this, "Failed to send message with location.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void sendMessagesToAllContacts() {
        if (arrContact.isEmpty()) {
            // Handle the case when there are no contacts
            Toast.makeText(SendVoicemsg.this, "No contacts to send messages to.", Toast.LENGTH_SHORT).show();
            return;
        }

        String message = "Emergency.";
        for (Contact_Model contact : arrContact) {
            String phoneNumber = contact.getNumber();
            sendSmsWithLocation(phoneNumber, message);
        }

        Log.d("Database", "Contacts sent to Firebase with location");

        Toast.makeText(SendVoicemsg.this, "Messages sent to multiple contacts with location.", Toast.LENGTH_SHORT).show();
    }
}
