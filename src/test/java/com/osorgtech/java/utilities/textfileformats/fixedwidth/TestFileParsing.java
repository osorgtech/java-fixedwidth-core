package com.osorgtech.java.utilities.textfileformats.fixedwidth;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class TestFileParsing {

    private static FixedWidthReader fwr;

    @BeforeAll
    public static void init() throws IOException {

        fwr = new FixedWidthReader(TestFileParsing.class.getResourceAsStream("/test.txt"));
        fwr.nextLine();

    }

    @Test
    public void testFileRead() throws IOException {

        Assertions.assertEquals("12345678ABCDEFY20000101123456000000012345+000000012345-BB", this.fwr.currentLine());

    }

    @Test
    public void testFileParse() throws IOException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {

        TestDataRecord record = this.fwr.into(TestDataRecord.class);

        Assertions.assertEquals(12345678, record.getTestInteger());
        Assertions.assertEquals("ABCDEF", record.getTestString());
        Assertions.assertEquals("Y", record.getTestBoolean_Y_N());
        Assertions.assertEquals(LocalDate.of(2000, 1, 1), record.getTestDate());
        Assertions.assertEquals(LocalTime.of(12, 34, 56), record.getTestTime());
        Assertions.assertEquals(new BigDecimal("123.45"), record.getTestBigDecimal1());
        Assertions.assertEquals(new BigDecimal("-123.45"), record.getTestBigDecimal2());
        Assertions.assertEquals(TestEnum.OPTION_B, record.getTestEnum());
        Assertions.assertTrue(StringUtils.isBlank(record.getFiller()));

    }

}
