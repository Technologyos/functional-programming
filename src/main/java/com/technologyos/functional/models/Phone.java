package com.technologyos.functional.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Phone {
   private String country;
   private char[] countryCode;
   private char[] digits;

   public Phone(String phoneString) {}
}
