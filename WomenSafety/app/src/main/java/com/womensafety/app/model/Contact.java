package com.womensafety.app.model;

public class Contact {
    public String name, phoneNumber;
    public int id;

    public Contact(){

    }
    public Contact(String name, String phoneNumber, int id) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.id = id;
    }
}
