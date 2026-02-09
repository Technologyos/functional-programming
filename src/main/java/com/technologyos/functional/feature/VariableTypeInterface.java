package com.technologyos.functional.feature;

/**
 * Where they can be used:
 *  - constructors, methods or init blocks
 * Where they CANNOT be used
 *  - constructors/methods parameters or instance/class variables ("local")
 *  - caveat: lambdas where the parameter type can be inferred
 * A var is always initialised on the same statement where it is declared.
 * e.g. var x = 8;
 *
 * The value of var can change but the type cannot.
 * var cannot be simply initialised to null; cast the null to a type first.
 * var not allowed in multi-variable declarations.
 * var is a reserved name but not a reserved word
 *   - var var = 8;
 *   - identifiers can be called var but not type names e.g. classes, interfaces, enums.
 */
public class VariableTypeInterface {

   public static void main(String[] args) {
      var name = "Armando";
      var age = 29;
   }
}
