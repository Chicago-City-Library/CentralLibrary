package com.zipcodewilmington.centrallibrary.model;

import java.time.LocalDate;

public class Periodical extends LibraryItem {

    // Instance Variables

    private String publisher;
    private String issn;
    private int volume;
    private int issueNumber;
    private LocalDate publicationDate;


    // No-argument constructor used by Jackson

    public Periodical() {
        super();
    }


    // Constructor used when manually creating periodicals

    public Periodical(
            String id,
            String title,
            String location,
            String publisher,
            String issn,
            int volume,
            int issueNumber,
            LocalDate publicationDate) {

        super(id, title, location);

        this.publisher = publisher;
        this.issn = issn;
        this.volume = volume;
        this.issueNumber = issueNumber;
        this.publicationDate = publicationDate;
    }


    // Getters & Setters

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        if (publisher != null && !publisher.isBlank()) {
            this.publisher = publisher;
        }
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        if (issn != null && !issn.isBlank()) {
            this.issn = issn;
        }
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        if (volume > 0) {
            this.volume = volume;
        }
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        if (issueNumber > 0) {
            this.issueNumber = issueNumber;
        }
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        if (publicationDate != null) {
            this.publicationDate = publicationDate;
        }
    }


    // Searchable Methods

    @Override
    public boolean matchesKeyword(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return false;
        }

        for (String field : getSearchableFields()) {
            if (field != null
                    && field.toLowerCase().contains(keyword.toLowerCase())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean matchesField(String fieldName, String keyword) {
        if (fieldName == null || keyword == null || keyword.isBlank()) {
            return false;
        }

        if (fieldName.equalsIgnoreCase("title")) {
            return getTitle() != null
                    && getTitle().toLowerCase().contains(keyword.toLowerCase());
        }

        if (fieldName.equalsIgnoreCase("publisher")) {
            return publisher != null
                    && publisher.toLowerCase().contains(keyword.toLowerCase());
        }

        if (fieldName.equalsIgnoreCase("issn")) {
            return issn != null
                    && issn.toLowerCase().contains(keyword.toLowerCase());
        }

        if (fieldName.equalsIgnoreCase("volume")) {
            return String.valueOf(volume).equals(keyword.trim());
        }

        if (fieldName.equalsIgnoreCase("issueNumber")) {
            return String.valueOf(issueNumber).equals(keyword.trim());
        }

        if (fieldName.equalsIgnoreCase("publicationDate")) {
            return publicationDate != null
                    && publicationDate.toString().contains(keyword.trim());
        }

        return false;
    }

    @Override
    public String[] getSearchableFields() {
        return new String[]{
            getTitle(),
            publisher,
            issn,
            String.valueOf(volume),
            String.valueOf(issueNumber),
            publicationDate == null ? null : publicationDate.toString()
        };
    }


    // LibraryItem Abstract Methods

    @Override
    public double calculateLateFee(int daysLate) {
        if (daysLate <= 0) {
            return 0.0;
        }

        return daysLate * 0.25;
    }

    @Override
    public int getMaxBorrowDays() {
        return 7;
    }

    @Override
    public String getItemType() {
        return "Periodical";
    }


    // Display Method

    @Override
    public String toString() {
        return "\n------------------------------------"
                + "\nType: " + getItemType()
                + "\nID: " + getId()
                + "\nTitle: " + getTitle()
                + "\nPublisher: " + publisher
                + "\nISSN: " + issn
                + "\nVolume: " + volume
                + "\nIssue Number: " + issueNumber
                + "\nPublication Date: " + publicationDate
                + "\nLocation: " + getLocation()
                + "\nAvailable: " + (isAvailable() ? "Yes" : "No")
                + "\n------------------------------------";
    }
}