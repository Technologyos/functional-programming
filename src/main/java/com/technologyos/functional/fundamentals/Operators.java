package com.technologyos.functional.fundamentals;

import com.technologyos.functional.utils.Utils;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Operators {
   static void operadores() {
      /*
       * Streams in Java are designed for fluent data processing.
       * However, they have strict rules about their lifecycle and usage.
       *
       * This method explores common mistakes (like reusing a stream),
       * and clarifies differences between intermediate and terminal operations.
       */

      // Example: simple transformation using map
      Stream<Integer> numbers = Utils.getListOf(1, 2, 3, 4).stream();
      numbers.map(i -> i * 2); // creates a new Stream (lazy, not executed yet)

      // Example: correct chaining with a new Stream instance
      Stream<Integer> numbersV2 = Utils.getListOf(1, 2, 3, 4).stream();
      Stream<Integer> numbersBy2 = numbersV2.map(i -> i * 2); // still lazy

      //Error: Attempt to reuse an already consumed stream (numbersV2)
      // Stream<Integer> squares = numbersV2.map(i -> i * i); // IllegalStateException

      /*
       * Streams are consumed after a terminal operation. Once used, they cannot be reused.
       * Let's go further with a real-world-like example: filtering and extracting course names.
       */
      Stream<String> courses = Stream.of(
         "Java:Introductorio",
         "Python:Introductorio",
         "Machine Learning:Avanzado",
         "JavaScript:Introductorio",
         "Node.js:Intermedio",
         "Android:Intermedio",
         "iOS:Intermedio"
      );

      // Step-by-step transformation chain
      Stream<String> introductoryCourses = courses
         .filter(course -> course.contains("Introductorio"));

      Stream<String[]> partsNames = introductoryCourses
         .map(course -> course.split(":"));

      Stream<String[]> partsWithData = partsNames
         .filter(parts -> parts.length > 1);

      Stream<String> justNamesStream = partsWithData
         .map(parts -> parts[0])
         .filter(name -> !name.isEmpty());

      // Alternatively, we could write it all in one fluent chain:
       /*
       Stream<String> justNamesStream = courses
           .filter(c -> c.contains("Introductorio"))
           .map(c -> c.split(":"))
           .filter(parts -> parts.length > 1)
           .map(parts -> parts[0])
           .filter(name -> !name.isEmpty());
       */

      /*
       * Until now, no actual data processing has occurred.
       * Streams are lazily evaluated and will only execute once a terminal operation is invoked.
       */

      // Let's simulate stream processing across multiple layers of a system
      Stream<List<String>> coursesStream = getCourses();
      Stream<String> courseDataStream = flatMapCourses(coursesStream);
      Stream<String[]> partsStream = splitInformation(courseDataStream);
      Stream<String[]> filteredPartsStream = filterAdvanceCourses(partsStream);
      Stream<String> advanceCourseNamesStream = getNamesStream(filteredPartsStream);

      // Still no data processed. Only now, with a terminal operation, the pipeline runs:
      List<String> advanceCourseNamesList = advanceCourseNamesStream.collect(Collectors.toList());

      // This would also work (another terminal operation example):
      // long totalAdvanceCourses = advanceCourseNamesStream.count();
   }

   public static Stream<List<String>> getCourses() {
      List<String> nodeCourses = Utils.getListOf(
         "Node.js:Intermedio", "Express.js:Intermedio", "Eventloop:Avanzado"
      );
      List<String> javaCourses = Utils.getListOf(
         "Spring:Introductorio", "Maven:Intermedio", "Gradle:Avanzado", "Functional:Introductorio"
      );
      return Stream.of(nodeCourses, javaCourses);
   }

   static Stream<String> flatMapCourses(Stream<List<String>> courses) {
      return courses.flatMap(Collection::stream);
   }

   static Stream<String[]> splitInformation(Stream<String> courseData) {
      return courseData.map(course -> course.split(":"));
   }

   static Stream<String[]> filterAdvanceCourses(Stream<String[]> courses) {
      return courses
         .filter(data -> data.length > 1)
         .filter(data -> Objects.equals(data[1], "Avanzado"));
   }

   static Stream<String> getNamesStream(Stream<String[]> coursesData) {
      return coursesData.map(data -> data[0]);
   }
}
