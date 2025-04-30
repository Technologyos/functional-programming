package com.technologyos.functional.fundamentals;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.technologyos.functional.fundamentals.Operators.getCourses;

public class FinalOperations {
   static void operations() {
      /*
       * Terminal operations are operations that **trigger the actual iteration** over a Stream
       * and produce a result or a side-effect.
       *
       * Once a terminal operation is invoked, the stream pipeline is considered consumed.
       *
       * Common terminal operations include:
       *  - anyMatch()
       *  - allMatch()
       *  - noneMatch()
       *  - collect()
       *  - count()
       *  - findAny()
       *  - findFirst()
       *  - forEach()
       *  - min()
       *  - max()
       *  - reduce()
       *  - toArray()
       */

      // anyMatch(): Returns true if **any** element matches the given predicate.
      Stream<Integer> numbersStream = Stream.of(1, 2, 3, 4, 5, 6, 7, 11);
      boolean biggerThanTen = numbersStream.anyMatch(i -> i > 10); // true (because of 11)

      // allMatch(): Returns true if **all** elements match the given predicate.
      Stream<Integer> agesStream = Stream.of(19, 21, 35, 45, 12);
      boolean allLegalDrinkingAge = agesStream.allMatch(age -> age > 18); // false (12 is underage)

      // noneMatch(): Returns true if **no** elements match the predicate.
      Stream<Integer> oddNumbers = Stream.of(1, 3, 5, 7, 9, 11);
      boolean allAreOdd = oddNumbers.noneMatch(i -> i % 2 == 0); // true

      // collect(): Collects the stream elements into a collection using a Collector.
      Stream<String> studentsStream = Stream.of("You", "Me");
      List<String> studentsList = studentsStream.collect(Collectors.toList());

      // count(): Returns the number of elements in the stream.
      Stream<Integer> yearsStream = Stream.of(1990, 1991, 1994, 2000, 2010, 2019, 2020);
      long yearsCount = yearsStream.count(); // 7

      // findAny(): Returns an Optional with **any** element (useful in parallel streams).
      Stream<List<String>> coursesStream = getCourses();
      Optional<List<String>> coursesOptional = coursesStream.findAny();

      // findFirst(): Returns an Optional with the **first** element (predictable order).
      Stream<List<String>> availableCourses = getCourses();
      Optional<List<String>> firstCoursesOptional = availableCourses.findFirst();

      // forEach(): Applies the given Consumer to each element.
      Stream<List<String>> courses = getCourses();
      courses.forEach(courseList -> System.out.println("Available courses: " + courseList));

      // min(): Returns the minimum element according to the Comparator.
      Stream<Long> bigNumbers = Stream.of(100L, 200L, 1000L, 5L);
      Optional<Long> minimumOptional = bigNumbers.min(Long::compareTo); // 5

      // max(): Returns the maximum element according to the Comparator.
      Stream<Long> bigNumbersAgain = Stream.of(100L, 200L, 1000L, 5L);
      Optional<Long> maximumOptional = bigNumbersAgain.max(Long::compareTo); // 1000

      // reduce(): Combines elements using an accumulator. There are 3 forms:

      // 1. reduce(BinaryOperator) → Optional<T>
      Stream<String> aLongStoryStream = Stream.of("When", "he", "woke", "up,", "the", "dinosaur", "was", "still", "there.");
      Optional<String> longStoryOptional = aLongStoryStream.reduce((prev, next) -> prev + " " + next);
      longStoryOptional.ifPresent(System.out::println);
      // Output: "When he woke up, the dinosaur was still there."

      // 2. reduce(identity, BinaryOperator) → T
      Stream<Integer> firstTenNumbersStream = Stream.iterate(0, i -> i + 1).limit(10);
      int sumOfFirstTen = firstTenNumbersStream.reduce(0, Integer::sum); // 0 + 1 + ... + 9 = 45

      // 3. reduce(identity, accumulator, combiner) → U (used in parallel)
      Stream<String> aLongStoryStreamAgain = Stream.of("When", "he", "woke", "up,", "the", "dinosaur", "was", "still", "there.");
      int charCount = aLongStoryStreamAgain.reduce(
         0,
         (count, word) -> count + word.length(), // accumulator
         Integer::sum // combiner (used in parallel processing)
      );
      System.out.println("Total character count: " + charCount);
   }
}
