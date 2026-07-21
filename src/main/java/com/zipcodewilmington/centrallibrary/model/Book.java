


public class Book extends LibraryItem implements Reservable {


    // Instance Variables

    private String author;
    private String isbn;
    private int pages;
    private String genre;


    // Constructor

    public Book(String id,
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

    }

    public void setAuthor(String author) {

    }

    public String getIsbn() {

    }

    public void setIsbn(String isbn) {

    }

    public int getPages() {

    }

    public void setPages(int pages) {

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