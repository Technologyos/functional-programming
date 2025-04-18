package com.technologyos.functional.utils.builder;


import com.technologyos.functional.models.Account;
import com.technologyos.functional.models.Phone;

public class BuilderNoChaining {
   private final String id;
   private String firstName;
   private String lastName;
   private double currentBalance;
   private Phone phone;

   public BuilderNoChaining(String id) {
      checkStringField(id, "ID");
      this.id = id;
      firstName = "";
      lastName = "";
      currentBalance = 0.0;
      phone = null;
   }

   public void withFirstName(String firstName) {
      checkStringField(firstName, "First Name");
      this.firstName = firstName;
   }

   public void withLastName(String lastName) {
      checkStringField(lastName, "First Name");
      this.lastName = lastName;
   }

   public void withBalance(double balance) {
      currentBalance = balance;
   }

   public void withPhone(String phone){
      checkStringField(phone, "Phone");
      this.phone = new Phone(phone);
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
