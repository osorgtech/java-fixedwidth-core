/*
 * Copyright 2026 osorgtech.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package online.codevault.java.utilities.textfileformats.fixedformat;

import online.codevault.java.utilities.textfileformats.fixedformat.annotations.FixedWidthField;
import online.codevault.java.utilities.textfileformats.fixedformat.annotations.FixedWidthRecord;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class FixedWidthReader {

    private final BufferedReader reader;
    private String line;

    public FixedWidthReader(InputStream is) {
        this.reader = new BufferedReader(new InputStreamReader(is));
    }

    public String nextLine() throws IOException {
        this.line = this.reader.readLine();
        return this.line;
    }

    public String currentLine() {
        return this.line;
    }

    public <T> T into(Class<T> clazz) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {

        Constructor[] ctors = clazz.getDeclaredConstructors();

        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0) {
                break;
            }
        }

        Objects.requireNonNull(ctor);

        ctor.setAccessible(true);

        final T o = (T) ctor.newInstance();

        FixedWidthRecord recordAnnotation = clazz.getAnnotation(FixedWidthRecord.class);

        for (Field field : o.getClass().getDeclaredFields()) {

            FixedWidthField fieldAnnotation = field.getAnnotation(FixedWidthField.class);

            if (null != fieldAnnotation) {

                if (fieldAnnotation.filler()) {
                    continue;
                }

                String setterMethodName = "set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);

                String value = this.line.substring(fieldAnnotation.startPosition() - 1, fieldAnnotation.startPosition() - 1 + fieldAnnotation.length());

                if (StringUtils.isBlank(value)) {
                    continue;
                } else {
                    value = value.trim();
                }

                if (null != fieldAnnotation.signedValueIndicator()) {

                    if (fieldAnnotation.signedValueIndicator().equals(FieldDefinition.SignedValueIndicator.RIGHT)) {

                        if(value.endsWith("-")) {
                            value = "-" + value.substring(0, value.length() - 1);
                        } else {
                            value = value.substring(0, value.length() - 1);
                        }

                    }

                }

                if (field.getType().equals(String.class)) {

                    Method m = o.getClass().getMethod(setterMethodName, String.class);
                    m.invoke(o, value);

                } else if (field.getType().equals(int.class)) {

                    Method m = o.getClass().getMethod(setterMethodName, int.class);

                    int intValue = 0;
                    if (!StringUtils.isBlank(value)) {
                        value = value.trim();
                        intValue = Integer.valueOf(value);
                    }

                    m.invoke(o, intValue);

                } else if (field.getType().equals(BigDecimal.class)) {

                    Method m = o.getClass().getMethod(setterMethodName, BigDecimal.class);

                    BigDecimal bigDecimalValue = null;

                    if (!StringUtils.isBlank(value)) {
                        value = value.trim();
                        bigDecimalValue = new BigDecimal(value);
                    }

                    // scale value
                    if (fieldAnnotation.scale() > 0) {
                        bigDecimalValue = bigDecimalValue.scaleByPowerOfTen(-1 * fieldAnnotation.scale());
                    }

                    m.invoke(o, bigDecimalValue);

                } else if (field.getType().equals(Character.class)) {

                    Method m = o.getClass().getMethod(setterMethodName, Character.class);
                    m.invoke(o, value.charAt(0));

                } else if (field.getType().equals(char.class)) {

                    Method m = o.getClass().getMethod(setterMethodName, char.class);
                    m.invoke(o, value.charAt(0));

                } else if (field.getType().equals(int.class)) {

                    Method m = o.getClass().getMethod(setterMethodName, int.class);

                    int intValue = 0;
                    if (!StringUtils.isBlank(value)) {
                        value = value.trim();
                        intValue = Integer.valueOf(value);
                    }

                    m.invoke(o, intValue);

                } else if (field.getType().equals(Integer.class)) {

                    Method m = o.getClass().getMethod(setterMethodName, Integer.class);

                    int intValue = 0;
                    if (!StringUtils.isBlank(value)) {
                        value = value.trim();
                        intValue = Integer.valueOf(value);
                    }

                    m.invoke(o, intValue);

                } else if (field.getType().equals(Boolean.class)) {

                    Method m = o.getClass().getMethod(setterMethodName, Boolean.class);

                    String[] options = fieldAnnotation.booleanDisplay().toString().split("_");

                    m.invoke(o, BooleanUtils.toBoolean(value, options[0], options[1]));

                } else if (field.getType().equals(LocalDate.class)) {

                    String format = fieldAnnotation.formatString();

                    if (StringUtils.isBlank(format)) {
                        format = "yyyyMMdd";
                    }

                    Method m = o.getClass().getMethod(setterMethodName, LocalDate.class);
                    if (!value.equals("00000000")) {
                        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
                        LocalDate localDate = LocalDate.parse(value, dateTimeFormatter);
                        m.invoke(o, localDate);
                    }
                } else if (field.getType().equals(LocalTime.class)) {

                    String format = fieldAnnotation.formatString();

                    if (StringUtils.isBlank(format)) {
                        format = "HHmmss";
                    }

                    Method m = o.getClass().getMethod(setterMethodName, LocalTime.class);
                    final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
                    LocalTime localTime = LocalTime.parse(value, dateTimeFormatter);
                    m.invoke(o, localTime);

                } else if (field.getType().isEnum()) {

                    Method valueMethod = field.getType().getDeclaredMethod("getValue");

                    Object enumConstant = null;
                    for (Object constant : field.getType().getEnumConstants()) {
                        if (Objects.equals(value, valueMethod.invoke(constant))) {
                            enumConstant = constant;
                            break;
                        }
                    }

                    if (null == enumConstant) {
                        throw new UnsupportedOperationException("Value '" + value + "' does not match any constant of enum " + field.getType() + ".");
                    }

                    Method m = o.getClass().getMethod(setterMethodName, field.getType());
                    m.invoke(o, enumConstant);

                } else {
                    throw new UnsupportedOperationException("Class type of " + field.getType() + " is not currently supported.");
                }

            }

        }

        return (T) o;

    }

}
