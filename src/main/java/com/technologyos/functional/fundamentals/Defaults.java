package com.technologyos.functional.fundamentals;

import java.util.Date;

public class Defaults {

   /**
    * A functional interface with a single abstract method and a default method.
    *
    * Default methods in functional interfaces can be useful to provide common behavior
    * that relies on the abstract method.
    */
   @FunctionalInterface
   interface Operational {
      int operate(String s);

      /**
       * A default method that repeats the input string based on the value returned
       * by the `operate` function. This allows reusable logic while still enabling customization.
       */
      default void repeat(String s) {
         int times = operate(s);
         while (times-- > 0) {
            System.out.println(s);
         }
      }
   }

   /**
    * Demonstrates how the `repeat` default method behaves differently
    * depending on the implementation of the `operate` function.
    */
   private static void repeatUsingDefaults(String str) {
      // The repeat method will print the string as many times as its length
      Operational basedOnLength = String::length;
      basedOnLength.repeat(str);

      // The repeat method will print the string exactly 2 times
      Operational fixedTwice = s -> 2;
      fixedTwice.repeat(str);
   }

   /**
    * Another example of a functional interface, this time using a static utility method.
    *
    * Static methods in interfaces are commonly used for helper logic related to the interface's domain.
    */
   @FunctionalInterface
   interface DayCounter {
      int countDays(Date startDate, Date endDate);

      /**
       * Static method to check if a year is a leap year.
       * This logic is helpful for any date-related calculations, and it's part of the interface's responsibility.
       */
      static boolean isLeapYear(int year) {
         if (year % 4 == 0) {
            if (year % 400 == 0) return true;
            return year % 100 != 0;
         }
         return false;
      }
   }

   public static void main(String[] args) {
      System.out.println("Repeating based on string length and custom logic:\n");
      repeatUsingDefaults("Puppy");

      System.out.println("2024 is leap year? " + DayCounter.isLeapYear(2024));
      System.out.println("2023 is leap year? " + DayCounter.isLeapYear(2023));
   }
}
