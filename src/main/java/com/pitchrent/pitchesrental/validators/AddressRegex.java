package com.pitchrent.pitchesrental.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static com.pitchrent.pitchesrental.common.I18n.*;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@NotEmpty(message = NOT_EMPTY_CONSTRAINT)
@Size(max = 64, message = ADDRESS_SIZE)
@Pattern(regexp = "^(?:[\\p{L}']+[- ]?)+$", message = INVALID_ADDRESS)
public @interface AddressRegex {
    String message() default INVALID_ADDRESS;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
