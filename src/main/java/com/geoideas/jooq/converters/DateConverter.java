package com.geoideas.jooq.converters;

import org.jooq.Converter;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateConverter implements Converter<LocalDate, String> {
    @Override
    public String from(LocalDate localDate) {
        return localDate == null ? null : localDate.toString();
    }

    @Override
    public LocalDate to(String s) {
        return s == null ? null : LocalDate.parse(s);
    }

    @Override
    public Class<LocalDate> fromType() {
        return LocalDate.class;
    }

    @Override
    public Class<String> toType() {
        return String.class;
    }
}
