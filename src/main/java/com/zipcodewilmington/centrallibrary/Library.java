package com.zipcodewilmington.centrallibrary;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zipcodewilmington.centrallibrary.Interface.Reservable;
import com.zipcodewilmington.centrallibrary.model.Book;
import com.zipcodewilmington.centrallibrary.model.DVD;
import com.zipcodewilmington.centrallibrary.model.LibraryItem;
import com.zipcodewilmington.centrallibrary.model.LibraryMember;
import com.zipcodewilmington.centrallibrary.model.Periodical;

public class Library {

    // Stores every item loaded into the library.
    private final List<LibraryItem> items = new ArrayList<>();

    // Reads the JSON files.
    private final ObjectMapper objectMapper = new ObjectMapper();

    // Loads all library data when the program starts.
    public void loadData() {

        // Prevents duplicates if loadData() runs twice.
        items.clear();

        loadBooks();
        loadMovies();
        loadPeriodicals();

        System.out.println();
        System.out.println(
                "Total library items loaded: " + items.size()
        );
    }

    // Loads books from matts_cool_catalog.json.
    private void loadBooks() {

        JsonNode books = readJsonFile(
                "src/main/resourses/data/matts_cool_catalog.json"
        );

        if (books == null || !books.isArray()) {

            System.out.println(
                    "Book data could not be loaded."
            );

            return;
        }

        int loadedCount = 0;

        for (JsonNode record : books) {

            try {

                String isbn = record.path("ISBN")
                        .asText();

                String title = record.path("Title")
                        .asText("Unknown Title");

                String author = record.path("Author_Name")
                        .asText("Unknown Author");

                String genre = record.path("Bookshelves")
                        .asText("Unknown Genre");

                /*
             * The JSON does not contain a page count,
             * so zero is used for now.
                 */
                int pages = 0;

                /*
             * Uses the ISBN value to create
             * a unique library item ID.
                 */
                if (isbn.isBlank()) {
                    continue;
                }

                Book book = new Book(
                        "B" + isbn,
                        title,
                        "Book Section",
                        author,
                        isbn,
                        pages,
                        genre
                );

                items.add(book);
                loadedCount++;

            } catch (Exception exception) {

                System.out.println(
                        "A book record could not be loaded."
                );
            }
        }

        System.out.println(
                loadedCount
                + " books loaded successfully."
        );
    }

    // Loads DVDs from aggregated_movies.json.
    private void loadMovies() {

        JsonNode movies = readJsonFile(
                "src/main/resourses/data/aggregated_movies.json"
        );

        if (movies == null || !movies.isArray()) {

            System.out.println(
                    "Movie data could not be loaded."
            );

            return;
        }

        int loadedCount = 0;

        for (JsonNode movie : movies) {

            try {

                String id = movie.path("id")
                        .asText();

                String title = movie.path("title")
                        .asText("Unknown Title");

                String director = movie.path("director")
                        .asText("Unknown Director");

                int duration = (int) movie.path("minutes")
                        .asDouble(0);

                String rating = movie.path("rating")
                        .asText("Unrated");

                String genre = movie.path("genre")
                        .asText("Unknown Genre");

                // Skips records without an ID.
                if (id.isBlank()) {
                    continue;
                }

                DVD dvd = new DVD(
                        "D" + id,
                        title,
                        "DVD Section",
                        director,
                        duration,
                        rating,
                        genre
                );

                items.add(dvd);
                loadedCount++;

            } catch (Exception exception) {

                // One bad record will not stop the entire file.
                System.out.println(
                        "A movie record could not be loaded."
                );
            }
        }

        System.out.println(
                loadedCount + " DVDs loaded successfully."
        );
    }

    // Loads periodicals from trove-periodicals-clean.json.
    private void loadPeriodicals() {

        JsonNode periodicalData = readJsonFile(
                "src/main/resourses/data/trove-periodicals-clean.json"
        );

        if (periodicalData == null) {
            return;
        }

        // The actual records are stored in the
        // top-level "periodicals" array.
        JsonNode records = periodicalData.path("periodicals");

        if (!records.isArray()) {

            System.out.println(
                    "Periodical data could not be loaded."
            );

            return;
        }

        int loadedCount = 0;

        for (JsonNode record : records) {

            try {

                String id = record.path("id")
                        .asText();

                String title = record.path("title")
                        .asText("Unknown Title");

                String publisher = record.path("publisher")
                        .asText("Unknown Publisher");

                String issn = record.path("issn")
                        .asText("No ISSN");

                int volume = record.path("start_year")
                        .asInt(0);

                int issueNumber = record.path("issue_count")
                        .asInt(0);

                String dateText = record.path("start_date")
                        .asText();

                // Skips records without an ID.
                if (id.isBlank()) {
                    continue;
                }

                LocalDate publicationDate
                        = createDate(dateText, volume);

                Periodical periodical = new Periodical(
                        "P" + id,
                        title,
                        "Periodical Section",
                        publisher,
                        issn,
                        volume,
                        issueNumber,
                        publicationDate
                );

                items.add(periodical);
                loadedCount++;

            } catch (Exception exception) {

                // One bad record will not stop the entire file.
                System.out.println(
                        "A periodical record could not be loaded."
                );
            }
        }

        System.out.println(
                loadedCount
                + " periodicals loaded successfully."
        );
    }

    // Creates a usable publication date.
    private LocalDate createDate(
            String dateText,
            int startYear) {

        if (dateText != null && !dateText.isBlank()) {

            try {
                return LocalDate.parse(dateText);
            } catch (Exception exception) {
                // Uses the fallback date below.
            }
        }

        // LocalDate cannot use year zero.
        int safeYear = startYear > 0
                ? startYear
                : 1;

        return LocalDate.of(safeYear, 1, 1);
    }

    // Opens one JSON file.
    private JsonNode readJsonFile(String filePath) {

        try {

            Path path = Path.of(filePath);

            System.out.println(
                    "Looking for file at: "
                    + path.toAbsolutePath()
            );

            if (!Files.exists(path)) {

                System.out.println(
                        "File does not exist."
                );

                return null;
            }

            return objectMapper.readTree(
                    path.toFile()
            );

        } catch (IOException exception) {

            System.out.println(
                    "Error reading JSON: "
                    + exception.getMessage()
            );

            return null;
        }
    }

    // Searches all library items.
    // A blank keyword returns every item.
    public List<LibraryItem> searchAll(String keyword) {

        List<LibraryItem> results = new ArrayList<>();

        String safeKeyword
                = keyword == null
                        ? ""
                        : keyword.trim();

        for (LibraryItem item : items) {

            if (safeKeyword.isBlank()
                    || item.matchesKeyword(safeKeyword)) {

                results.add(item);
            }
        }

        return results;
    }
// Searches one item type using one specific field.

    public List<LibraryItem> searchByField(
            Class<? extends LibraryItem> itemType,
            String fieldName,
            String keyword) {

        List<LibraryItem> results = new ArrayList<>();

        if (itemType == null
                || fieldName == null
                || keyword == null
                || keyword.isBlank()) {

            return results;
        }

        String safeKeyword = keyword.trim();

        for (LibraryItem item : items) {

            if (itemType.isInstance(item)
                    && item.matchesField(
                            fieldName,
                            safeKeyword)) {

                results.add(item);
            }
        }

        return results;
    }

    // Returns every item marked unavailable.
    public List<LibraryItem> getCheckedOutItems() {

        List<LibraryItem> checkedOutItems
                = new ArrayList<>();

        for (LibraryItem item : items) {

            if (!item.isAvailable()) {
                checkedOutItems.add(item);
            }
        }

        return checkedOutItems;
    }

    // Finds one library item by ID.
    public LibraryItem findItemById(String itemId) {

        if (itemId == null || itemId.isBlank()) {
            return null;
        }

        for (LibraryItem item : items) {

            if (item.getId() != null
                    && item.getId().equalsIgnoreCase(
                            itemId.trim()
                    )) {

                return item;
            }
        }

        return null;
    }

    // Reserves an item for a member.
    public boolean reserveItem(
            String itemId,
            LibraryMember member) {

        LibraryItem item = findItemById(itemId);

        if (!(item instanceof Reservable)
                || member == null) {

            return false;
        }

        Reservable reservable
                = (Reservable) item;

        if (reservable.isReserved()) {
            return false;
        }

        reservable.reserve(member);

        return reservable.isReserved();
    }

    // Cancels an item's reservation.
    public boolean cancelReservation(String itemId) {

        LibraryItem item = findItemById(itemId);

        if (!(item instanceof Reservable)) {
            return false;
        }

        Reservable reservable
                = (Reservable) item;

        if (!reservable.isReserved()) {
            return false;
        }

        reservable.cancelReserve();

        return !reservable.isReserved();
    }

    // Checks an item out to a member.
    public boolean checkOutItem(
            String itemId,
            LibraryMember member) {

        LibraryItem item = findItemById(itemId);

        if (item == null || member == null) {
            return false;
        }

        // If reserved, only the member who reserved
        // the item may check it out.
        if (item instanceof Reservable) {

            Reservable reservable
                    = (Reservable) item;

            if (reservable.isReserved()
                    && reservable.getReservedBy()
                    != member) {

                return false;
            }
        }

        boolean checkedOut
                = item.checkOut(member);

        // Removes the reservation after checkout.
        if (checkedOut
                && item instanceof Reservable) {

            Reservable reservable
                    = (Reservable) item;

            if (reservable.isReserved()) {
                reservable.cancelReserve();
            }
        }

        return checkedOut;
    }

    // Checks an item back into the library.
    public boolean checkInItem(String itemId) {

        LibraryItem item = findItemById(itemId);

        if (item == null || item.isAvailable()) {
            return false;
        }

        item.checkIn();

        return true;
    }
}
