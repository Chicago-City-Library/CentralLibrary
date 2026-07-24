package com.zipcodewilmington.centrallibrary.model;

import java.time.LocalDate;

public class Periodical extends LibraryItem {


    // Instance Variables

    private String publisher;
    private String issn;
    private int volume;
    private int issueNumber;
    private LocalDate publicationDate;


    // Constructor

    public Periodical(String id,
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
        this.publisher = publisher;

    }

    public String getIssn() {
        return issn;

    }

    public void setIssn(String issn) {
        this.issn = issn;

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
        this.publicationDate = publicationDate;

    }


    // Searchable Methods
    // (Required by Searchable interface)

    @Override
    public boolean matchesKeyword(String keyword) {
        for (String field : getSearchableFields()) {
            if (field != null&&
                field.toLowerCase().contains(keyword.toLowerCase())) {

                return true;
            }
        }

        return false;

    }

    @Override
    public String[] getSearchableFields() {
        return new String[] {
            getTitle(),
            publisher,
            issn
        
        };

    }


    // LibraryItem Methods
    // (Abstract methods to override)

    @Override
    public double calculateLateFee(int daysLate) {
        return daysLate * .25;

    }

    @Override
    public int getMaxBorrowDays() {
        return 7;

    }

    @Override
    public String getItemType() {
        return "Periodical";

    }

}
