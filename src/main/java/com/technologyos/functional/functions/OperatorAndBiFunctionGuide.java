package com.technologyos.functional.functions;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class OperatorAndBiFunctionGuide {

   /**
    * Demonstrates the use of UnaryOperator and shows that it’s a special case of Function<T, T>,
    * meaning it takes a value of type T and returns the same type.
    */
   public static void unaryOperatorsDemo() {
      UnaryOperator<Integer> square = x -> x * x;
      UnaryOperator<String> quoteText = s -> "\"" + s + "\"";

      System.out.println(square.apply(5)); // 25
      System.out.println(quoteText.apply("Focus is the key to mastery")); // "Focus is the key to mastery"

      UnaryOperator<String> toUpper = String::toUpperCase;
      UnaryOperator<String> addExclamation = s -> s + "!";
      Function<String, String> shout = toUpper.andThen(addExclamation);

      System.out.println(shout.apply("hello")); // HELLO!
   }

   // Demonstrates BiFunction, which is a function that accepts two different input types and produces a result.
   public static void biFunctionDemo() {
      BiFunction<Integer, Integer, Integer> sum = Integer::sum;
      BiFunction<String, Integer, String> leftPad = (s, width) -> String.format("%" + width + "s", s);

      System.out.println(sum.apply(7, 3)); // 10
      System.out.println(leftPad.apply("Java", 10)); // "      Java"

      // Extra example: combining name and age into a formatted string
      BiFunction<String, Integer, String> formatPerson = (name, age) -> name + " is " + age + " years old.";
      System.out.println(formatPerson.apply("Alice", 30)); // Alice is 30 years old.
   }

   // Demonstrates BinaryOperator, which is a special case of BiFunction where all types are the same.
   public static void binaryOperatorDemo() {
      BinaryOperator<Integer> multiply = (a, b) -> a * b;
      BinaryOperator<String> concatWithSpace = (s1, s2) -> s1 + " " + s2;

      System.out.println(multiply.apply(4, 5)); // 20
      System.out.println(concatWithSpace.apply("Functional", "Programming")); // Functional Programming

      // Finding the longer string
      BinaryOperator<String> longest = BinaryOperator.maxBy((s1, s2) -> Integer.compare(s1.length(), s2.length()));
      System.out.println(longest.apply("apple", "watermelon")); // watermelon
   }
}
