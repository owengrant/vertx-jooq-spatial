package com.geoideas.jooq.converters;

import org.jooq.Converter;

import java.time.LocalDateTime;

public class TimestampConverter implements Converter<LocalDateTime, String> {
    @Override
    public String from(LocalDateTime localDateTime) {
        return localDateTime == null ? null : localDateTime.toString();
    }

    @Override
    public LocalDateTime to(String s) {
        return s == null ? null : LocalDateTime.parse(s);
    }

    @Override
    public Class<LocalDateTime> fromType() {
        return LocalDateTime.class;
    }

    @Override
    public Class<String> toType() {
        return String.class;
    }
}
