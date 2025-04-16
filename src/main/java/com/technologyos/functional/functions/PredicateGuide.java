package com.technologyos.functional.functions;

import com.technologyos.functional.fundamentals.Functional;

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
      Predicate<String> notBlank = s -> !s.trim().isEmpty();
      Predicate<String> hasMinLength = s -> s.length() >= 8;
      Predicate<String> hasNumber = s -> s.matches(".*\\d.*");
      Predicate<String> hasUpperCase = s -> s.matches(".*[A-Z].*");

      Predicate<String> isValidPassword = notBlank
         .and(hasMinLength)
         .and(hasNumber)
         .and(hasUpperCase);

      if (isValidPassword.test(password)) {
         System.out.println("Password is valid.");
      } else {
         System.out.println("Password is invalid.");
      }
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
      isXLargerThanY.test("MyText", "Text"); // TRUE
   }

   private static void predicateMethods(){
      Predicate<String> isBlank = s -> s.trim().isEmpty();
      Predicate<String> isNotBlank = isBlank.negate();

      System.out.println(isNotBlank.test("  ")); // false
      System.out.println(isNotBlank.test("Java")); // true

      Predicate<String> endsWithPDF = s -> s.endsWith(".pdf");
      Predicate<String> endsWithDoc = s -> s.endsWith(".doc");

      Predicate<String> isDocument = endsWithPDF.or(endsWithDoc);

      System.out.println(isDocument.test("resume.pdf")); // true
      System.out.println(isDocument.test("notes.doc"));  // true
      System.out.println(isDocument.test("image.png"));  // false

      Predicate<String> hasLength = s -> s.length() >= 8;
      Predicate<String> containsAtSymbol = s -> s.contains("@");

      Predicate<String> validEmail = hasLength.and(containsAtSymbol);

      System.out.println(validEmail.test("user@example.com")); // true
      System.out.println(validEmail.test("user.com"));         // false

      Predicate<String> isAdmin = Predicate.isEqual("admin");

      System.out.println(isAdmin.test("admin")); // true
      System.out.println(isAdmin.test("user"));  // false
   }

   private void validateNumbers(){
      Functional<Integer> validate = i -> i < 0;
      System.out.println("Is Negative" + validate.isNegative(-1));
      System.out.println("Is Negative" + validate.isNegative(10));

      IntPredicate intPredicate = i -> i < 0;
      System.out.println("Is Negative" + intPredicate.test(-1));
      System.out.println("Is Negative" + intPredicate.test(10));

      int x = 4;
      System.out.println("Is" +x+ "even? "+ check(x,  n -> n % 2 == 0));
      x = 7;
      System.out.println("Is" +x+ "even? "+ check(x,  n -> n % 2 == 0));

      String name = "Jose Armando";
      System.out.println("Does" +name+ "start with A"+ check(name, s -> s.startsWith("A")));
      name = "Armando";
      System.out.println("Does" +name+ "start with A"+ check(name, s -> s.startsWith("A")));
   }

   public static <T> boolean check(T t, Predicate<T> validation){
      return validation.test(t);
   }
}
