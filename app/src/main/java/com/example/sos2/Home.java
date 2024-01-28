// Home.java
package com.example.sos2;

//import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Home extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//       Button GotoReg=findViewById(R.id.btnreg1);
//        GotoReg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i=new Intent(Home.this, SendVoicemsg.class);
//                startActivity(i);
//            }
//        });
        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);

        // Check if the user is not logged in
        if (!sharedPreferences.getBoolean("isLoggedIn", false)) {
            // If not logged in, go to Login Activity
            goToLoginActivity();
        }

        // Initialize UI components
//        Button btnLogout = findViewById(R.id.btnlogout);
//
//        btnLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Call the logout method when the button is clicked
//                logout();
//            }
//        });

        // Add a button to navigate to the Contact activity
       CardView btnAddContact = findViewById(R.id.addcontact);
        CardView contactus=findViewById(R.id.cardContactus);
        CardView aboutus=findViewById(R.id.cardaboutus);
        contactus.setOnClickListener(v -> {
            Intent intent1 = new Intent(Home.this, Contactus.class);
            startActivity(intent1);
        });
        aboutus.setOnClickListener(v -> {
            Intent intent2 = new Intent(Home.this, Aboutus.class);
            startActivity(intent2);
        });
        btnAddContact.setOnClickListener(v -> {
            // Navigate to the Contact activity
            Intent intent = new Intent(Home.this, Contact.class);
            startActivity(intent);
        });
    }

   public void logout() {
        // Update login status in SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", false);
        editor.apply();

        // Navigate back to the login screen
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }

    private void goToLoginActivity() {
        // Navigate to Login Activity
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }
}
