package com.zipcodewilmington.centrallibrary.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DVDTest {

    private DVD dvd;

    @BeforeEach
    void setUp() {

        dvd = new DVD(
                "D001",
                "The Matrix",
                "D1",
                "Wachowski Sisters",
                136,
                "R",
                "Sci-Fi"
        );
    }

    @Test
    void constructorTest() {

        assertEquals("D001", dvd.getId());
        assertEquals("The Matrix", dvd.getTitle());
        assertEquals("D1", dvd.getLocation());

        assertEquals("Wachowski Sisters", dvd.getDirector());
        assertEquals(136, dvd.getDuration());
        assertEquals("R", dvd.getRating());
        assertEquals("Sci-Fi", dvd.getGenre());
    }

    @Test
    void getItemTypeTest() {

        assertEquals("DVD", dvd.getItemType());

    }

    @Test
    void getMaxBorrowDaysTest() {

        assertEquals(7, dvd.getMaxBorrowDays());

    }

    @Test
    void calculateLateFeeTest() {

        assertEquals(4.0, dvd.calculateLateFee(4));

    }

    @Test
    void matchesKeywordTitleTest() {

        assertTrue(dvd.matchesKeyword("Matrix"));

    }

    @Test
    void matchesKeywordDirectorTest() {

        assertTrue(dvd.matchesKeyword("Wachowski"));

    }

    @Test
    void matchesKeywordFalseTest() {

        assertFalse(dvd.matchesKeyword("Comedy"));

    }

    @Test
    void getSearchableFieldsTest() {

        String[] fields = dvd.getSearchableFields();

        assertEquals(3, fields.length);

        assertEquals("The Matrix", fields[0]);
        assertEquals("Wachowski Sisters", fields[1]);
        assertEquals("Sci-Fi", fields[2]);
    }

    @Test
    void checkOutTest() {

        dvd.checkOut();

        assertFalse(dvd.isAvailable());

    }

    @Test
    void checkInTest() {

        dvd.checkOut();

        dvd.checkIn();

        assertTrue(dvd.isAvailable());

    }

    @Test
    void reserveTest() {

        LibraryMember member = null;

        dvd.reserve(member);

        assertTrue(dvd.isReserved());

    }

    @Test
    void cancelReserveTest() {

        LibraryMember member = null;

        dvd.reserve(member);

        dvd.cancelReserve();

        assertFalse(dvd.isReserved());

    }

}