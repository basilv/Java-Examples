// Copyright 2008 by Basil Vandegriend.  All rights reserved.

package com.basilv.examples.mutableproperties;

import java.util.Calendar;

public class OrderUseImmutable
{
  private Calendar date = Calendar.getInstance();

  public long getDate() {
    return date.getTimeInMillis();
  }

  public void setDate(long timeInMillis) {
    date.setTimeInMillis(timeInMillis);
  }
}
