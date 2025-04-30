package com.technologyos.functional.fundamentals;

import com.technologyos.functional.utils.Utils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class IntermediateOperations {
   static void operations() {
      /*
       * Intermediate operations are operations that transform a stream into another stream.
       * They are lazy — they do not process any data until a terminal operation is invoked.
       *
       * Common intermediate operations:
       *  - filter()
       *  - map()
       *  - flatMap()
       *  - distinct()
       *  - limit()
       *  - peek()
       */

      // filter(): Returns a new stream containing only elements that match the predicate.
      Stream<Integer> evenNumbersStream = Stream.iterate(0, i -> i + 1).limit(10); // limit to prevent infinite loop
      Stream<Integer> onlyEvenNumbers = evenNumbersStream.filter(i -> i % 2 == 0); // [0, 2, 4, 6, 8]

      // map(): Transforms each element from type T to type R using a Function<T, R>.
      Stream<String> namesStream = Stream.of("Armando", "Jose", "Carmen", "Carlos", "Maria");
      Stream<Integer> lengthStream = namesStream.map(String::length);

      // flatMap(): Flattens a stream of collections/streams into a single stream of elements.
      List<String> nodeCourses = Utils.getListOf("Node.js", "Express.js", "Eventloop");
      List<String> javaCourses = Utils.getListOf("Spring", "Maven", "Gradle", "Functional");

      Stream<List<String>> nestedCoursesStream = Stream.of(nodeCourses, javaCourses);

      // Flatten the nested structure:
      Stream<String> allCourses = nestedCoursesStream.flatMap(Collection::stream);
      // Result: "Node.js", "Express.js", ..., "Functional"

      // distinct(): Removes duplicate elements based on Object.equals().
      Stream<String> heroesStream = Stream.of("Peter", "Logan", "Luisa", "Clark", "Gwen", "Logan", "Peter");
      Stream<String> uniqueHeroes = heroesStream.distinct(); // ["Peter", "Logan", "Luisa", "Clark", "Gwen"]

      // limit(n): Returns a stream containing at most 'n' elements.
      Stream<String> limitedHeroes = uniqueHeroes.limit(2); // e.g. ["Peter", "Logan"]

      // peek(): Performs an action (side-effect) on each element as it passes through the stream.
      Stream<String> previewedHeroes = limitedHeroes.peek(hero -> System.out.println("A hero was chosen: " + hero));
      // Still returns a Stream<String>, unmodified

      /*
       * Important note:
       * Intermediate operations build up a pipeline.
       * No data is processed until a terminal operation like forEach(), collect(), etc., is called.
       */

      // Example: Consume the pipeline
      previewedHeroes.forEach(hero -> {}); // triggers all operations above

      /*
       * Other intermediate operations include:
       * - sorted(): Sorts elements based on a Comparator or natural order.
       * - mapToInt(), mapToDouble(), mapToLong(): Convert Stream<T> into primitive streams.
       *
       * All intermediate operations return a new Stream.
       * They are designed to be chained for fluent-style data processing.
       */
   }
}
