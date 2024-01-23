package com.example.sos2;

// LocationData.java
public class LocationData {
    private double latitude;
    private double longitude;
    private String phoneNumber;

    public LocationData() {
        // Default constructor required for Firestore
    }

    public LocationData(double latitude, double longitude, String phoneNumber) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.phoneNumber = phoneNumber;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
