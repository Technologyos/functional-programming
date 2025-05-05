package com.technologyos.functional.fundamentals;

import java.util.Arrays;
import java.util.List;

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
 *  Intermediate operations: transforms the stream into another one. there can as few or as many as requiered.
 *  Terminal operation: Required to start the whole process and produces the result.
 *  Streams can only be used once i.e. streams are no longer usable after a terminal operation
 *  completes (re-generate the stream if necessary)
 *
 *
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
