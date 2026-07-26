package com.zipcodewilmington.centrallibrary;

import java.util.Scanner;
import com.zipcodewilmington.centrallibrary.model.Address;
import com.zipcodewilmington.centrallibrary.model.LibraryMember;
import com.zipcodewilmington.centrallibrary.model.Librarian;

/**
 * Created by n3pjk on 6/9/2025.
 */
public class MainApplication {

    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        MainApplication app = new MainApplication();
        app.start();
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
            System.out.println("1: View all items");
            System.out.println("2: Search");
            System.out.println("3: Reserve an item");
            System.out.println("4: Check-out/Check-in");
            System.out.println("5: Exit");

            System.out.print("Choose an option:");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Viewing checked-out items...");
                case 2:
                    System.out.println("Enter a search keyword or prees Enter to view all items:");
                    String keyword = scanner.nextLine();

                    System.out.println("You searcheed for: " + keyword);
                    break;
                case 3:
                    System.out.println("Reserving an item...");
                case 4:
                    System.out.println("Checking in/out...");
                case 5:
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }
}
