package com.technologyos.functional.interfaces;

@FunctionalInterface
public interface Printer<T> {

   void print(T toPrint);
}
