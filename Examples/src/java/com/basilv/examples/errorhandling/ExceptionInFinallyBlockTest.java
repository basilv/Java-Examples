// Copyright 2007 by Basil Vandegriend.  All rights reserved.

package com.basilv.examples.errorhandling;

import static org.junit.Assert.*;
import org.junit.Test;

public class ExceptionInFinallyBlockTest 
{  
  private void haveExceptionInFinallyBlock() {
    try {
      if (true) throw new IllegalArgumentException();
    } finally {
      if (true) throw new NullPointerException();
    }
  }
  
  @Test
  public void testHaveExceptionInFinallyBlock() {
    try {
      haveExceptionInFinallyBlock();
      fail("Expect exception");
    } catch (NullPointerException e) {
      // Expected case.
    }
  }

}
