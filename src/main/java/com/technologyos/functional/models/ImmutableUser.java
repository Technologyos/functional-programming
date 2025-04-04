package com.technologyos.functional.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * Information:
 * 1.- no one can extend from it
 * 2.- once created an object cannot mutate
 * 3.- The constructor requires all properties to generate an object
 */
@Getter
@AllArgsConstructor
@ToString
public final class ImmutableUser implements Serializable {
   private final String fullName;
   private final String age;
   private final String email;
   private final List<String> friends;
}
