package com.zipcodewilmington.centrallibrary.model;


public class DVD extends LibraryItem implements Reservable {


    // Instance Variables (Owned by DVD)

    private String director;
    private int duration;
    private String rating;
    private String genre;
    private boolean reserved;
    private LibraryMember reservedBy;


    // Constructor

    public DVD(String id,
           String title,
           String location,
           String director,
           int duration,
           String rating,
           String genre) {

    super(id, title, location);

    this.director = director;
    this.duration = duration;
    this.rating = rating;
    this.genre = genre;

    }


    // Getters & Setters

    public String getDirector() {
        return director;

    }

    public void setDirector(String director) {
        if (director != null && !director.isBlank()) {
            this.director = director;
        }
    }

    public int getDuration() {
        return duration;

    }

    public void setDuration(int duration) {
        if (duration > 0) {
            this.duration = duration;
        }
    }

    public String getRating() {
        return rating;

    }

    public void setRating(String rating) {
        if (rating != null && !rating.isBlank()) {
            this.rating = rating;
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
         reserved = true;
         reservedBy = member;
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
            director,
            genre
        };

    }


    // LibraryItem Abstract Methods

    @Override
    public double calculateLateFee(int daysLate) {
        return daysLate * 1.00;

    }

    @Override
    public int getMaxBorrowDays() {
        return 7;

    }

    @Override
    public String getItemType() {
        return "DVD";

    }

}