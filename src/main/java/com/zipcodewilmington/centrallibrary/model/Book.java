package com.zipcodewilmington.centrallibrary.model;

import com.zipcodewilmington.centrallibrary.Interface.Reservable;

public class Book extends LibraryItem implements Reservable {

    // Instance Variables

    private String author;
    private String isbn;
    private int pages;
    private String genre;
    private boolean reserved;
    private LibraryMember reservedBy;


    // Constuctor for Jackson

    public Book() {
        super();
    }


    // Constructor used when manually creating books

    public Book(
            String id,
            String title,
            String location,
            String author,
            String isbn,
            int pages,
            String genre) {

        super(id, title, location);

        this.author = author;
        this.isbn = isbn;
        this.pages = pages;
        this.genre = genre;
    }


    // Getters & Setters

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (author != null && !author.isBlank()) {
            this.author = author;
        }
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        if (isbn != null && !isbn.isBlank()) {
            this.isbn = isbn;
        }
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        if (pages > 0) {
            this.pages = pages;
        }
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        if (genre != null && !genre.isBlank()) {
            this.genre = genre;
        }
    }


    // Reservable Methods

    @Override
    public void reserve(LibraryMember member) {
        if (!reserved && member != null) {
            reserved = true;
            reservedBy = member;
        }
    }

    @Override
    public void cancelReserve() {
        reserved = false;
        reservedBy = null;
    }

    @Override
    public boolean isReserved() {
        return reserved;
    }

    @Override
    public LibraryMember getReservedBy() {
        return reservedBy;
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

        if (fieldName.equalsIgnoreCase("author")) {
            return author != null
                    && author.toLowerCase().contains(keyword.toLowerCase());
        }

        if (fieldName.equalsIgnoreCase("isbn")) {
            return isbn != null
                    && isbn.toLowerCase().contains(keyword.toLowerCase());
        }

        if (fieldName.equalsIgnoreCase("genre")) {
            return genre != null
                    && genre.toLowerCase().contains(keyword.toLowerCase());
        }

        return false;
    }

    @Override
    public String[] getSearchableFields() {
        return new String[]{
            getTitle(),
            author,
            genre,
            isbn
        };
    }


    // LibraryItem Abstract Methods

    @Override
    public double calculateLateFee(int daysLate) {
        if (daysLate <= 0) {
            return 0.0;
        }

        return daysLate * 0.50;
    }

    @Override
    public int getMaxBorrowDays() {
        return 14;
    }

    @Override
    public String getItemType() {
        return "Book";
    }


    // Display Method

    @Override
    public String toString() {
        return "\n------------------------------------"
                + "\nType: " + getItemType()
                + "\nID: " + getId()
                + "\nTitle: " + getTitle()
                + "\nAuthor: " + author
                + "\nISBN: " + isbn
                + "\nPages: " + pages
                + "\nGenre: " + genre
                + "\nLocation: " + getLocation()
                + "\nAvailable: " + (isAvailable() ? "Yes" : "No")
                + "\nReserved: " + (reserved ? "Yes" : "No")
                + "\n------------------------------------";
    }
}