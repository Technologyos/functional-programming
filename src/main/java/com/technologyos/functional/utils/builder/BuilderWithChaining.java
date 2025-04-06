package com.technologyos.functional.utils.builder;

import com.technologyos.functional.models.Account;
import com.technologyos.functional.models.Phone;

public class BuilderWithChaining {
   private final String id;
   private String firstName;
   private String lastName;
   private double currentBalance;
   private Phone phone;

   public BuilderWithChaining(String id) {
      checkStringField(id, "ID");
      this.id = id;
      firstName = "";
      lastName = "";
      currentBalance = 0.0;
      phone = null;
   }

   public BuilderWithChaining withFirstName(String firstName) {
      checkStringField(firstName, "First Name");
      this.firstName = firstName;
      return this;
   }

   public BuilderWithChaining withLastName(String lastName) {
      checkStringField(lastName, "First Name");
      this.lastName = lastName;
      return this;
   }

   public BuilderWithChaining withBalance(double balance) {
      currentBalance = balance;
      return this;
   }

   public BuilderWithChaining withPhone(String phone) {
      checkStringField(phone, "Phone");
      this.phone = new Phone(phone);
      return this;
   }

   public Account buildAccount() {
      return new Account(
         id,
         firstName,
         lastName,
         currentBalance,
         phone
      );
   }

   private void checkStringField(String field, String fieldName) {
      if (field == null || field.isEmpty()) {
         throw new IllegalArgumentException(fieldName + " is not valid");
      }
   }
}
