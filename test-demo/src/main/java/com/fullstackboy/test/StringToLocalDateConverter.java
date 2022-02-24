package com.fullstackboy.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * TODO Liuyongfei
 *
 * @author Liuyongfei
 * @date 2022/2/24 23:22
 */
public class StringToLocalDateConverter implements Converter<String, LocalDate>{

    private final DateTimeFormatter DATE_TIME_FORMATTER;

    public StringToLocalDateConverter(String pattern) {
        DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(pattern);
    }

    @Override
    public LocalDate convert(String source) {
        return LocalDate.parse(source, DATE_TIME_FORMATTER);
    }
}
