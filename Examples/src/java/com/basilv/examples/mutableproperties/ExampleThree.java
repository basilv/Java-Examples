// Copyright 2008 by Basil Vandegriend.  All rights reserved.

package com.basilv.examples.mutableproperties;

import java.text.DateFormat;
import java.util.*;

public class ExampleThree
{
  public static void exampleThree() {
    Calendar calendar = Calendar.getInstance();
    Order firstOrder = new Order();
    Order secondOrder = new Order();
    firstOrder.setDate(calendar);

    calendar.add(Calendar.DAY_OF_YEAR, 10);
    secondOrder.setDate(calendar);

    print("First date = "
      + convertToText(firstOrder.getDate()));
    print("Second date = "
      + convertToText(secondOrder.getDate()));
  }

  private static void print(String message) {
    System.out.println(message);
  }

  private static String convertToText(Calendar calendar) {
    return DateFormat.getDateInstance().format(
      new Date(calendar.getTimeInMillis()));
  }

  public static void main(String[] args) {
    exampleThree();
  }
}