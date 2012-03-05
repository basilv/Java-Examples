// Copyright 2009 by Basil Vandegriend.  All rights reserved.

package com.basilv.examples.concurrency;

import static org.junit.Assert.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.*;

public class DateProcessorMultiThreadedTest
{
  
  @Before
  public void setUp() {
    DateProcessor.setInstanceForTesting(null);
  }

  @Test
  public void multithreadedExecutionOneThread() {
    verifyMultiThreadedExecution(1); // Should always pass
  }

  @Test
  public void multithreadedExecutionFewThreads() {
    verifyMultiThreadedExecution(3); // Always seems to pass
  }
  
  @Test
  public void multithreadedExecutionManyThreads() {
    boolean successful = false;
    try {
      // 500 always seems to fail given my hardware/software platform, even when 
      // entire Examples suite is executed. Smaller numbers inconsistently fail, especially
      // when entire suite is executed.
      verifyMultiThreadedExecution(500); 
      successful = true;
    } catch (AssertionError e) {
      // Expected case.
      successful = false;
      System.err.print("Expected failure due to:" + e.getMessage());
    }
    if (successful) {
      fail("Expected exception");
    }
  }

  private void verifyMultiThreadedExecution(int totalExecutions) {
    int totalValid = totalExecutions / 2;
    List<DateProcessorThread> threads = new ArrayList<DateProcessorThread>(totalExecutions);

    // Execute threads
    for (int i = 0; i < totalValid; i++) {
      DateProcessorThread thread = createAndStartThread("2009-11-25", true);
      threads.add(thread);
      
    }
    for (int i = 0; i < totalExecutions - totalValid; i++) {
      DateProcessorThread thread = createAndStartThread("foo", false);
      threads.add(thread);
    }
    
    // Wait for all threads to be finished
    for (Thread thread : threads) {
      try {
        thread.join();
      } catch (InterruptedException e) {
        // Ignore for testing.
      }
    }
    
    // Verify totals
    assertEquals("Total execution count", totalExecutions, DateProcessor.getInstance().getDatesProcessedCount());
    assertEquals("Total valid count", totalValid, DateProcessor.getInstance().getDatesValidCount());
    
    // Verify individual results
    for (DateProcessorThread thread : threads) {
      thread.verifyResults();
    }
  }

  private DateProcessorThread createAndStartThread(String input, boolean expectValid) {
    DateProcessorThread thread = new DateProcessorThread(input, expectValid);
    thread.start();
    return thread;
  }
  
  private static class DateProcessorThread extends Thread {

    private final String input;
    private final boolean expectValid;
    private AtomicReference<Date> resultRef = new AtomicReference<Date>();
    
    public DateProcessorThread(String input, boolean expectValid) {
      this.input = input;
      this.expectValid = expectValid;
    }

    @Override
    public void run() {
      Date date = DateProcessor.getInstance().processDate(input);
      resultRef.set(date);
    }
    
    public void verifyResults() {
      Date result = resultRef.get();
      if (expectValid) {
        assertNotNull(result);
      } else {
        assertNull(result);
      }
    }
    
  }
}
