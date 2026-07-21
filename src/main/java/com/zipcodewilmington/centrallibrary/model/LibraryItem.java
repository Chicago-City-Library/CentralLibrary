


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

    }

    public void setId(String id) {

    }

    public String getTitle() {

    }

    public void setTitle(String title) {

    }

    public String getLocation() {

    }

    public void setLocation(String location) {

    }

    public boolean isAvailable() {

    }


    // Library Methods

    public void checkOut() {

    }

    public void checkIn() {

    }


    // Abstract Methods
    // (Implemented by child classes)

    public abstract double calculateLateFee(int daysLate);

    public abstract int getMaxBorrowDays();

    public abstract String getItemType();

}