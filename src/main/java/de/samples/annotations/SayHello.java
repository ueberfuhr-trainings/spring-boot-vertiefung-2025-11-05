package de.samples.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used to mark methods that represent a greeting functionality,
 * enabling them to be identified and invoked by frameworks or utilities for dynamic behavior.
 * <p>
 * Methods annotated with this annotation are expected to:
 * - Not require parameters for invocation.
 * - Return a greeting message or similar output.
 * <p>
 * Typical usage involves scanning and dynamically invoking all methods annotated with `@SayHello`
 * within a given object, as demonstrated in certain processing frameworks.
 * <p>
 * The annotation is retained at runtime, making it suitable for use cases involving reflection.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
// @Inherited
// @Documented
public @interface SayHello {

  /**
   * Specifies a prefix that should be added to the output of a method annotated with {@link SayHello}.
   *
   * @return the prefix to be included in the greeting message
   */
  String prefix() default "";

}
