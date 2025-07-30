package com.technologyos.functional.fundamentals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A stream is a sequence of data that can be processed with operations.
 *
 * Streams are not another way of organising data, like an array or a Collection.
 * Streams do not hold data; streams are all about processing data efficiently
 *
 * The power of stream comes from the multiple intermediate operations you can perform on the stream
 *
 * A stream pipeline consists of the operations that run on a stream to produce a result
 *
 * There are 3 parts to a stream pipeline:
 *  Source: where the stream comes from e.g array, collection of file.
 *  Intermediate operations: transforms the stream into another one. there can as few or as many as required.
 *  Terminal operation: Required to start the whole process and produces the result.
 *
 *  Streams can only be used once i.e. streams are no longer usable after a terminal operation
 *  completes (re-generate the stream if necessary)
 *
 * STREAM OPERATIONS
 *
 * 1. Intermediate Operations
 * filter(Predicate<T>)	Filters elements based on a condition.
 * map(Function<T, R>)	Transforms each element into another form.
 * flatMap(Function<T, Stream<R>>)	Transforms each element into a stream, then flattens all streams into one.
 * distinct()	Removes duplicate elements (based on equals).
 * sorted()	Sorts elements in natural order.
 * sorted(Comparator<T>)	Sorts elements using a custom comparator.
 * peek(Consumer<T>)	Performs a side-effect action for each element (mainly for debugging).
 * limit(long n)	Limits the stream to the first n elements.
 * skip(long n)	Skips the first n elements.
 * takeWhile(Predicate<T>) (Java 9+)	Takes elements from the stream while the condition is true.
 * dropWhile(Predicate<T>) (Java 9+)	Drops elements while the condition is true, then returns the rest.
 *
 * 2. Terminal Operations
 * forEach(Consumer<T>)	Performs an action for each element.
 * forEachOrdered(Consumer<T>)	Performs an action in encounter order (useful in parallel streams).
 * toArray()	Collects elements into an array.
 * reduce(...)	Reduces elements to a single value (e.g., sum, max).
 * collect(Collector<T, A, R>)	Collects elements into a collection or result (e.g., List, Set, Map).
 * min(Comparator<T>)	Returns the smallest element.
 * max(Comparator<T>)	Returns the largest element.
 * count()	Returns the number of elements.
 * anyMatch(Predicate<T>)	Returns true if any element matches the condition.
 * allMatch(Predicate<T>)	Returns true if all elements match the condition.
 * noneMatch(Predicate<T>)	Returns true if no element matches the condition.
 * findFirst()	Returns the first element (if any).
 * findAny()	Returns any element (more useful in parallel streams).
 *
 * 3. Java 9+ Stream Enhancements
 * ofNullable(T)	Creates a stream with a single element or an empty stream if the element is null.
 * iterate(seed, predicate, function)	Creates a stream by iterating with a condition.
 * dropWhile(Predicate<T>)	Skips elements as long as the condition holds.
 * takeWhile(Predicate<T>)	Takes elements while the condition holds.
 */
public class Streams {

   //the pipeline
   public long getTemps(){
      List<Double> temps = Arrays.asList(98.4, 100.2, 87.9, 102.8);
      return temps.stream()
         .peek(System.out::println)
         .filter(temp -> temp > 100)
         .peek(System.out::println)
         .count();
   }

   // 1. filter
   public void exampleFilter() {
      List<String> names = List.of("Armando", "Jaime", "Amanda");
      names.stream()
         .filter(name -> name.startsWith("A"))
         .forEach(System.out::println); // Armando, Amanda
   }

   // 2. map
   public void exampleMap() {
      List<String> names = List.of("Armando", "Miriam");
      names.stream()
         .map(String::toUpperCase)
         .forEach(System.out::println); // ARMANDO, MIRIAM
   }

   // 3. flatMap
   public void exampleFlatMap() {
      List<List<String>> nested = List.of(List.of("A", "B"), List.of("C"));
      nested.stream()
         .flatMap(List::stream)
         .map(String::toLowerCase)
         .forEach(System.out::println); // a, b, c
   }

   // 4. distinct
   public void exampleDistinct() {
      List<Integer> numbers = List.of(1, 2, 2, 3, 1);
      numbers.stream()
         .distinct()
         .forEach(System.out::println); // 1, 2, 3
   }

   // 5. sorted
   public void exampleSorted() {
      List<Integer> nums = List.of(5, 3, 1, 4);
      nums.stream()
         .sorted()
         .forEach(System.out::println); // 1, 3, 4, 5
   }

   // 6. sorted with comparator
   public void exampleSortedWithComparator() {
      List<String> names = List.of("Zoe", "Anna", "Bob");
      names.stream()
         .sorted(Comparator.reverseOrder())
         .forEach(System.out::println); // Zoe, Bob, Anna
   }

   // 7. peek
   public void examplePeek() {
      List<String> names = List.of("John", "Jane");
      names.stream()
         .peek(name -> System.out.println("Processing: " + name))
         .map(String::toUpperCase)
         .forEach(System.out::println);
   }

   // 8. limit
   public void exampleLimit() {
      Stream.iterate(1, n -> n + 1)
         .limit(5)
         .forEach(System.out::println); // 1 to 5
   }

   // 9. skip
   public void exampleSkip() {
      Stream.of(1, 2, 3, 4, 5)
         .skip(2)
         .forEach(System.out::println); // 3, 4, 5
   }

   // 10. takeWhile (Java 9+)
   public void exampleTakeWhile() {
      Stream.of(1, 2, 3, 6, 2)
         .takeWhile(n -> n < 5)
         .forEach(System.out::println); // 1, 2, 3
   }

   // 11. dropWhile (Java 9+)
   public void exampleDropWhile() {
      Stream.of(1, 2, 3, 6, 2)
         .dropWhile(n -> n < 5)
         .forEach(System.out::println); // 6, 2
   }

   //TERMINAL OPERATIONS
   // 12. forEach
   public void exampleForEach() {
      List.of("Java", "Python", "Go").forEach(System.out::println);
   }

   // 13. forEachOrdered (for parallel streams)
   public void exampleForEachOrdered() {
      List.of("A", "B", "C").parallelStream()
         .forEachOrdered(System.out::println);
   }

   // 14. toArray
   public void exampleToArray() {
      String[] arr = List.of("A", "B").stream().toArray(String[]::new);
      System.out.println(Arrays.toString(arr)); // [A, B]
   }

   // 15. reduce
   public void exampleReduce() {
      int sum = List.of(1, 2, 3).stream().reduce(0, Integer::sum);
      System.out.println(sum); // 6
   }

   // 16. collect
   public void exampleCollect() {
      List<String> upper = List.of("a", "b").stream()
         .map(String::toUpperCase)
         .collect(Collectors.toList());
      System.out.println(upper); // [A, B]
   }

   // 17. min
   public void exampleMin() {
      int min = Stream.of(3, 5, 1).min(Integer::compareTo).orElse(-1);
      System.out.println(min); // 1
   }

   // 18. max
   public void exampleMax() {
      int max = Stream.of(3, 5, 1).max(Integer::compareTo).orElse(-1);
      System.out.println(max); // 5
   }

   // 19. count
   public void exampleCount() {
      long count = Stream.of("A", "B", "C").count();
      System.out.println(count); // 3
   }

   // 20. anyMatch
   public void exampleAnyMatch() {
      boolean hasEven = Stream.of(1, 3, 4).anyMatch(n -> n % 2 == 0);
      System.out.println(hasEven); // true
   }

   // 21. allMatch
   public void exampleAllMatch() {
      boolean allEven = Stream.of(2, 4, 6).allMatch(n -> n % 2 == 0);
      System.out.println(allEven); // true
   }

   // 22. noneMatch
   public void exampleNoneMatch() {
      boolean noneNegative = Stream.of(1, 2, 3).noneMatch(n -> n < 0);
      System.out.println(noneNegative); // true
   }

   // 23. findFirst
   public void exampleFindFirst() {
      Optional<String> first = Stream.of("a", "b").findFirst();
      first.ifPresent(System.out::println); // a
   }

   // 24. findAny
   public void exampleFindAny() {
      Optional<String> any = Stream.of("x", "y").findAny();
      any.ifPresent(System.out::println); // x or y
   }

   //JAVA 9+ EXTRA STREAM METHODS
   // 25. Stream.ofNullable
   public void exampleOfNullable() {
      Stream.ofNullable(null).forEach(System.out::println); // does nothing
      Stream.ofNullable("Hello").forEach(System.out::println); // Hello
   }

   // 26. Stream.iterate (with condition) - Java 9+
   public void exampleIterateWithCondition() {
      Stream.iterate(1, n -> n < 10, n -> n + 2)
         .forEach(System.out::println); // 1, 3, 5, 7, 9
   }


   /**
    * The principle of lazy evaluation is that you get that you need only when you need it
    * for example, if you were displaying 10,000 records to a user, the principle of lazy
    * evaluation would be to retrieve 50 and while the user is viewing these, retrieve another
    * 50 in the background.
    *
    * Eager: evaluation would be to retrieve all 10,000 records in one go.
    *
    * With regard to streams, this means that nothing happens until the terminal operation occurs
    */
}
