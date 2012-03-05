// Copyright 2008 by Basil Vandegriend.  All rights reserved.

package com.basilv.examples.mutableproperties;

import java.util.Calendar;

public class Order
{
  private Calendar date = Calendar.getInstance();

  public Calendar getDate() {
    return date;
  }

  public void setDate(Calendar calendar) {
    this.date = calendar;
  }
}
