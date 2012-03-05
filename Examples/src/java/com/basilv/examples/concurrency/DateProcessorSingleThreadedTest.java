// Copyright 2009 by Basil Vandegriend.  All rights reserved.

package com.basilv.examples.concurrency;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.*;

public class DateProcessorSingleThreadedTest
{
  private DateProcessor dateProcessor;
  
  @Before
  public void setUp() {
    DateProcessor.setInstanceForTesting(null);
    dateProcessor = DateProcessor.getInstance();
  }
  
  @Test
  public void initialState() {
    assertEquals(0, dateProcessor.getDatesProcessedCount());
    assertEquals(0, dateProcessor.getDatesValidCount());
  }
  
  @Test
  public void parseValidDate() {
    Date date = dateProcessor.processDate("2009-11-25");
    assertNotNull(date);
    assertEquals(createDate(2009, 11, 25), date);
    assertEquals(1, dateProcessor.getDatesProcessedCount());
    assertEquals(1, dateProcessor.getDatesValidCount());
  }

  @Test
  public void parseInvalidDate() {
    assertNull(dateProcessor.processDate("foo"));
    assertEquals(1, dateProcessor.getDatesProcessedCount());
    assertEquals(0, dateProcessor.getDatesValidCount());
  }
  
  @SuppressWarnings("deprecation") // Due to stupid API on java.util.Date
  private Date createDate(int year, int monthIndexedFromOne, int dayOfMonth) {
    return new Date(year - 1900, monthIndexedFromOne - 1, dayOfMonth);
  }
}
