package com.technologyos.functional.fundamentals;

import com.technologyos.functional.utils.Utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class StreamsAndIterables {

   static void iterables() {
      /*
       * One of Java's major innovations was introducing Collections:
       * Lists, Maps, Sets, Trees, and many other data structures.
       *
       * Before Collections, we had arrays — but iterating over an array
       * or a list was basically the same manual process.
       */
      List<String> namesList = Utils.getListOf("Armando", "Miriam", "Elizabeth");

      // Traditional iteration using enhanced for-loop
      for (String name : namesList) {
         System.out.println(name);
      }

      /*
       * While this approach works, it has some limitations:
       * - The code becomes verbose, especially when you need to perform multiple operations.
       * - It's not very flexible for more complex processing.
       */
      for (String name : namesList) {
         if (name != null && !name.isEmpty()) {
            System.out.println("Do something!");
         }
      }

      /*
       * Also, trying to perform this kind of iteration in parallel
       * would be very complicated and error-prone.
       */
   }

   static void streams() {

      /*
       * You can think of a Stream as a **flow of data** — like a river.
       *
       * Data flows through the stream without waiting for manual iteration.
       *
       * A Stream is **self-iterable**: once you define a source, the stream will manage
       * the flow and iteration for you automatically.
       *
       * Streams can be created from almost any source: existing collections,
       * external input, or even from infinite generators.
       */

      // A simple stream with fixed data
      Stream<String> stringStream = Stream.of("Hello", "Goodbye");

      //You can even create streams from external sources, like user input.
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      Stream<String> bufferStream = in.lines(); // Stream of user input lines

      //Very commonly, we create streams from existing collections:
      List<String> coolNames = Utils.getListOf("Armando", "Miriam", "Elizabeth", "Jaime");
      Stream<String> coolNamesStream = coolNames.stream();

      Set<String> courses = new HashSet<>(coolNames);
      Stream<String> coursesStream = courses.stream();

      //Streams can also be generated dynamically, for example, by functions:
      AtomicInteger counter = new AtomicInteger();
      Stream<Integer> countingStream = Stream.generate(counter::getAndIncrement);

      /*
       * One of the most powerful features of Streams is that they can be processed
       * in **parallel** very easily — without needing to manually manage threads.
       */
      Stream<Integer> countingInParallelStream = countingStream.parallel();

      // Parallel processing will become really interesting when we start
      // applying operations like filter, map, reduce, etc.
   }

}
