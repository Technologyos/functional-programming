package com.technologyos.functional.fundamentals;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PureAndImpureFunctionTest {

   @Test
   public void validateIfHasAvailableFunds(){
      assertTrue(PureAndImpureFunction.hasAvailableFunds(1300.00));
      assertFalse(PureAndImpureFunction.hasAvailableFunds(-20.00));
   }

   @Test
   public void validate_sum(){
      assertEquals(10, PureAndImpureFunction.sum(5, 5));
   }

}
