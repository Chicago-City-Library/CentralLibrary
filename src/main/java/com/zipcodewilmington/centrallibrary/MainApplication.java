package com.zipcodewilmington.centrallibrary;

import java.util.Scanner;

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
