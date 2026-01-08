package com.technologyos.functional.fundamentals;

import java.util.List;
import java.util.Scanner;

public class SingleAbstractMethod {
   /**
    * A SAM (Single Abstract Method) interface is any interface that contains only one
    * abstract method without an implementation.
    *
    * Here's an example of a valid SAM:
    */
   interface SAMInterface {
      int doSomething();
   }

   /**
    * If an interface has more than one abstract method, then it's not a SAM interface.
    *
    * These interfaces cannot be annotated with @FunctionalInterface.
    */
   interface NotASAMInterface {
      int doFoo();

      void doBar();
   }

   /**
    * Since Java 8, the concept of SAM interfaces became more important due to the
    * introduction of lambda expressions.
    *
    * You can annotate a SAM interface with @FunctionalInterface to signal that it's
    * intended to be used with functional programming.
    */
   @FunctionalInterface
   interface AnotherSAM {
      String getText();
   }

   /**
    * This interface is **NOT** a valid SAM because it has two abstract methods.
    * If you try to annotate it with @FunctionalInterface, the compiler will throw an error.
    */
   // @FunctionalInterface // Uncommenting this will cause a compilation error
   interface NotAValidSAM {
      String getText();

      String getSubText();
   }

   /**
    * You can create your own functional interfaces with parameters and use them as functions.
    */
   @FunctionalInterface
   interface MySAMInterfaceIsAlsoAFunction {
      String someMethod(int x);
   }

   /**
    * Functional interfaces can be implemented using lambda expressions.
    */
   private static void fooSAM() {
      // Lambda for a SAM interface with one int parameter
      MySAMInterfaceIsAlsoAFunction myFunction = x -> "Result: " + x;
      System.out.println(myFunction.someMethod(42)); // Output: Result: 42
   }

   /**
    * SAM interfaces can have any number of parameters — as long as there's only one abstract method,
    * it's still a SAM.
    */
   @FunctionalInterface
   interface OverComplicatedSAM {
      int someWeirdNameForAMethod(String s, int x, Scanner sc, List<Double> values);
   }

   // Here's how you can use a complex SAM interface with a lambda.
   public static void somethingCalling() {
      OverComplicatedSAM stillAFunction = (s, x, sc, list) -> {
         System.out.println("Received: " + s + ", " + x);
         return list.size(); // Just returning the size for demo purposes
      };

      // Example usage
      int result = stillAFunction.someWeirdNameForAMethod("Hello", 5, new Scanner(System.in), List.of(1.0, 2.0));
      System.out.println("Result: " + result); // Output: Result: 2
   }

   public static void main(String[] args) {
      fooSAM();
      somethingCalling();
   }
}
