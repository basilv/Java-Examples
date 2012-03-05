// Copyright 2007 by Basil Vandegriend.  All rights reserved.

package com.basilv.examples.errorhandling;

import static org.junit.Assert.*;
import org.junit.Test;


public class ReturnInFinallyBlockTest 
{

  @SuppressWarnings("finally") 
  boolean performBusinessOperation() {
    boolean operationResult = false;
    try {
      
      // Perform some business logic...
      operationResult = true;
      
    } catch (IllegalStateException e) {
      // Handle this exception..
      operationResult = false;
    } catch (IllegalArgumentException e) {
      // Handle this exception...
      operationResult = false;
    } finally {
      // Common cleanup...
      
      // Following line produces warning
      // "Finally block does not complete normally"
      return operationResult;
    }
  }
  
  
  @SuppressWarnings("finally") 
  private boolean isReturnWithinFinally() {
    try {
      if (true) throw new RuntimeException();
    } finally {
      return true; // This hides the exception
    }
  }

  private boolean isReturnOutsideFinally() {
    try {
      if (true) throw new RuntimeException();
    } finally {
      // Return outside finally block.
    }
    return true;
  }

  @Test
  public void testReturnFromFinallyBlockWithUnhandledException() {

    assertTrue(isReturnWithinFinally());
    try {
      isReturnOutsideFinally();
      fail("Expect exception");
    } catch (RuntimeException e) {
      // Expected case.
    }
  }

}
