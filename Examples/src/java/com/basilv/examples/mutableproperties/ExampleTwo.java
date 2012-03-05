// Copyright 2008 by Basil Vandegriend.  All rights reserved.

package com.basilv.examples.mutableproperties;

import java.text.DateFormat;
import java.util.*;

public class ExampleTwo
{

  public static void exampleTwo(Order order) {
    print("Original order date = "
      + convertToText(order.getDate()));
    Calendar cal = order.getDate();

    // Is order past due?
    cal.add(Calendar.DAY_OF_YEAR, -10);
    if (cal.before(Calendar.getInstance())) {
      // Order past due logic...
    }
    print("Ending order date = "
      + convertToText(order.getDate()));
  }

  private static void print(String message) {
    System.out.println(message);
  }

  private static String convertToText(Calendar calendar) {
    return DateFormat.getDateInstance().format(
      new Date(calendar.getTimeInMillis()));
  }

  public static void main(String[] args) {
    Order order = new Order();
    exampleTwo(order);
  }

}