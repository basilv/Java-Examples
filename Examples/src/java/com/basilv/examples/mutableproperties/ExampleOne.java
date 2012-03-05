// Copyright 2008 by Basil Vandegriend.  All rights reserved.

package com.basilv.examples.mutableproperties;

import java.util.Calendar;

public class ExampleOne
{

  public static void exampleOne(Order order) {
    Calendar cal = order.getDate();

    // Is order past due?
    cal.add(Calendar.DAY_OF_YEAR, -10);
    if (cal.before(Calendar.getInstance())) {
      // Order past due logic...
    }
  }

  public static void main(String[] args) {
    Order order = new Order();
    exampleOne(order);
  }

}