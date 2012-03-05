// Copyright 2008 by Basil Vandegriend.  All rights reserved.

package com.basilv.examples.mutableproperties;

import java.util.Calendar;

public class OrderUseNewObject
{
  private Calendar date = Calendar.getInstance();

  public Calendar getDate() {
    return (Calendar) date.clone();
  }

  public void setDate(Calendar calendar) {
    this.date = (Calendar) calendar.clone();
  }
}
