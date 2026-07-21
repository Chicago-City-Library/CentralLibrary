

   
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

    }

    public void setPublisher(String publisher) {

    }

    public String getIssn() {

    }

    public void setIssn(String issn) {

    }

    public int getVolume() {

    }

    public void setVolume(int volume) {

    }

    public int getIssueNumber() {

    }

    public void setIssueNumber(int issueNumber) {

    }

    public LocalDate getPublicationDate() {

    }

    public void setPublicationDate(LocalDate publicationDate) {

    }


    // Searchable Methods
    // (Required by Searchable interface)

    @Override
    public boolean matchesKeyword(String keyword) {

    }

    @Override
    public String[] getSearchableFields() {

    }


    // LibraryItem Methods
    // (Abstract methods to override)

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