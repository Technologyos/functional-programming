package com.technologyos.functional.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
   private String street;
   private String interiorNumber;
   private String exteriorNumber;
   private String state;
   private String zip;
}
