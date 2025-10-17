package com.technologyos.functional.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EmptyValueException extends IllegalArgumentException {
   public EmptyValueException(String message){
      super(message);
   }
}
