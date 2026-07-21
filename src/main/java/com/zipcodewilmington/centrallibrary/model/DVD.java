


public class DVD extends LibraryItem implements Reservable {


    // Instance Variables (Owned by DVD)

    private String director;
    private int duration;
    private String rating;
    private String genre;


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

    }

    public void setDirector(String director) {

    }

    public int getDuration() {

    }

    public void setDuration(int duration) {

    }

    public String getRating() {

    }

    public void setRating(String rating) {

    }

    public String getGenre() {

    }

    public void setGenre(String genre) {

    }


    // Reservable Methods

    @Override
    public void reserve(LibraryMember member) {

    }

    @Override
    public void cancelReserve() {

    }

    @Override
    public boolean isReserved() {

    }


    // Searchable Methods

    @Override
    public boolean matchesKeyword(String keyword) {

    }

    @Override
    public String[] getSearchableFields() {

    }


    // LibraryItem Abstract Methods

    @Override
    public double calculateLateFee(int daysLate) {

    }

    @Override
    public int getMaxBorrowDays() {

    }

    @Override
    public String getItemType() {

    }

}