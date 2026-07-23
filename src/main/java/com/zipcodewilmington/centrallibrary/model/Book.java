package com.zipcodewilmington.centrallibrary.model;

//import java.time.LocalDate;

public class Book extends LibraryItem implements Reservable {


    // Instance Variables

    private String author;
    //private String language;
    //private String subjects;
    //private String locc;
    //private String bookshelves;
    //private LocalDate issued;
    //private String mediaType;
    private String isbn;
    private int pages;
    private String genre;
    private boolean reserved;
    private LibraryMember reservedBy;


    // Constructor

    public Book(String id,
            String title,
            String location,
            String author,
            //String language,
            //String subjects,
            //String locc,
            //String bookshelves,
            //LocalDate issued,
            String isbn,
            int pages,
            //String type) {
            String genre) {

    super(id, title, location);

    this.author = author;
    //this.language = language;
    //this.subject = subject;
    //this.locc = locc;
    //this.bookshelve = bookshelves;
    //this.issued = issued;
    this.isbn = isbn;
    this.pages = pages;
    this.genre = genre;
    //this.type = type;

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
        this.isbn = isbn;

    }

    public int getPages() {
        return pages;

    }

    public void setPages(int pages) {
        if (page > 0) {
        this.pages = pages;
        }
    }

    public String getGenre() {
        return genre;

    }

    public void setGenre(String genre) {
        this.genre = genre;

    }


    // Reservable Methods

    @Override
    public void reserve(LibraryMember member) {
        if (!reserved) {
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


    // Searchable Methods

    @Override
    public boolean matchesKeyword(String keyword) {
        for (String field : getSearchableFields()) {
            if (field != null &&
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
            author,
            //language,
            //subjects,
            //locc,
            //bookshelves,
            genre,
            isbn
        };

    }


    // LibraryItem Abstract Methods

    @Override
    public double calculateLateFee(int daysLate) {
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

}