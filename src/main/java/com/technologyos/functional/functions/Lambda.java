package com.technologyos.functional.functions;

import java.util.function.*;

public class Lambda {

   private static void basicFunctionExamples() {
      // A simple function that receives a String and returns a String
      Function<String, String> simpleFunction = s -> "Processed: " + s;
      System.out.println(simpleFunction.apply("Data"));

      // You can be more explicit with types if you want:
      Function<String, String> explicitFunction = (String s) -> "Explicit: " + s;
      System.out.println(explicitFunction.apply("Typing"));

      // You can also use a block if the logic is more complex
      Function<String, String> blockFunction = s -> {
         System.out.println("Starting transformation...");
         String result = s.trim().toUpperCase();
         System.out.println("Done.");
         return result;
      };
      System.out.println(blockFunction.apply("   hello   "));
   }

   /**
    * A more complex example showing how to chain functions using `andThen` and `compose`
    */
   private static void extendedFunctionExample() {
      Function<String, String> trim = String::trim;
      Function<String, String> toUpper = String::toUpperCase;
      Function<String, String> addExclamation = s -> s + "!!!";

      // Chain using andThen: applies in the order trim -> toUpper -> addExclamation
      Function<String, String> shout = trim.andThen(toUpper).andThen(addExclamation);
      System.out.println(shout.apply("   wow   ")); // Output: WOW!!!

      // Compose applies in reverse order: addExclamation -> toUpper -> trim
      Function<String, String> composed = addExclamation.compose(toUpper).compose(trim);
      System.out.println(composed.apply("   wow   ")); // Output: WOW!!!
   }

   /**
    * Demonstrates BiFunction usage — functions that take two arguments.
    */
   private static void biFunctionExamples() {
      // A function that adds the lengths of two strings
      BiFunction<String, String, Integer> lengthAdder = (s1, s2) -> s1.length() + s2.length();
      System.out.println("Total length: " + lengthAdder.apply("Hello", "World")); // 10

      // A function that pads a string to the left with spaces
      BiFunction<String, Integer, String> leftPad = (text, width) -> String.format("%" + width + "s", text);
      System.out.println(leftPad.apply("Java", 10)); // "      Java"

      // You can also explicitly define the parameter types
      BiFunction<String, String, String> mergeWords = (String a, String b) -> a + " & " + b;
      System.out.println(mergeWords.apply("Spring", "Angular")); // "Spring & Angular"
   }

   /**
    * Consumer represents an operation that takes one input and returns nothing.
    */
   private static void consumerExamples() {
      Consumer<String> print = s -> System.out.println("Message: " + s);
      print.accept("Hello, World!");

      Consumer<String> shout = s -> System.out.println(s.toUpperCase() + "!");
      Consumer<String> combined = print.andThen(shout);
      combined.accept("lambda functions"); // Prints message then uppercases it

      // Consumer with block
      Consumer<String> detailedLogger = s -> {
         System.out.println("Logging...");
         System.out.println("Value: " + s);
         System.out.println("Done.");
      };
      detailedLogger.accept("My log message");
   }

   /**
    * Supplier provides a result without taking any arguments.
    */
   private static void supplierExamples() {
      Supplier<String> helloSupplier = () -> "Hello, Functional World!";
      System.out.println(helloSupplier.get());

      Supplier<Double> randomSupplier = Math::random;
      System.out.println("Random number: " + randomSupplier.get());

      Supplier<String> multiline = () -> {
         String name = "Anonymous";
         return "Welcome, " + name;
      };
      System.out.println(multiline.get());
   }
}
