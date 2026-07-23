package com.zipcodewilmington.centrallibrary.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PeriodicalTest {

    private Periodical periodical;

    @BeforeEach
    void setUp() {

        periodical = new Periodical(
                "P001",
                "Byte",
                "P1",
                "McGraw-Hill, Inc.",
                "0360-5280",
                6,
                8,
                LocalDate.of(1981, 8, 1)
        );
    }

    @Test
    void constructorTest() {

        assertEquals("P001", periodical.getId());
        assertEquals("Byte", periodical.getTitle());
        assertEquals("P1", periodical.getLocation());

        assertEquals("McGraw-Hill, Inc.", periodical.getPublisher());
        assertEquals("0360-5280", periodical.getIssn());
        assertEquals(6, periodical.getVolume());
        assertEquals(8, periodical.getIssueNumber());
        assertEquals(LocalDate.of(1981, 8, 1), periodical.getPublicationDate());
    }

    @Test
    void getItemTypeTest() {

        assertEquals("Periodical", periodical.getItemType());

    }

    @Test
    void getMaxBorrowDaysTest() {

        assertEquals(7, periodical.getMaxBorrowDays());

    }

    @Test
    void calculateLateFeeTest() {

        assertEquals(1.0, periodical.calculateLateFee(4));

    }

    @Test
    void matchesKeywordTitleTest() {

        assertTrue(periodical.matchesKeyword("Byte"));

    }

    @Test
    void matchesKeywordPublisherTest() {

        assertTrue(periodical.matchesKeyword("McGraw"));

    }

    @Test
    void matchesKeywordFalseTest() {

        assertFalse(periodical.matchesKeyword("Java"));

    }

    @Test
    void getSearchableFieldsTest() {

        String[] fields = periodical.getSearchableFields();

        assertEquals(3, fields.length);

        assertEquals("Byte", fields[0]);
        assertEquals("McGraw-Hill, Inc.", fields[1]);
        assertEquals("0360-5280", fields[2]);
    }

    @Test
    void checkOutTest() {

        periodical.checkOut();

        assertFalse(periodical.isAvailable());

    }

    @Test
    void checkInTest() {

        periodical.checkOut();

        periodical.checkIn();

        assertTrue(periodical.isAvailable());

    }

}