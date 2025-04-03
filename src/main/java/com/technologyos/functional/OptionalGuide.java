package com.technologyos.functional;

import com.technologyos.functional.utils.EmptyValueException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Optional;

public class OptionalGuide {
   private static final Log logger = LogFactory.getLog(OptionalGuide.class);

   //Optional is used to represent a value that may or may not be present.
   public void optional() {
      Optional<Object> emptyOptional = Optional.empty();
      logger.info(emptyOptional.isPresent()); //false

      Optional<String> optional = Optional.of("Java 8");
      logger.info(optional.get()); //"Java 8"

      Optional<String> nullableOptional = Optional.ofNullable(printName(20, "Jose"));
      String something = nullableOptional.orElse("Empty value");
      logger.info(something);

      something = optional.orElseGet(this::doSomething);
      logger.info(something);

      something = optional.orElseGet(() -> /*doSomething */ "");
      logger.info(something);

      nullableOptional.ifPresent(System.out::println);
      //nullableOptional.ifPresentOrElse(System.out::println, () -> System.out.println("without content"));
      nullableOptional.orElseThrow(()-> new EmptyValueException("The value cannot be null"));

      Optional<String> subOptional = nullableOptional.filter(String::isEmpty);
      Optional<String> transformOptional = optional.map(String::toLowerCase);

      //examples
      //GOOD
      String vResult = getName(24)
         .map(value -> value.toLowerCase().trim())
         .orElse("without content");
      logger.info(vResult);

      //BAD
      vResult = doSomethingWithoutOptional(23);
      if(vResult != null){
         vResult = vResult.toLowerCase().trim();
      }else{
         vResult = "without content";
      }
      logger.info(vResult);
   }

   private Optional<String> getName(int age){
      if(age < 18){
         return Optional.empty();
      }

      return Optional.of("Hello World");
   }

   private String doSomethingWithoutOptional(int age){
      if(age < 18){
         return null;
      }

      return "Hello World";
   }

   private String printName(int age, String name){
      if(age > 18){
         return "your name is: " + name + "and your age is " + age;
      }
      return null;
   }

   private String doSomething(){
      //TODO doSomething..
      return "";
   }
}
