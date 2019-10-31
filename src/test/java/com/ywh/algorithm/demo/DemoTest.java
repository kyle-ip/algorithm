package com.ywh.algorithm.demo;

import com.ywh.algorithm.util.ToStringConverter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author ywh
 * @since 2/13/2019
 */
public class DemoTest {

    @ParameterizedTest
    @EnumSource(TimeUnit.class)
    void testWithExplicitArgumentConversion(@ConvertWith(ToStringConverter.class) String argument) {
        assertNotNull(TimeUnit.valueOf(argument));
    }

}
