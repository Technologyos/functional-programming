package com.technologyos.functional.moduls;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MutableUser implements Serializable {
   private String fullName;
   private String age;
   private String email;
   private List<String> friends;

   public MutableUser(List<String> friends) {
      this.friends = friends;
   }
}
