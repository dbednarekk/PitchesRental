package com.pitchrent.pitchesrental.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static com.pitchrent.pitchesrental.common.I18n.*;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@NotEmpty(message = NOT_EMPTY_CONSTRAINT)
@Pattern(regexp = "^\\w{3,50}$", message = INVALID_CUSTOMER_NAME)
public @interface CustomerName {
    String message() default INVALID_CUSTOMER_NAME;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}