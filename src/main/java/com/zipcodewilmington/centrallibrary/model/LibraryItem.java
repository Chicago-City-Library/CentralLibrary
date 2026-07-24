package com.zipcodewilmington.centrallibrary.model;
<<<<<<< HEAD


public abstract class LibraryItem implements Searchable {


    // Instance Variables

    private String id;
    private String title;
    private String location;
    private boolean isAvailable;


    // Constructor

    public LibraryItem(String id,
                   String title,
                   String location) {

    this.id = id;
    this.title = title;
    this.location = location;
    this.isAvailable = true;

    }


    // Getters & Setters

    public String getId() {
        return id;

    }

    public void setId(String id) {
        if (id != null && !id.isBlank()) {
            this.id = id;
        }
    }

    public String getTitle() {
        return title;

    }

    public void setTitle(String title) {
        if (this != null && !title.isBlank()) {
            this.title = title;
        }

    }

    public String getLocation() {
        return location;

    }

    public void setLocation(String location) {
        if (location != null && !location.isBlank()) {
            this.location = location;
        }

    }

    public boolean isAvailable() {
        return isAvailable;

    }


    // Library Methods

    public void checkOut() {
        isAvailable = false;

    }

    public void checkIn() {
        isAvailable = true;

    }


    // Object Methods
    @Override
    public String toString() {
        return getItemType() + ": " + getTitle();
    }


    // Abstract Methods
    // (Implemented by child classes)

    public abstract double calculateLateFee(int daysLate);

    public abstract int getMaxBorrowDays();

    public abstract String getItemType();

}
=======
>>>>>>> origin/mecca-personnel
