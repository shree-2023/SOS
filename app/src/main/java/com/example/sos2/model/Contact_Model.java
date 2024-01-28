//package com.example.sos2;
//
//public class Contact_Model {
//    String name,number;
//    public Contact_Model(String name,String number){
//        this.name=name;
//        this.number=number;
//    }
//}
// Contact_Model.java
package com.example.sos2.model;
import java.io.Serializable;
//public class Contact_Model implements Serializable {
//    private final String name;
//    private final String number;
//
//    public Contact_Model(String name, String number) {
//        this.name = name;
//        this.number = number;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getNumber() {
//        return number;
//    }
//}

public class Contact_Model {
    private String name;
    private String number;

    public Contact_Model() {
        // Default constructor with no arguments (required by Firebase)
    }

    public Contact_Model(String name, String number) {
        this.name = name;
        this.number = number;
    }

    // Getters and setters for 'name' and 'number' fields
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


