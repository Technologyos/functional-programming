package com.technologyos.functional.models;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MutableUserTest {

   @Test
   public void validate_mutable_user(){
      MutableUser mutableUser = new MutableUser(Arrays.asList("Juan", "Andrea", "Patricia"));
      mutableUser.setAge("28");
      mutableUser.setFullName("Jose Armando");
      mutableUser.setEmail("armando@gmail.com");

      assertIterableEquals(Arrays.asList("Juan", "Andrea", "Patricia"), mutableUser.getFriends());

      mutableUser.setFriends(Arrays.asList("Carmen", "Heina"));

      assertIterableEquals(Arrays.asList("Carmen", "Heina"), mutableUser.getFriends());
   }
}
