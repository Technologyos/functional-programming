package com.technologyos.functional.moduls;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@ToString
public class User implements Serializable {
   private final String name;
   private final Integer age;
}
