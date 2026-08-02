package com.osorgtech.java.utilities.textfileformats.fixedwidth;

import com.osorgtech.java.utilities.textfileformats.fixedwidth.annotations.FixedWidthField;
import com.osorgtech.java.utilities.textfileformats.fixedwidth.annotations.FixedWidthRecord;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@FixedWidthRecord(
        length = 70,
        description = "Test Data Record"
)
public final class TestDataRecord {

    @FixedWidthField(
            ordinal = 1,
            startPosition = 1,
            endPosition = 8,
            length = 8
    )
    public Integer testInteger;

    @FixedWidthField(
            ordinal = 2,
            startPosition = 9,
            endPosition = 14,
            length = 6
    )
    public String testString;

    @FixedWidthField(
            ordinal = 3,
            startPosition = 15,
            endPosition = 15,
            length = 1
    )
    public String testBoolean_Y_N;

    @FixedWidthField(
            ordinal = 4,
            startPosition = 16,
            endPosition = 23,
            length = 8,
            dateFormat = "yyyyMMdd"
    )
    public LocalDate testDate;

    @FixedWidthField(
            ordinal = 5,
            startPosition = 24,
            endPosition = 29,
            length = 6,
            dateFormat = "HHmmss"
    )
    public LocalTime testTime;

    @FixedWidthField(
            ordinal = 6,
            startPosition = 30,
            endPosition = 42,
            length = 13,
            scale = 2,
            signedValueIndicator = FieldDefinition.SignedValueIndicator.RIGHT
    )
    public BigDecimal testBigDecimal1;

    @FixedWidthField(
            ordinal = 7,
            startPosition = 43,
            endPosition = 55,
            length = 13,
            scale = 2,
            signedValueIndicator = FieldDefinition.SignedValueIndicator.RIGHT
    )
    public BigDecimal testBigDecimal2;

    @FixedWidthField(
            ordinal = 8,
            startPosition = 56,
            endPosition = 57,
            length = 2
    )
    public TestEnum testEnum;

    @FixedWidthField(
            ordinal = 9,
            startPosition = 58,
            endPosition = 70,
            length = 13,
            filler = true
    )
    public String filler;

    public Integer getTestInteger() {
        return testInteger;
    }

    public void setTestInteger(Integer testInteger) {
        this.testInteger = testInteger;
    }

    public String getTestString() {
        return testString;
    }

    public void setTestString(String testString) {
        this.testString = testString;
    }

    public String getTestBoolean_Y_N() {
        return testBoolean_Y_N;
    }

    public void setTestBoolean_Y_N(String testBoolean_Y_N) {
        this.testBoolean_Y_N = testBoolean_Y_N;
    }

    public LocalDate getTestDate() {
        return testDate;
    }

    public void setTestDate(LocalDate testDate) {
        this.testDate = testDate;
    }

    public LocalTime getTestTime() {
        return testTime;
    }

    public void setTestTime(LocalTime testTime) {
        this.testTime = testTime;
    }

    public BigDecimal getTestBigDecimal1() {
        return testBigDecimal1;
    }

    public void setTestBigDecimal1(BigDecimal testBigDecimal1) {
        this.testBigDecimal1 = testBigDecimal1;
    }

    public BigDecimal getTestBigDecimal2() {
        return testBigDecimal2;
    }

    public void setTestBigDecimal2(BigDecimal testBigDecimal2) {
        this.testBigDecimal2 = testBigDecimal2;
    }

    public TestEnum getTestEnum() {
        return testEnum;
    }

    public void setTestEnum(TestEnum testEnum) {
        this.testEnum = testEnum;
    }

    public String getFiller() {
        return filler;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }
}
