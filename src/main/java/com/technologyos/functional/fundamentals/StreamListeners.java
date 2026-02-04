package com.technologyos.functional.fundamentals;

import com.technologyos.functional.utils.Utils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import java.util.concurrent.atomic.AtomicInteger;

public class StreamListeners {
   static void listeners() {

      /*
       * Streams in Java are "self-iterating".
       *
       * It's simple — the Stream API is functional and allows passing in
       * `Suppliers`, `Consumers`, `Predicates`, and lambdas in general.
       */

      Stream<String> coursesStream = Stream.of("Java", "Functional");
      coursesStream.forEach(course -> System.out.println("Course about: " + course));

      /*
       * These lambdas or functions we attach to streams to process data
       * are often referred to as **listeners**.
       *
       * While this name isn't official in the Java API, it makes sense:
       * once the stream is triggered (usually by a terminal operation),
       * it will iterate over the elements and apply the defined operations step by step.
       */

      /*
       * There are different types of stream operations that accept different kinds of listeners.
       * For example: filtering data.
       */
      AtomicInteger x = new AtomicInteger();
      Stream<Integer> countingStream = Stream.generate(x::getAndIncrement);

      // Filter only even numbers produced by the stream
      Stream<Integer> evens = countingStream.filter(i -> i % 2 == 0);

      Stream<Integer> squared = countingStream.map(i -> i * i);

      /*
       * A common case is to have a Stream<List<String>> — that is, a stream that emits collections.
       *
       * If you try to process this stream directly, you only access each `List<String>`,
       * not the individual strings. Re-processing each list inside the stream may not be ideal,
       * especially when working in parallel streams.
       */
      Stream<List<String>> coursesModules = Stream.of(/* Let's imagine we fetch this from a DB */);

      // This would NOT work as expected, because each element is a list, not a string
      coursesModules.filter(list -> list.contains("Java"));

      /*
       * To operate on each element inside those lists, we use a special operation called `flatMap`.
       *
       * `flatMap` transforms a Stream<Collection<T>> into a Stream<T>.
       *
       * It flattens all the nested collections into a single stream. To do this,
       * you need to return a Stream<T> from your lambda.
       *
       * For example:
       * Initial stream:
       * Stream.of(
       *   List.of("Node.js", "JavaScript"),
       *   List.of("Android", "Kotlin"),
       *   List.of("JavaSE 8", "Java FP")
       * )
       *
       * After flatMap:
       * Stream.of("Node.js", "JavaScript", "Android", "Kotlin", "JavaSE 8", "Java FP")
       */
      List<String> nodeCourses = Utils.getListOf("Node.js", "Express.js", "Eventloop");
      List<String> javaCourses = Utils.getListOf("Spring", "Maven", "Gradle", "Functional");

      Stream<List<String>> courses = Stream.of(nodeCourses, javaCourses);

      // Without flatMap: it will not find "js" because it's looking at entire lists
      long jsCourses = courses.filter(course -> course.contains("js")).count();
      System.out.println(jsCourses); // Output: 0

      // With flatMap: we flatten the lists and can filter individual course names
      jsCourses = Stream.of(nodeCourses, javaCourses)
         .flatMap(Collection::stream)
         .filter(course -> course.contains("js"))
         .count(); // Output: 2 ("Node.js", "Express.js")

      /*
       * Streams can be created from functions, such as with `Stream.iterate` or `generate`.
       *
       * These streams can be infinite — so how do we stop them?
       *
       * We use the `limit(n)` operation to restrict the stream to `n` elements.
       */
      Stream<Integer> firstTen = Stream.iterate(0, i -> i + 1);
      firstTen.limit(10)
         .forEach(System.out::println); // Prints numbers 0 to 9

      /*
       * Instead of using lambdas, we can also use method references or objects.
       *
       * Remember: lambdas are just a compact way of passing functions.
       * As long as a method matches the expected signature, it can be passed instead.
       */
      NumericOperator numericOperator = new NumericOperator();
      Stream<Integer> customStep = Stream.iterate(0, numericOperator::operate);
      customStep.limit(10)
         .forEach(System.out::println); // 0, 3, 6, 9, ..., 27
   }

   static class NumericOperator {
      public int operate(int x) {
         return x + 3;
      }
   }
}
