package com.example.sos2;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.telephony.SmsManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import java.util.HashMap;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

public class SendVoicemsg extends AppCompatActivity {

    private static final int REQUEST_CODE_SPEECH = 1;
    private static final String EMERGENCY_MESSAGE = "Emergency! Help needed.";

    private DatabaseReference databaseReference;
    private static final String EMERGENCY_PHONE_NUMBER = "9964300914";  // Specify the desired emergency number

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseReference = FirebaseDatabase.getInstance().getReference("contacts");
        voiceAutomation();
    }

    private void voiceAutomation() {
        // Check if the app has the RECORD_AUDIO permission
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
            // Permission granted, proceed with voice recognition
            startVoiceRecognition();
        } else {
            // Request the RECORD_AUDIO permission
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.RECORD_AUDIO}, REQUEST_CODE_SPEECH);
        }
    }

    private void startVoiceRecognition() {
        Intent voice = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        voice.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        voice.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        voice.putExtra(RecognizerIntent.EXTRA_PROMPT, "Help...");
        startActivityForResult(voice, REQUEST_CODE_SPEECH);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SPEECH && resultCode == RESULT_OK && data != null) {
            ArrayList<String> arrayList = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (!arrayList.isEmpty() && arrayList.get(0).equalsIgnoreCase("help")) {
                startLocationUpdates();
            }
        }
    }


//    private void sendEmergencySMSToSavedContacts() {
//        // Request location updates
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_LOCATION_PERMISSION);
//        } else {
//            startLocationUpdates();
//        }
//    }

    private void startLocationUpdates() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                // Once the location is changed, send the SMS with the updated location
                String currentLocation = "Latitude: " + location.getLatitude() + ", Longitude: " + location.getLongitude();
                String messageWithLocation = EMERGENCY_MESSAGE + "\nCurrent Location: " + currentLocation;
                String locationLink = getLocationLink(location.getLatitude(), location.getLongitude());
                messageWithLocation += "\nLocation Link: " + locationLink;
                sendEmergencySMSToSavedContacts(messageWithLocation);

                // Remove updates to conserve battery
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
        };

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    }

    private void sendEmergencySMSToSavedContacts(String message) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Retrieve the value as a HashMap
                    HashMap<String, Object> contactData = (HashMap<String, Object>) snapshot.getValue();

                    // Extract the phone number from the HashMap
                    if (contactData != null && contactData.containsKey("number")) {
                        String phoneNumber = (String) contactData.get("number");
                        sendSMS(message, phoneNumber);
                    }
                }
                Toast.makeText(SendVoicemsg.this, "Emergency message sent to all saved contacts", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(SendVoicemsg.this, "Failed to retrieve contacts. Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendSMS(String message, String phoneNumber) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
        } catch (Exception e) {
            Toast.makeText(this, "Failed to send emergency message to " + phoneNumber, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private String getLocationLink(double latitude, double longitude) {
        return "http://maps.google.com/maps?q=loc:" + latitude + "," + longitude;
    }

    // Define the request code for location permission
    private static final int REQUEST_CODE_LOCATION_PERMISSION = 123;

}
