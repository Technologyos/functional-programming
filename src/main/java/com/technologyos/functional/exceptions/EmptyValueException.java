package com.technologyos.functional.exceptions;

public class EmptyValueException extends IllegalArgumentException {
   public EmptyValueException(){}

   public EmptyValueException(String message){
      super(message);
   }
}
