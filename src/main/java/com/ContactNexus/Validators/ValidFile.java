//package com.ContactNexus.Validators;
//
//import java.lang.annotation.*;
//
//import jakarta.validation.Constraint;
//import jakarta.validation.Payload;
//
//@Documented
//@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR,
//        ElementType.PARAMETER })
//@Retention(RetentionPolicy.RUNTIME)
//@Constraint(validatedBy = FileValidator.class)
//public @interface ValidFile {
//    String message() default "Invalid file";
//
//    Class<?>[] groups() default {};
//
//    boolean checkEmpty() default true;
//
//    Class<? extends Payload>[] payload() default {};
//}
package com.ContactNexus.Validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FileValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidFile {
    String message() default "Invalid file";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}