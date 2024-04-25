package com.test.itau.chavepix.helper;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeFormatterHelperTest {

    @Test
    public void testToLocalDateTime() {
        String date = "25/04/2024";
        DateTimeFormatter formatter = DateTimeFormatterHelper.formatterWithDateOnly();

        LocalDateTime dateTime = DateTimeFormatterHelper.toLocalDateTime(date, formatter);

        assertEquals(2024, dateTime.getYear());
        assertEquals(4, dateTime.getMonthValue());
        assertEquals(25, dateTime.getDayOfMonth());
        assertEquals(0, dateTime.getHour());
        assertEquals(0, dateTime.getMinute());
        assertEquals(0, dateTime.getSecond());
    }

    @Test
    public void testFormatterWithDateOnly() {
        DateTimeFormatter formatter = DateTimeFormatterHelper.formatterWithDateOnly();

        String formattedDate = formatter.format(LocalDateTime.of(2024, 4, 25, 14, 30));

        assertEquals("25/04/2024 14:30:00", formattedDate);
    }
}

