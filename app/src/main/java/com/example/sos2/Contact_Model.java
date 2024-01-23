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
package com.example.sos2;
import java.io.Serializable;
public class Contact_Model implements Serializable {
    private final String name;
    private final String number;

    public Contact_Model(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }
}
