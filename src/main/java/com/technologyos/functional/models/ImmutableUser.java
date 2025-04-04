package com.technologyos.functional.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

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
@Getter
@AllArgsConstructor
@ToString
public final class ImmutableUser implements Serializable {
   private final String fullName;
   private final String age;
   private final String email;
   private final List<String> friends;

   public final List<String> getFriends() {
      return new LinkedList<>(friends);
   }
}
