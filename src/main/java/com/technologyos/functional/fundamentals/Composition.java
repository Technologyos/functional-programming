package com.technologyos.functional.fundamentals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Composition {
   //Since functions are types, we can store them as variables (function objects).
   private static final Function<String, File> CREATE_FILE = File::new;

   /**
    * Functions can be created from method references.
    * This one reads lines from a given File.
    */
   private static final Function<File, List<String>> LINES_FROM_FILE = Composition::getLinesFromFile;

   //This function filters a list of strings, removing empty or null lines.
   private static final Function<List<String>, List<String>> FILTER = list -> {
      List<String> resultList = new LinkedList<>();
      list.forEach(line -> addIfNotEmpty(resultList, line));
      return resultList;
   };

   /**
    * Creates a composed function that:
    * 1. Converts the input string (a file path) into a File.
    * 2. Reads all lines from that file into a List<String>.
    * 3. Filters the list, removing empty or whitespace-only lines.
    *
    * `compose` applies the functions in reverse order: last defined is executed first.
    */
   static List<String> getLinesWithContentUsingCompose(String pathToFile) {
      return FILTER
         .compose(LINES_FROM_FILE) // Takes the result of LINES_FROM_FILE
         .compose(CREATE_FILE)     // Takes the result of CREATE_FILE
         .apply(pathToFile);       // Input goes to CREATE_FILE
   }

   /**
    * Equivalent to the previous method, but split step-by-step for clarity.
    * This shows how `compose` chains the functions in reverse.
    */
   static List<String> stepByStepWithCompose(String pathToFile) {
      Function<String, List<String>> createFileAndGetLines = LINES_FROM_FILE.compose(CREATE_FILE);
      Function<String, List<String>> fullPipeline = FILTER.compose(createFileAndGetLines);
      return fullPipeline.apply(pathToFile);

      // Alternatively:
      // List<String> lines = createFileAndGetLines.apply(pathToFile);
      // return FILTER.apply(lines);
   }

   /**
    * Same as getLinesWithContentUsingCompose, but using `andThen` instead of `compose`.
    * `andThen` applies functions in the order they are written.
    */
   static List<String> getLinesWithContentUsingAndThen(String pathToFile) {
      return CREATE_FILE
         .andThen(LINES_FROM_FILE)
         .andThen(FILTER)
         .apply(pathToFile);
   }

   /**
    * Equivalent step-by-step version using `andThen`.
    */
   static List<String> stepByStepWithAndThen(String pathToFile) {
      Function<String, List<String>> createFileAndGetLines = CREATE_FILE.andThen(LINES_FROM_FILE);
      Function<String, List<String>> fullPipeline = createFileAndGetLines.andThen(FILTER);
      return fullPipeline.apply(pathToFile);
   }

   /**
    * Reads all lines from a file and returns them as a List of Strings.
    */
   private static List<String> getLinesFromFile(File file) {
      try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
         return reader.lines().collect(Collectors.toList());
      } catch (IOException e) {
         return Collections.emptyList();
      }
   }

   /**
    * Adds the string to the list only if it is non-null, non-empty, and not just whitespace.
    */
   private static void addIfNotEmpty(List<String> list, String s) {
      if (s != null && !s.trim().isEmpty()) {
         list.add(s);
      }
   }

   /**
    * Sample main method to run the composed function.
    */
   public static void main(String[] args) {
      String pathToFile = "/path/to/file.txt";

      List<String> contentLines = getLinesWithContentUsingCompose(pathToFile);

      contentLines.forEach(System.out::println);
   }
}
