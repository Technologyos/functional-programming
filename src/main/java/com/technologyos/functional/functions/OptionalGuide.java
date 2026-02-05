package com.technologyos.functional.functions;

import com.technologyos.functional.exceptions.EmptyValueException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Optional;

/**
 * Method	            Purpose
 * empty()	            Represents an absent value
 * of(value)	         Creates an Optional, throws if value is null
 * ofNullable(value)	   Safe way to wrap possibly null values
 * isPresent()	         Check if value exists
 * get()	               Retrieve the value (dangerous if not present)
 * orElse(value)	      Provide default value if absent
 * orElseGet(Supplier)	Lazy version of orElse
 * orElseThrow()	      Throw exception if value is absent
 * ifPresent()	         Run logic only when value exists
 * ifPresentOrElse()	   Dual branch handling (Java 9+)
 * filter(Predicate)	   Keep only matching values
 * map(Function)	      Transform the value
 * flatMap()	         Prevent nested Optional
 */
public class OptionalGuide {
   private static final Log logger = LogFactory.getLog(OptionalGuide.class);

   public void optionalExamples() {
      // Create an empty Optional
      Optional<Object> empty = Optional.empty();
      logger.info("Is value present? " + empty.isPresent()); // false

      // Create a non-null Optional
      Optional<String> present = Optional.of("Java 8");
      logger.info("Value from present Optional: " + present.get()); // "Java 8"

      // Optional with a possibly-null value
      Optional<String> maybeName = Optional.ofNullable(buildName(20, "Jose"));

      // ofNullable: allows nulls without throwing exception
      String emptyName = getNameFromService();
      Optional<String> safeName = Optional.ofNullable(emptyName);

      // Provide a fallback value if Optional is empty
      String finalName  = maybeName.orElse("Unknown");
      logger.info("Fallback with orElse: " + finalName );

      // Use a Supplier as a fallback (lazy evaluation)
      finalName  = present.orElseGet(this::getDefaultName);
      logger.info("Fallback with orElseGet: " + finalName );

      // Use lambda as fallback
      finalName  = present.orElseGet(() -> "Generated default");
      logger.info("Lambda fallback: " + finalName );

      // Execute code if value is present
      maybeName.ifPresent(System.out::println);

      //ifPresentOrElse (Java 9+)
      safeName.ifPresentOrElse(
         n -> logger.info("Present: " + n),
         () -> logger.info("Value is missing!")
      );

      // Throw exception if value is missing
      maybeName.orElseThrow(() -> new EmptyValueException("Value must not be null"));

      // Apply transformation if present
      Optional<String> filtered = maybeName.filter(String::isEmpty); // Only present if empty string
      Optional<String> transformed = present.map(String::toLowerCase);

      // flatMap: use when the mapper returns another Optional
      Optional<Optional<String>> nested = safeName.map(Optional::of);
      Optional<String> flattened = nested.flatMap(opt -> opt);
      logger.info("Flattened value: " + flattened.orElse("empty"));

      // Chaining multiple operations
      String processed = getOptionalName()
         .filter(n -> !n.isEmpty())
         .map(String::toUpperCase)
         .map(n -> n + "!")
         .orElse("DEFAULT");
      logger.info("Processed name: " + processed);

      // GOOD: Optional chaining to transform safely
      String goodResult = getNameByAge(24)
         .map(name -> name.toLowerCase().trim())
         .orElse("No name available");
      logger.info("Good usage: " + goodResult);

      // BAD: Manual null-checking, less readable and more error-prone
      String badResult = getNameTraditional(23);
      if (badResult != null) {
         badResult = badResult.toLowerCase().trim();
      } else {
         badResult = "No name available";
      }
      logger.info("Bad usage: " + badResult);
   }

   private Optional<String> getNameByAge(int age) {
      if (age < 18) return Optional.empty();
      return Optional.of("Hello World");
   }

   /**
    * Traditional null-returning method.
    */
   private String getNameTraditional(int age) {
      if (age < 18) return null;
      return "Hello World";
   }

   /**
    * Builds a formatted name if the age condition is met, or null otherwise.
    */
   private String buildName(int age, String name) {
      if (age > 18) {
         return "Your name is: " + name + " and your age is " + age;
      }
      return null;
   }

   /**
    * Simulates a fallback action when no value is present.
    */
   private String getDefaultName() {
      return "Default Name";
   }

   private String getNameFromService() {
      return null; // Simulate external service
   }

   private Optional<String> getOptionalName() {
      return Optional.of("optional chaining");
   }
}
