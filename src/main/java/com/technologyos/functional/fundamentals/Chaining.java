package com.technologyos.functional.fundamentals;

import com.technologyos.functional.models.Account;
import com.technologyos.functional.utils.builder.BuilderNoChaining;
import com.technologyos.functional.utils.builder.BuilderWithChaining;

import java.util.function.Function;

public class Chaining {
   /**
    * Chaining is not exclusive to functional programming,
    * but it's a very useful pattern—especially in Java—when working
    * with complex objects or data transformations.
    *
    * Chaining refers to returning the same object (or another usable one)
    * so that you can call multiple methods in a single expression.
    */
   static void examplesOfChaining() {
      // Without chaining, code becomes verbose and repetitive
      BuilderNoChaining builderNoChaining = new BuilderNoChaining("");
      builderNoChaining.withBalance(100.00);
      builderNoChaining.withFirstName("Alice");
      builderNoChaining.withLastName("Johnson");
      builderNoChaining.withPhone("123456789");

      Account account = builderNoChaining.buildAccount();

      // With method chaining, we can create objects in a much cleaner way
      Account anotherAccount = new BuilderWithChaining("1")
         .withBalance(200.00)
         .withFirstName("Bob")
         .withLastName("Smith")
         .withPhone("987654321")
         .buildAccount();

      // Chaining is very common with String methods
      char[] message = "Hello World"
         .replaceAll("Hello", "Hey there")
         .toUpperCase()
         .toCharArray();

      // It's also common when using builders like StringBuilder
      StringBuilder sentence = new StringBuilder()
         .append("I").append(" ")
         .append("a").append("m").append(" ")
         .append("l").append("e").append("a").append("r").append("n").append("i").append("n").append("g").append(" ")
         .append("J").append("a").append("v").append("a").append(" ")
         .append("w").append("i").append("t").append("h").append(" ")
         .append("c").append("h").append("a").append("i").append("n").append("i").append("n").append("g.");

      System.out.println(sentence.toString());

      // Relevance in functional programming:
      // You often compose functions to build more complex ones using chaining

      // A function to replace all 'f' characters with '*'
      Function<String, String> replaceF = s -> s.replaceAll("f", "*");

      // Compose it with another function that replaces 'a' with '/'
      Function<Function<String, String>, Function<String, String>> enhance =
         f -> f.andThen(s -> s.replaceAll("a", "/"));

      // Then we can add even more transformations with andThen()
      String result = enhance
         .apply(replaceF)
         .andThen(s -> s.replaceFirst("Chaining", "MAGIC"))
         .apply("Chaining is fun after you figure out functional features!");

      System.out.println(result);
   }
}
