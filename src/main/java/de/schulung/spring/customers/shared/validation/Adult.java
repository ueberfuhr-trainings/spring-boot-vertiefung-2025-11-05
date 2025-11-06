package de.schulung.spring.customers.shared.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.temporal.ChronoUnit;

/**
 * Annotation to validate that a person's age meets a minimum required value, typically used for
 * ensuring the person is considered an adult. The default value for the minimum age is 18 years.
 * This validation is applied to LocalDate fields or parameters by utilizing the `AdultValidator` class.
 * <p>
 * Attributes:
 * - message: Customizes the error message when validation fails. Default is "must be at least 18 years old".
 * - groups: Specifies the validation groups this constraint belongs to.
 * - payload: Provides additional metadata or information for the constraint.
 * - value: Defines the minimum age required. Default is 18.
 * - unit: Specifies the unit of time, such as YEARS, to determine the age. Default is ChronoUnit.YEARS.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({
  ElementType.FIELD,
  ElementType.PARAMETER,
  ElementType.ANNOTATION_TYPE,
  ElementType.METHOD,
  ElementType.CONSTRUCTOR
})
@Documented
// Bean Validation
@Constraint(validatedBy = AdultValidator.class)
public @interface Adult {

  String message() default "must be at least 18 years old";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  /**
   * Specifies the minimum required age for validation. The default value is 18.
   *
   * @return the minimum age required for validation
   */
  long value() default 18;

  /**
   * Specifies the unit of time used to calculate the age for validation purposes.
   * The default unit is years, represented by ChronoUnit.YEARS.
   *
   * @return the ChronoUnit used to calculate the age
   */
  ChronoUnit unit() default ChronoUnit.YEARS;

}
