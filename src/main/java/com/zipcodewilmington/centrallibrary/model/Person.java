package com.zipcodewilmington.centrallibrary.model;

public class Person {

    // Instance Variables
    private String name;
    private int age;
    private String email;
    private String phoneNumber;

    // No-Argument Constructor (for Jackson)
    public Person() {
    }

    // Constructor
    public Person(String name,
            int age,
            String email,
            String phoneNumber) {

        this.name = name;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.isBlank()) {
            this.name = name;
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age >= 0) {
            this.age = age;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null && !email.isBlank()) {
            this.email = email;
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber != null && !phoneNumber.isBlank()) {
            this.phoneNumber = phoneNumber;
        }
    }

    // Display Method
    @Override
    public String toString() {
        return "Name: " + name
                + "\nAge: " + age
                + "\nEmail: " + email
                + "\nPhone: " + phoneNumber;
    }
}
