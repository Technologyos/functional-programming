package com.technologyos.functional.fundamentals;

import java.util.function.Function;

public class Currying {
   /**
    * Let's start with something familiar: a Functional Interface that takes three parameters
    * of types F, S, and T, and returns a result of type R.
    *
    * This is like a TriFunction (which Java does not provide by default).
    */
   @FunctionalInterface
   interface ThreeFunction<F, S, T, R> {
      R apply(F first, S second, T third);
   }

   static void curryingExample() {
      /*
       Having three parameters can make a function harder to use and prone to mistakes
       (e.g. mixing up the order of parameters).

       Additionally, we might not always have all the arguments at once when we want to execute the function.
       This is where **currying** comes in.
      */

      // Original function that takes 3 parameters
      ThreeFunction<Integer, String, Double, String> fullFunction = (i, s, d) ->
         "ID: " + i + ", Name: " + s + ", Salary: $" + d;

      // This is the standard way to use it
      String result1 = fullFunction.apply(1, "Alice", 45000.0);
      System.out.println(result1);

      /*
       Currying is a technique in functional programming where you transform a function
       that takes multiple parameters into a sequence of functions each taking a single parameter.

       The goal is to reduce complexity and gain flexibility — now we can partially apply arguments.
      */

      // We curry our 3-parameter function into a chain of single-parameter functions
      Function<Integer, Function<String, Function<Double, String>>> curriedFunction = curryThree(fullFunction);

      // You can use the curried function in steps:
      Function<String, Function<Double, String>> step1 = curriedFunction.apply(2);
      Function<Double, String> step2 = step1.apply("Bob");
      String result2 = step2.apply(55000.0);
      System.out.println(result2);

      // Or all at once (fully applied)
      String result3 = curriedFunction.apply(3).apply("Charlie").apply(65000.0);
      System.out.println(result3);
   }

   /**
    * This method takes a function with three parameters and converts (curries) it into
    * a chain of three nested single-parameter functions.
    *
    * Though Java generics can make it look complex, the logic is straightforward:
    * - First function takes the first argument.
    * - Returns a function that takes the second argument.
    * - Which returns another function that takes the third argument.
    * - Which finally returns the result.
    *
    * This allows step-by-step application, reusability, and a more functional style.
    */
   static <F, S, T, R> Function<F, Function<S, Function<T, R>>> curryThree(ThreeFunction<F, S, T, R> threeFunction) {
      return f -> s -> t -> threeFunction.apply(f, s, t);
   }

   public static void main(String[] args) {
      curryingExample();
   }
}
