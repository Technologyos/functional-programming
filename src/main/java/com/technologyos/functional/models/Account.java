package com.technologyos.functional.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Account {
   private String id;
   private String firstName;
   private String lastName;
   private double currentBalance;
   private Phone phone;
}
