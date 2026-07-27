package com.zipcodewilmington.centrallibrary.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.zipcodewilmington.centrallibrary.Interface.Searchable;

public abstract class LibraryItem implements Searchable {

    // Instance Variables
    private String id;
    private String title;
    private String location;
    private boolean isAvailable;
    private LibraryMember checkedOutBy;
    private LocalDate dueDate;

    // No-argument constructor used by Jackson when creating objects from JSON
    protected LibraryItem() {
        this.isAvailable = true;
    }

    // Constructor used when creating items manually in Java
    public LibraryItem(String id, String title, String location) {
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
        if (title != null && !title.isBlank()) {
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

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public LibraryMember getCheckedOutBy() {
        return checkedOutBy;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
    // Calculates the current late fee for this item.
    public double getCurrentLateFee() {

        if (dueDate == null || !LocalDate.now().isAfter(dueDate)) {
            return 0.0;
        }

        int daysLate = (int) ChronoUnit.DAYS
        .between(dueDate, LocalDate.now());

        return calculateLateFee(daysLate);
    }

    // Library Methods
    public boolean checkOut(LibraryMember member) {

        if (!isAvailable || member == null) {
            return false;
        }

        isAvailable = false;
        checkedOutBy = member;
        dueDate = LocalDate.now()
                .plusDays(getMaxBorrowDays());

        return true;
    }

    public void checkIn() {

        isAvailable = true;
        checkedOutBy = null;
        dueDate = null;
    }

    // Object Methods
    @Override
    public String toString() {
        return getItemType() + ": " + getTitle();
    }

    // Abstract Methods
    // Each child class must provide its own implementation.
    @Override
    public abstract boolean matchesField(String fieldName, String keyword);

    public abstract double calculateLateFee(int daysLate);

    public abstract int getMaxBorrowDays();

    public abstract String getItemType();
}
