package com.technologyos.functional.moduls;

import java.io.Serializable;
import java.util.List;

public class MutableUser implements Serializable {
   private String fullName;
   private String age;
   private String email;
   private List<String> friends;

   public MutableUser(){}

   public MutableUser(List<String> friends) {
      this.friends = friends;
   }

   public String getFullName() {
      return fullName;
   }

   public void setFullName(String fullName) {
      this.fullName = fullName;
   }

   public String getAge() {
      return age;
   }

   public void setAge(String age) {
      this.age = age;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public List<String> getFriends() {
      return friends;
   }

   @Override
   public String toString() {
      return "MutableUser{" +
         "fullName='" + fullName + '\'' +
         ", age='" + age + '\'' +
         ", email='" + email + '\'' +
         ", friends=" + friends +
         '}';
   }
}
