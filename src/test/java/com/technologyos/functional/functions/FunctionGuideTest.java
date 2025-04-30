package com.technologyos.functional.functions;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FunctionGuideTest {

   @Test
   public void multiply(){
      List<Integer> myNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);

      //square
      assertIterableEquals(Arrays.asList(1, 4, 9, 16, 25, 36),
         FunctionGuide.applyMathInTheList(myNumbers, x -> x * x));

      //cube
      assertIterableEquals(Arrays.asList(1, 8, 27, 64, 125, 216),
         FunctionGuide.applyMathInTheList(myNumbers, x -> x * x * x));

      //toNegative
      assertIterableEquals(Arrays.asList(-1, -2, -3, -4, -5, -6),
         FunctionGuide.applyMathInTheList(myNumbers, x -> -1 * x));
   }

}
