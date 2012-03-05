// Copyright 2009 by Basil Vandegriend.  All rights reserved.

package com.basilv.examples.concurrency;

import java.text.*;
import java.util.Date;

/**
 * Parses dates and tracks the results.
 * This class deliberately contains a number of concurrency defects.
 */
public class DateProcessor
{
  private static DateProcessor instance;
  
  public static DateProcessor getInstance() {
    if (instance == null) {
      synchronized(DateProcessor.class) {
        if (instance == null) {
          instance = new DateProcessor();
        }
      }
    }
    return instance;
  }

  static void setInstanceForTesting(DateProcessor newInstance) {
    instance = newInstance;
  }
  
  private static SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
  
  private long datesProcessedCount = 0;
  private long datesValidCount = 0;
  
  /**
   * Try to parse the supplied input into a date, 
   * returning null if the input is not a valid date in format yyyy-MM-dd
   */
  public Date processDate(String input) {
    datesProcessedCount++;
    try {
      Date date = dateFormatter.parse(input);
      datesValidCount++;
      return date;
    } catch (ParseException e) {
      return null;
    }
  }

  public long getDatesProcessedCount() {
    return datesProcessedCount;
  }

  public long getDatesValidCount() {
    return datesValidCount;
  }
  
}
