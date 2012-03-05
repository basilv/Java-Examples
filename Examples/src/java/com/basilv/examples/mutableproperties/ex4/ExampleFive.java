// Copyright 2008 by Basil Vandegriend.  All rights reserved.

package com.basilv.examples.mutableproperties.ex4;


public class ExampleFive
{
  public static void exampleFive(Order order) {

    print("Original order customer name = "
      + order.getCustomer().getName());
    
    String newName = "New name";
    Customer customer = order.getCustomer();
    customer.setName(newName);

    print("Final order customer name = "
      + order.getCustomer().getName());
  }

  private static void print(String message) {
    System.out.println(message);
  }

  public static void main(String[] args) {
    Customer customer = new Customer();
    customer.setName("Starting name");
    Order order = new Order();
    order.setCustomer(customer);
    exampleFive(order);
  }
}