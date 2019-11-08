package com.ywh.util;

import org.junit.jupiter.params.converter.SimpleArgumentConverter;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author ywh
 * @since 2/13/2019
 */
public class StringConverter extends SimpleArgumentConverter {

    @Override
    protected Object convert(Object source, Class<?> targetType) {
        assertEquals(String.class, targetType, "Can only convert to String");
        return String.valueOf(source);
    }
}