package com.zipcodewilmington.centrallibrary;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.zipcodewilmington.centrallibrary.model.Address;
import com.zipcodewilmington.centrallibrary.model.LibraryMember;
import com.zipcodewilmington.centrallibrary.model.Librarian;

import com.zipcodewilmington.centrallibrary.model.Book;
import com.zipcodewilmington.centrallibrary.model.DVD;
import com.zipcodewilmington.centrallibrary.model.LibraryItem;
import com.zipcodewilmington.centrallibrary.model.Periodical;

/**
 * Created by n3pjk on 6/9/2025.
 */
public class MainApplication {

    private Scanner scanner = new Scanner(System.in);

    private List<LibraryItem> items = new ArrayList<>();

    public static void main(String[] args) {
        MainApplication app = new MainApplication();
        app.loadSampleData();
        app.start();
    }

    public void loadSampleData() {

        items.add(new Book(
                "B001",
                "The Shining",
                "A1",
                "Stephen King",
                "9780385121675",
                447,
                "Horror"
        ));

        items.add(new Book(
                "B002",
                "The Hobbit",
                "A2",
                "J.R.R. Tolkien",
                "9780547928227",
                310,
                "Fantasy"
        ));
    }

    public void start() {

// Create Address Objects

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
// Create Library Member Objects
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

// ===========================
// Create Librarian Objects
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
        boolean running = true;
        while (running) {
            System.out.println("CENTRAL LIBRARY");
            System.out.println("1: View checked-out items");
            System.out.println("2: Search");
            System.out.println("3: Reserve an item");
            System.out.println("4: Check-out/Check-in");
            System.out.println("5: Exit");

            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewCheckedOutItems();
                    break;
                case 2:
                    searchMenu();
                    break;

                case 3:
                    System.out.println("Reserving an item...");
                    break;
                case 4:
                    System.out.println("Checking in/out...");
                    break;
                case 5:
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    public void viewCheckedOutItems() {
        boolean found = false;

        for (LibraryItem item : items) {
            if (!item.isAvailable()) {
                System.out.println(item);
                found = true;
            }
        }

        if (!found) {
            System.out.println("There are currently no checked-out items.");
        }
    }

    public void searchMenu() {
        System.out.println("SEARCH");
        System.out.println("1: Search all items");
        System.out.println("2: Search books");
        System.out.println("3: Search DVDs");
        System.out.println("4: Search periodicals");
        System.out.println("5: Back");

        System.out.print("Choose an option: ");

        int searchChoice = scanner.nextInt();

        switch (searchChoice) {
            case 1:
                scanner.nextLine();

                System.out.print("Enter a search keyword: ");
                String keyword = scanner.nextLine();

                boolean found = false;

                for (LibraryItem item : items) {
                    if (item.matchesKeyword(keyword)) {
                        System.out.println(item);
                        found = true;
                    }
                }
                if (!found) {
                    System.out.println("No matching items found.");
                }

                break;

            case 2:
                bookSearchMenu();
                break;

            case 3:
                dvdSearchMenu();
                break;

            case 4:
                periodicalsSearchMenu();
                break;

            case 5:
                System.out.println("Returning to main menu...");
                break;

            default:
                System.out.println("Invalid option");
        }
    }

    public void bookSearchMenu() {
        System.out.println("BOOK SEARCH");
        System.out.println("1: Search by title");
        System.out.println("2: Search by author");
        System.out.println("3: Search by ISBN");
        System.out.println("4: Search by genre");
        System.out.println("5: Back");

        System.out.print("Choose an option: ");

        int bookChoice = scanner.nextInt();
        scanner.nextLine();

        switch (bookChoice) {
            case 1:
                System.out.print("Enter title: ");
                String title = scanner.nextLine();

                boolean titleFound = false;

                for (LibraryItem item : items) {
                    if (item instanceof Book && item.matchesField("title", title)) {
                        System.out.println(item);
                        titleFound = true;
                    }
                }
                if (!titleFound) {
                    System.out.println("No books matching that title.");
                }
                break;

            case 2:
                System.out.print("Enter author: ");
                String author = scanner.nextLine();

                boolean authorFound = false;

                for (LibraryItem item : items) {
                    if (item instanceof Book && item.matchesField("author", author)) {
                        System.out.println(item);
                        authorFound = true;
                    }
                }
                if (!authorFound) {
                    System.out.println("No books matching that author.");
                }

                break;

            case 3:
                System.out.print("Enter ISBN: ");
                String isbn = scanner.nextLine();

                boolean isbnFound = false;

                for (LibraryItem item : items) {
                    if (item instanceof Book && item.matchesField("isbn", isbn)) {
                        System.out.println(item);
                        isbnFound = true;
                    }
                }

                if (!isbnFound) {
                    System.out.println("No books matching that ISBN.");
                }
                break;

            case 4:
                System.out.print("Enter genre: ");
                String genre = scanner.nextLine();

                boolean genreFound = false;

                for (LibraryItem item : items) {
                    if (item instanceof Book && item.matchesField("genre", genre)) {
                        System.out.println(item);
                        genreFound = true;
                    }
                }

                if (!genreFound) {
                    System.out.println("No books in that genre found.");
                }

                break;

            case 5:
                System.out.println("Returning to search menu...");
                break;

            default:
                System.out.println("Invalid option");
        }
    }

    public void dvdSearchMenu() {
        System.out.println("DVD SEARCH");
        System.out.println("1: Search by title");
        System.out.println("2: Search by director");
        System.out.println("3: Search by genre");
        System.out.println("4: Search by duration");
        System.out.println("5: Back");

        System.out.print("Choose an option: ");

        int dvdChoice = scanner.nextInt();
        scanner.nextLine();

        switch (dvdChoice) {
            case 1:
                System.out.print("Enter DVD title: ");
                String title = scanner.nextLine();

                boolean titleFound = false;

                for (LibraryItem item : items) {
                    if (item instanceof DVD && item.matchesField("title", title)) {
                        System.out.println(item);
                        titleFound = true;
                    }
                }
                if (!titleFound) {
                    System.out.println("No DVDs matching that title.");
                }
                break;
            case 2:
                System.out.print("Enter director: ");
                String director = scanner.nextLine();

                boolean directorFound = false;

                for (LibraryItem item : items) {
                    if (item instanceof DVD && item.matchesField("director", director)) {
                        System.out.println(item);
                        directorFound = true;
                    }
                }

                if (!directorFound) {
                    System.out.println("No DVDs matching that director.");
                }

                break;
            case 3:
                System.out.print("Enter genre: ");
                String genre = scanner.nextLine();

                boolean genreFound = false;

                for (LibraryItem item : items) {
                    if (item instanceof DVD && item.matchesField("genre", genre)) {
                        System.out.println(item);
                        genreFound = true;
                    }
                }
                if (!genreFound) {
                    System.out.println("No DVDs matching that genre.");
                }
                break;
            case 4:
                System.out.print("Enter duration in minutes: ");
                int duration = scanner.nextInt();
                scanner.nextLine();

                boolean durationFound = false;

                for (LibraryItem item : items) {
                    if (item instanceof DVD
                            && item.matchesField("duration", String.valueOf(duration))) {
                        System.out.println(item);
                        durationFound = true;
                    }
                }

                if (!durationFound) {
                    System.out.println("No DVDs matching that duration.");
                }

                break;

            case 5:
                System.out.println("Returning to search menu...");
                break;

            default:
                System.out.println("Invalid option");
        }
    }

    public void periodicalsSearchMenu() {
        System.out.println("PERIODICAL SEARCH");
        System.out.println("1: Search by title");
        System.out.println("2: Search by publisher");
        System.out.println("3: Search by volume");
        System.out.println("4: Search by ISSN");
        System.out.println("5: Back");

        System.out.println("Choose an option: ");

        int periodicalsChoice = scanner.nextInt();
        scanner.nextLine();

        switch (periodicalsChoice) {

            case 1:
                System.out.print("Enter title: ");
                String title = scanner.nextLine();

                boolean titleFound = false;

                for (LibraryItem item : items) {
                    if (item instanceof Periodical && item.matchesField("title", title)) {
                        System.out.println(item);
                        titleFound = true;
                    }
                }

                if (!titleFound) {
                    System.out.println("No periodicals matching that title.");
                }

                break;

            case 2:
                System.out.print("Enter publisher: ");
                String publisher = scanner.nextLine();

                boolean publisherFound = false;

                for (LibraryItem item : items) {
                    if (item instanceof Periodical && item.matchesField("publisher", publisher)) {
                        System.out.println(item);
                        publisherFound = true;
                    }
                }

                if (!publisherFound) {
                    System.out.println("No periodicals matching that publisher.");
                }

                break;

            case 3:
                System.out.print("Enter volume: ");
                int volume = scanner.nextInt();
                scanner.nextLine();

                boolean volumeFound = false;

                for (LibraryItem item : items) {
                    if (item instanceof Periodical
                            && item.matchesField("volume", String.valueOf(volume))) {
                        System.out.println(item);
                        volumeFound = true;
                    }
                }

                if (!volumeFound) {
                    System.out.println("No periodicals matching that volume.");
                }

                break;

            case 4:
                System.out.print("Enter ISSN: ");
                String issn = scanner.nextLine();

                boolean issnFound = false;

                for (LibraryItem item : items) {
                    if (item instanceof Periodical && item.matchesField("issn", issn)) {
                        System.out.println(item);
                        issnFound = true;
                    }
                }

                if (!issnFound) {
                    System.out.println("No periodicals matching that ISSN.");
                }

                break;

            case 5:
                System.out.println("Returning to search menu...");
                break;

            default:
                System.out.println("Invalid option");
        }
    }
}
