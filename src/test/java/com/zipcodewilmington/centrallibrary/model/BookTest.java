package com.zipcodewilmington.centrallibrary.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    private Book book;

    @BeforeEach
    void setUp() {

        book = new Book(
                "B001",
                "Java Programming",
                "A1",
                "John Doe",
                "978-1234567890",
                500,
                "Programming"
        );
    }

    @Test
    void constructorTest() {

        assertEquals("B001", book.getId());
        assertEquals("Java Programming", book.getTitle());
        assertEquals("A1", book.getLocation());

        assertEquals("John Doe", book.getAuthor());
        assertEquals("978-1234567890", book.getIsbn());
        assertEquals(500, book.getPages());
        assertEquals("Programming", book.getGenre());
    }
    @Test
    void getItemTypeTest() {

        assertEquals("Book", book.getItemType());

    }
    @Test
    void getMaxBorrowDaysTest() {

        assertEquals(14, book.getMaxBorrowDays());

    }
    @Test
    void calculateLateFeeTest() {

        assertEquals(2.00, book.calculateLateFee(4));

    }
    @Test
    void matchesKeywordTitleTest() {

        assertTrue(book.matchesKeyword("Java"));

    }
    @Test
    void matchesKeywordAuthorTest() {

        assertTrue(book.matchesKeyword("John"));

    }
    @Test
    void matchesKeywordFalseTest() {

        assertFalse(book.matchesKeyword("Python"));

    }
    @Test
    void getSearchableFieldsTest() {

    String[] fields = book.getSearchableFields();

        assertEquals(4, fields.length);

        assertEquals("Java Programming", fields[0]);
        assertEquals("John Doe", fields[1]);
        assertEquals("Programming", fields[2]);
        assertEquals("978-1234567890", fields[3]);
    }
    @Test
    void checkOutTest() {

        book.checkOut();

        assertFalse(book.isAvailable());

    }
    @Test
    void checkInTest() {

        book.checkOut();

        book.checkIn();

        assertTrue(book.isAvailable());

    }
    @Test
    void reserveTest() {

    LibraryMember member = null;

        book.reserve(member);

        assertTrue(book.isReserved());

    }
    @Test
    void cancelReserveTest() {

    LibraryMember member = null;

        book.reserve(member);

        book.cancelReserve();

        assertFalse(book.isReserved());

    }
    @Test
    void itemStartsAvailable() {
        assertTrue(book.isAvailable());
}


}
