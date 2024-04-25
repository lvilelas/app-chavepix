package com.test.itau.chavepix.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public final class DateTimeFormatterHelper {


    public static LocalDateTime toLocalDateTime(String date, DateTimeFormatter formatter){
        return LocalDateTime.parse(date, formatter);
    }

    public static DateTimeFormatter formatterWithDateOnly(){
        return new DateTimeFormatterBuilder()
                .appendPattern("dd/MM/yyyy[ HH:mm:ss]")
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                .toFormatter();
    }
}
