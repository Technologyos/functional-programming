package com.technologyos.functional.utils;

import java.util.Arrays;
import java.util.List;

public class Utils {
   @SafeVarargs
   public static <T> List<T> getListOf(T... items) {
      return Arrays.asList(items);
   }
}
