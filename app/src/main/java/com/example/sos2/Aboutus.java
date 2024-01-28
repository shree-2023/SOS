package com.example.sos2;

import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class Aboutus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
// Add this line in the onCreate method of Aboutus activity
        setTheme(R.style.AppThemeWithActionBar);

        // Inflate the custom action bar layout
        View customActionBarView = LayoutInflater.from(this).inflate(R.layout.activity_aboutus, null);

        // Set the custom action bar view
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(customActionBarView);
        TextView actionBarIcon = customActionBarView.findViewById(R.id.actionBarIcon);


        setContentView(R.layout.activity_aboutus);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
