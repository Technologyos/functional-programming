package com.technologyos.functional.interfaces;

@FunctionalInterface
public interface Math {
   Double execute(Double a, Double b);

   default Double sum(Double a, Double b) {
      return  a + b;
   }
}
