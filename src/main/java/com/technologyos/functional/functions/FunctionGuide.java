package com.technologyos.functional.functions;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FunctionGuide {

   /**
    * The Function interface works with generics:
    * The first type is the input type, and the second is the output (result) type.
    */
   private static <T, R> void exampleMethod() {
      Function<T, R> someFunction = new Function<>() {
         /**
          * The only method to implement is apply().
          * It takes an input of type T and returns a result of type R.
          */
         @Override
         public R apply(T input) {
            return null; // Replace with actual logic
         }
      };

      // Then you can call apply() with the appropriate parameter:
      someFunction.apply(null); // For now it returns null
   }

   // Let's see a simple example: a function that checks if a number is even.
   private static void functionExample() {
      Function<Integer, Boolean> isEven = new Function<>() {
         @Override
         public Boolean apply(Integer number) {
            return number % 2 == 0;
         }
      };

      System.out.println(isEven.apply(2));    // true
      System.out.println(isEven.apply(25));   // false
   }

   /**
    * Using a Comparator to sort numbers.
    * This is similar to using functions, but with a different interface.
    */
   private static void sortNumbers(List<Integer> numbers) {
      // Create an anonymous instance of Comparator
      numbers.sort(new Comparator<>() {
         @Override
         public int compare(Integer a, Integer b) {
            return a - b; // Ascending order
         }
      });
   }

   /**
    * Instead of writing verbose anonymous classes, we can use Java 8 lambda syntax.
    * Much cleaner and easier to read.
    */
   private static void syntaxFixing() {
      // Function to multiply a number by 5
      Function<Integer, Integer> multiplyBy5 = x -> x * 5;

      System.out.println(multiplyBy5.apply(10)); // Outputs: 50

      // Higher-order function: returns another function
      Function<Integer, Function<Integer, Integer>> multiplyXByY = x -> y -> x * y;

      // Apply the outer function to get an inner one
      Function<Integer, Integer> multiply2ByY = multiplyXByY.apply(2);

      System.out.println(multiply2ByY.apply(7)); // Outputs: 14
      System.out.println(multiplyXByY.apply(9).apply(8)); // Outputs: 72
   }

   // Reusable function to check if a number is even
   public static boolean isEven(int number) {
      Function<Integer, Boolean> isEven = n -> n % 2 == 0;
      return isEven.apply(number);
   }

   /**
    * Function chaining to transform a string:
    * 1. Trim spaces
    * 2. Convert to uppercase
    * 3. Remove hyphens
    */
   public static String transformText(String text) {
      List<Function<String, String>> transformations = List.of(
         String::trim,
         String::toUpperCase,
         s -> s.replace("-", "")
      );

      return transformations.stream()
         .reduce(Function.identity(), Function::andThen)
         .apply(text);
   }

   /**
    * Applies a given function to each item in a list.
    * Returns a new list with the results.
    */
   public static List<Integer> applyMathInTheList(List<Integer> items, Function<Integer, Integer> operation) {
      return items.stream()
         .map(operation)
         .collect(Collectors.toList());
   }
}
