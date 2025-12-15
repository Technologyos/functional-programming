package com.technologyos.functional.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EmptyValueException extends IllegalArgumentException {

   public EmptyValueException(String message){
      super(message);
   }
}
