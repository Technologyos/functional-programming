package com.technologyos.functional.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ImmutableUserTest {

   @Test
   void validate_immutable_user(){
      ImmutableUser immutableUser = new ImmutableUser("Jose Armando",
         "28", "my_email@gmail.com", Arrays.asList("Juan", "Andrea", "Patricia"));

      assertIterableEquals(Arrays.asList("Juan", "Andrea", "Patricia"), immutableUser.friends());

      List<String> friends = new ArrayList<>(immutableUser.friends());
      friends.clear();
      friends.add("Carmen");

      assertIterableEquals(Arrays.asList("Juan", "Andrea", "Patricia"), immutableUser.friends());
   }
}
