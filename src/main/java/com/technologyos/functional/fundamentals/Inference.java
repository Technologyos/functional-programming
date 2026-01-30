package com.technologyos.functional.fundamentals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Inference {

   private static void typeInference() {
      List<Integer> numbersList = getNumbers();

      /*
       Type inference allows Java to automatically determine the types of
       parameters and return values based on context. This keeps code shorter
       and more readable, especially in lambda expressions.
      */
      getOperable(numbersList)
         .filter(n -> n % 10 == 0) // Keep only numbers divisible by 10
         .map(n -> {
            // Create a char array of length 'n' filled with '!'
            char[] repeater = new char[n];
            Arrays.fill(repeater, '!');
            return repeater;
         })
         .map(String::new) // Convert char[] to String
         .forEach(System.out::println);

      /*
       Without type inference, we would need to explicitly declare types in lambdas.
       This is more verbose and can reduce code readability, especially when the type
       is already obvious from context.
      */
      getOperable(numbersList)
         .filter((Integer n) -> n % 10 == 0)
         .map((Integer n) -> {
            char[] repeater = new char[n];
            Arrays.fill(repeater, '!');
            return repeater;
         })
         .map(String::new)
         .forEach(System.out::println);
   }

   private static List<Integer> getNumbers() {
      return Arrays.asList(1, 10, 11, 100, 1001, 1010, 1100, 1111);
   }

   // Converts the list to a Stream for functional operations
   private static Stream<Integer> getOperable(List<Integer> list) {
      return list.stream();
   }
}
