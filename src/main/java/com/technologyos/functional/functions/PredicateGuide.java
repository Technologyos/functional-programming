package com.technologyos.functional.functions;

import java.util.function.*;

public class PredicateGuide {

   /**
    * The predicate is a predefined functional interface in Java It helps with manageability of code,
    * aids in unit-testing, and provides various handy functions.
    * Methods:
    * 1.- isEqual(Object targetRef) : Returns a predicate that tests if two arguments are equal according to Objects.equals(Object, Object)
    * 2.- and(Predicate other) : Returns a composed predicate that represents a short-circuiting logical AND of this predicate and another
    * 3.- negate() : Returns a predicate that represents the logical negation of this predicate
    * 4.- or(Predicate other) : Returns a composed predicate that represents a short-circuiting logical OR of this predicate and another
    * 5.- test(T t) : Evaluates this predicate on the given argument.boolean test(T t)
    */
   private static void checkPassword(String password) {
      Predicate<String> isAllWhite = s -> s.trim().isEmpty();
      Predicate<String> hasGoodLength = s -> s.length() > 8;
      Predicate<String> hasAtLeastOneNumber = s -> s.matches("\\d+");
      Predicate<String> hasAnUpperCaseLetter = s -> s.matches("[A-Z]+");

      Predicate<String> isAValidPassword = s ->
         !isAllWhite.test(s)
            && hasGoodLength.test(s)
            && hasAtLeastOneNumber.test(s)
            && hasAnUpperCaseLetter.test(s);

      isAValidPassword.test(password);
   }

   private static boolean validatePasswordSimplified(String password){
      Predicate<String> validatePassword = pass -> !pass.trim().isEmpty()
         && pass.length() > 8 && pass.matches("\\d+") && pass.matches("[A-Z]+");
      return validatePassword.test(password);
   }

   private static void validations() {
      IntPredicate intPredicate;
      DoublePredicate doublePredicate;
      LongPredicate longPredicate;

      //And if you need to do some more complex validation of two elements such as comparing that one data is larger than another
      BiPredicate<String, String> isXLargerThanY = (x, y) -> x.length() > y.length();

      isXLargerThanY.test("Lobo", "Perrito"); // False
   }

   private static void predicateMethods(){
      Predicate<String> isAllWhite = s -> s.trim().isEmpty();
      System.out.println(isAllWhite.negate());
      //Predicate.not(String::isEmpty);

      boolean flag = isAllWhite.or(s-> s.equals("")).test("hello world");

      Predicate<String> hasGoodLength = s -> s.length() > 8;
      boolean flag2 = hasGoodLength.and(s-> s.length() < 100).test("hello");
   }
}
