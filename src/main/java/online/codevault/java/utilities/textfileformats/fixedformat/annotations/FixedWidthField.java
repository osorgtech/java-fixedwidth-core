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

package online.codevault.java.utilities.textfileformats.fixedformat.annotations;

import com.osorgtech.java.utilities.textfileformats.shared.BooleanDisplay;
import online.codevault.java.utilities.textfileformats.fixedformat.FieldDefinition;
import online.codevault.java.utilities.textfileformats.fixedformat.Padding;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FixedWidthField {
    int ordinal();
    int startPosition();
    int endPosition();
    int length();
    int decimalScale() default 0;
    Padding padding() default Padding.RIGHT;
    char paddingCharacter() default ' ';
    FieldDefinition.SignedValueIndicator signedValueIndicator() default FieldDefinition.SignedValueIndicator.UNDEFINED;
    String description() default "";
    String additionalInformation() default "";
    String extendedInformation() default "";
    String defaultValue() default "";
    String dateFormat() default "MM/dd/yyyy";
    int precision() default 0;
    int scale() default 0;
    String timeFormat() default "HHmmss";
    String formatString() default "";
    BooleanDisplay booleanDisplay() default BooleanDisplay.ONE_ZERO;
    boolean mandatory() default false;
    boolean filler() default false;
}
