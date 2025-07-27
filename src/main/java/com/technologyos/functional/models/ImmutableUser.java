package com.technologyos.functional.models;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Information:
 * 1.- no one can extend from it
 * 2.- once created an object cannot mutate
 * 3.- The constructor requires all properties to generate an object
 * 4.- When accessing the friends, a copy is generated — the mutable list is not returned.
 */
public record ImmutableUser(String fullName, String age, String email, List<String> friends) implements Serializable {
   @Override
   public final List<String> friends() {
      return new LinkedList<>(friends);
   }
}
