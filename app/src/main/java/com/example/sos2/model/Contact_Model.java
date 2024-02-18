package com.example.sos2.model;

public class Contact_Model {
    private String id;
    private String name;
    private String number;
    public Contact_Model() {
        // Empty constructor
    }

    public Contact_Model(String contactId, String name, String number) {
        this.id = contactId;
        this.name = name;
        this.number = number;

    }

    public Contact_Model(String name, String number) {
        this.id = null;
        this.name = name;
        this.number = number;
    }

    // Getters and setters for 'name' and 'number' fields
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}

