package com.zipcodewilmington.centrallibrary;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.zipcodewilmington.centrallibrary.model.Address;
import com.zipcodewilmington.centrallibrary.model.LibraryMember;
import com.zipcodewilmington.centrallibrary.model.Librarian;

import com.zipcodewilmington.centrallibrary.model.Address;
import com.zipcodewilmington.centrallibrary.model.Book;
import com.zipcodewilmington.centrallibrary.model.DVD;
import com.zipcodewilmington.centrallibrary.model.Librarian;
import com.zipcodewilmington.centrallibrary.model.LibraryItem;
import com.zipcodewilmington.centrallibrary.model.LibraryMember;
import com.zipcodewilmington.centrallibrary.model.Periodical;

/**
 * Runs the Central Library console menu.
 */
public class MainApplication {

    // Stores the people who can use the library system.
    private final List<LibraryMember> members = new ArrayList<>();

// Stores the librarians working in the system.
    private final List<Librarian> librarians = new ArrayList<>();
    // Reads information typed by the user.
    private final Scanner scanner = new Scanner(System.in);

    // Holds and manages all library items.
    private final Library library = new Library();

    // Starts the application.
    public static void main(String[] args) {

        MainApplication app = new MainApplication();

        // Temporary data until all JSON loaders are finished.
        app.library.loadData();

        app.loadPeopleData();

        // Starts the main menu.
        app.start();
    }

    // Displays the main menu until the user exits.
    public void start() {

        boolean running = true;

        while (running) {

            System.out.println();
            System.out.println("====================================");
            System.out.println("          CENTRAL LIBRARY");
            System.out.println("====================================");
            System.out.println("1: View checked-out items");
            System.out.println("2: Search");
            System.out.println("3: Reserve an item");
            System.out.println("4: Check-out/Check-in");
            System.out.println("5: Exit");

            int choice = readInt("Choose an option: ");

            switch (choice) {

                case 1:
                    viewCheckedOutItems();
                    break;

                case 2:
                    searchMenu();
                    break;

                case 3:
                    reservationMenu();
                    break;

                case 4:
                    checkoutMenu();
                    break;

                case 5:
                    System.out.println();
                    System.out.println(
                            "Thank you for using Central Library."
                    );

                    running = false;
                    break;

                default:
                    System.out.println(
                            "Invalid option. Please choose 1 through 5."
                    );
            }
        }

        scanner.close();
    }

    // Displays every item that is currently checked out.
    public void viewCheckedOutItems() {

        System.out.println();
        System.out.println("CHECKED-OUT ITEMS");
        System.out.println("------------------------------------");

        List<LibraryItem> checkedOutItems
                = library.getCheckedOutItems();

        if (checkedOutItems.isEmpty()) {

            System.out.println(
                    "There are currently no checked-out items."
            );

            return;
        }

        displayResults(
                checkedOutItems,
                "There are currently no checked-out items."
        );
    }

    // Displays the different search choices.
    public void searchMenu() {

        boolean searching = true;

        while (searching) {

            System.out.println();
            System.out.println("SEARCH");
            System.out.println("------------------------------------");
            System.out.println("1: Search all items");
            System.out.println("2: Search books");
            System.out.println("3: Search DVDs");
            System.out.println("4: Search periodicals");
            System.out.println("5: Back");

            int choice = readInt("Choose an option: ");

            switch (choice) {

                case 1:
                    searchAllItems();
                    break;

                case 2:
                    bookSearchMenu();
                    break;

                case 3:
                    dvdSearchMenu();
                    break;

                case 4:
                    periodicalSearchMenu();
                    break;

                case 5:
                    System.out.println(
                            "Returning to main menu..."
                    );

                    searching = false;
                    break;

                default:
                    System.out.println(
                            "Invalid option. Please choose 1 through 5."
                    );
            }
        }
    }

    // Searches every item using one keyword.
    // A blank keyword displays all items.
    public void searchAllItems() {

        System.out.println();

        System.out.print(
                "Enter a keyword or press Enter to view all items: "
        );

        String keyword = scanner.nextLine();

        List<LibraryItem> results
                = library.searchAll(keyword);

        displayResults(
                results,
                "No matching items found."
        );
    }

    // Displays the book search options.
    public void bookSearchMenu() {

        boolean searchingBooks = true;

        while (searchingBooks) {

            System.out.println();
            System.out.println("BOOK SEARCH");
            System.out.println("------------------------------------");
            System.out.println("1: Search by title");
            System.out.println("2: Search by author");
            System.out.println("3: Search by ISBN");
            System.out.println("4: Search by genre");
            System.out.println("5: Back");

            int choice = readInt("Choose an option: ");

            switch (choice) {

                case 1:
                    promptAndSearchByField(
                            Book.class,
                            "title",
                            "Enter title: ",
                            "No books matching that title."
                    );
                    break;

                case 2:
                    promptAndSearchByField(
                            Book.class,
                            "author",
                            "Enter author: ",
                            "No books matching that author."
                    );
                    break;

                case 3:
                    promptAndSearchByField(
                            Book.class,
                            "isbn",
                            "Enter ISBN: ",
                            "No books matching that ISBN."
                    );
                    break;

                case 4:
                    promptAndSearchByField(
                            Book.class,
                            "genre",
                            "Enter genre: ",
                            "No books matching that genre."
                    );
                    break;

                case 5:
                    System.out.println(
                            "Returning to search menu..."
                    );

                    searchingBooks = false;
                    break;

                default:
                    System.out.println(
                            "Invalid option. Please choose 1 through 5."
                    );
            }
        }
    }

    // Displays the DVD search options.
    public void dvdSearchMenu() {

        boolean searchingDvds = true;

        while (searchingDvds) {

            System.out.println();
            System.out.println("DVD SEARCH");
            System.out.println("------------------------------------");
            System.out.println("1: Search by title");
            System.out.println("2: Search by director");
            System.out.println("3: Search by genre");
            System.out.println("4: Search by duration");
            System.out.println("5: Back");

            int choice = readInt("Choose an option: ");

            switch (choice) {

                case 1:
                    promptAndSearchByField(
                            DVD.class,
                            "title",
                            "Enter DVD title: ",
                            "No DVDs matching that title."
                    );
                    break;

                case 2:
                    promptAndSearchByField(
                            DVD.class,
                            "director",
                            "Enter director: ",
                            "No DVDs matching that director."
                    );
                    break;

                case 3:
                    promptAndSearchByField(
                            DVD.class,
                            "genre",
                            "Enter genre: ",
                            "No DVDs matching that genre."
                    );
                    break;

                case 4:
                    searchDvdByDuration();
                    break;

                case 5:
                    System.out.println(
                            "Returning to search menu..."
                    );

                    searchingDvds = false;
                    break;

                default:
                    System.out.println(
                            "Invalid option. Please choose 1 through 5."
                    );
            }
        }
    }

    // Searches DVDs using a number of minutes.
    public void searchDvdByDuration() {

        int duration = readInt(
                "Enter duration in minutes: "
        );

        searchItemsByField(
                DVD.class,
                "duration",
                String.valueOf(duration),
                "No DVDs matching that duration."
        );
    }

    // Displays the periodical search options.
    public void periodicalSearchMenu() {

        boolean searchingPeriodicals = true;

        while (searchingPeriodicals) {

            System.out.println();
            System.out.println("PERIODICAL SEARCH");
            System.out.println("------------------------------------");
            System.out.println("1: Search by title");
            System.out.println("2: Search by publisher");
            System.out.println("3: Search by volume");
            System.out.println("4: Search by ISSN");
            System.out.println("5: Back");

            int choice = readInt("Choose an option: ");

            switch (choice) {

                case 1:
                    promptAndSearchByField(
                            Periodical.class,
                            "title",
                            "Enter title: ",
                            "No periodicals matching that title."
                    );
                    break;

                case 2:
                    promptAndSearchByField(
                            Periodical.class,
                            "publisher",
                            "Enter publisher: ",
                            "No periodicals matching that publisher."
                    );
                    break;

                case 3:
                    searchPeriodicalByVolume();
                    break;

                case 4:
                    promptAndSearchByField(
                            Periodical.class,
                            "issn",
                            "Enter ISSN: ",
                            "No periodicals matching that ISSN."
                    );
                    break;

                case 5:
                    System.out.println(
                            "Returning to search menu..."
                    );

                    searchingPeriodicals = false;
                    break;

                default:
                    System.out.println(
                            "Invalid option. Please choose 1 through 5."
                    );
            }
        }
    }

    // Searches periodicals using a volume number.
    public void searchPeriodicalByVolume() {

        int volume = readInt(
                "Enter volume: "
        );

        searchItemsByField(
                Periodical.class,
                "volume",
                String.valueOf(volume),
                "No periodicals matching that volume."
        );
    }

    // Asks the user for text and sends it to the shared search method.
    public void promptAndSearchByField(
            Class<? extends LibraryItem> itemType,
            String fieldName,
            String prompt,
            String notFoundMessage) {

        System.out.print(prompt);

        String keyword = scanner.nextLine();

        searchItemsByField(
                itemType,
                fieldName,
                keyword,
                notFoundMessage
        );
    }

    // Searches one item type and one field.
    public void searchItemsByField(
            Class<? extends LibraryItem> itemType,
            String fieldName,
            String keyword,
            String notFoundMessage) {

        List<LibraryItem> results
                = library.searchByField(
                        itemType,
                        fieldName,
                        keyword
                );

        displayResults(
                results,
                notFoundMessage
        );
    }

    // Prints search results.
    // Prints a message when the list is empty.
    private void displayResults(
            List<LibraryItem> results,
            String notFoundMessage) {

        if (results.isEmpty()) {

            System.out.println(notFoundMessage);
            return;
        }

        for (LibraryItem item : results) {
            System.out.println(item);
        }
    }

    private void reservationMenu() {

        boolean manageReservations = true;

        while (manageReservations) {

            System.out.println();
            System.out.println("RESERVATIONS");
            System.out.println("1: Reserve an item");
            System.out.println("2: Cancel a reservation");
            System.out.println("3: Return to main menu");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    reserveItem();
                    break;

                case "2":
                    cancelReservation();
                    break;

                case "3":
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void reserveItem() {

        System.out.print("Enter member ID: ");
        String memberId = scanner.nextLine();

        LibraryMember member = findMemberById(memberId);

        if (member == null) {

            System.out.println(
                    "No library member was found with that ID."
            );

            return;
        }

        System.out.print("Enter the item ID: ");
        String itemId = scanner.nextLine();

        boolean reserved
                = library.reserveItem(itemId, member);

        if (reserved) {

            System.out.println(
                    "The item was reserved successfully for "
                    + member.getName()
                    + "."
            );

        } else {

            System.out.println(
                    "The item could not be reserved."
            );
        }
    }

    private void cancelReservation() {

        System.out.print(
                "Enter the item ID to cancel: "
        );

        String itemId = scanner.nextLine();

        boolean cancelled
                = library.cancelReservation(itemId);

        if (cancelled) {

            System.out.println(
                    "The reservation was cancelled."
            );

        } else {

            System.out.println(
                    "The reservation could not be cancelled."
            );
        }
    }

    private void checkoutMenu() {

        boolean managingCheckouts = true;

        while (managingCheckouts) {

            System.out.println();
            System.out.println("CHECK-OUT / CHECK-IN");
            System.out.println("------------------------------------");
            System.out.println("1: Check out an item");
            System.out.println("2: Check in an item");
            System.out.println("3: Return to main menu");

            int choice = readInt("Choose an option: ");

            switch (choice) {

                case 1:
                    checkOutItem();
                    break;

                case 2:
                    checkInItem();
                    break;

                case 3:
                    managingCheckouts = false;
                    break;

                default:
                    System.out.println(
                            "Invalid option. Please choose 1 through 3."
                    );
            }
        }
    }

    private void checkOutItem() {

        System.out.print("Enter member ID: ");
        String memberId = scanner.nextLine();

        LibraryMember member = findMemberById(memberId);

        if (member == null) {

            System.out.println(
                    "No library member was found with that ID."
            );

            return;
        }

        System.out.print("Enter item ID: ");
        String itemId = scanner.nextLine();

        boolean checkedOut
                = library.checkOutItem(itemId, member);

        if (checkedOut) {

            System.out.println(
                    "The item was checked out successfully."
            );

        } else {

            System.out.println(
                    "The item could not be checked out."
            );
        }
    }

    private void checkInItem() {

        System.out.print("Enter item ID: ");
        String itemId = scanner.nextLine();

        boolean checkedIn
                = library.checkInItem(itemId);

        if (checkedIn) {

            System.out.println(
                    "The item was checked in successfully."
            );

        } else {

            System.out.println(
                    "The item could not be checked in."
            );
        }
    }
// Finds a member using their library member ID.

    private LibraryMember findMemberById(String memberId) {

        if (memberId == null || memberId.isBlank()) {
            return null;
        }

        for (LibraryMember member : members) {

            if (member.getMemberId()
                    .equalsIgnoreCase(memberId.trim())) {

                return member;
            }
        }

        return null;
    }

    // Safely reads a whole number.
    // It keeps asking until the user enters a valid number.
    private int readInt(String prompt) {

        while (true) {

            System.out.print(prompt);

            String input = scanner.nextLine();

            try {

                return Integer.parseInt(input);

            } catch (NumberFormatException exception) {

                System.out.println(
                        "Please enter a valid whole number."
                );
            }
        }
    }

    private void loadPeopleData() {

        // Prevents duplicate people if this method is called twice.
        members.clear();
        librarians.clear();

        // ===========================
        // Create Address Objects
        // ===========================
        Address address1 = new Address(
                "456 Oak St",
                "Bookville",
                "MD",
                "12347"
        );

        Address address2 = new Address(
                "654 Maple St",
                "Media",
                "PA",
                "12346"
        );

        Address address3 = new Address(
                "789 Pine Dr",
                "Bear",
                "DE",
                "19701"
        );

        Address address4 = new Address(
                "101 Cedar Ln",
                "New Castle",
                "DE",
                "19720"
        );

        // ===========================
        // Create Library Members
        // ===========================
        LibraryMember member1 = new LibraryMember(
                "Alice Johnson",
                25,
                "alice@email.com",
                "555-1234",
                "M001",
                "2024-01-15",
                0.00,
                address1
        );

        LibraryMember member2 = new LibraryMember(
                "Bob Wilson",
                35,
                "bob@email.com",
                "555-4321",
                "M002",
                "2023-08-10",
                8.50,
                address2
        );

        LibraryMember member3 = new LibraryMember(
                "Kris Younger",
                38,
                "kris.younger@email.com",
                "555-6789",
                "M003",
                "2025-02-20",
                3.25,
                address3
        );

        LibraryMember member4 = new LibraryMember(
                "Maya Carter",
                29,
                "maya.carter@email.com",
                "555-9876",
                "M004",
                "2024-11-03",
                0.00,
                address4
        );

        // Saves the members so other menus can access them.
        members.add(member1);
        members.add(member2);
        members.add(member3);
        members.add(member4);

        // ===========================
        // Create Librarians
        // ===========================
        Librarian librarian1 = new Librarian(
                "Sarah Mitchell",
                42,
                "sarah.mitchell@library.org",
                "555-2001",
                "EMP1001",
                "Circulation",
                58000.00
        );

        Librarian librarian2 = new Librarian(
                "David Brooks",
                39,
                "david.brooks@library.org",
                "555-2002",
                "EMP1002",
                "Reference",
                61500.00
        );

        Librarian librarian3 = new Librarian(
                "Jennifer Adams",
                36,
                "jennifer.adams@library.org",
                "555-2003",
                "EMP1003",
                "Children's Services",
                56750.00
        );

        Librarian librarian4 = new Librarian(
                "Marcus Reed",
                47,
                "marcus.reed@library.org",
                "555-2004",
                "EMP1004",
                "Archives",
                63250.00
        );

        // Saves the librarians for later use.
        librarians.add(librarian1);
        librarians.add(librarian2);
        librarians.add(librarian3);
        librarians.add(librarian4);
    }
}
