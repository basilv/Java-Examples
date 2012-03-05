// Copyright 2008 by Basil Vandegriend.  All rights reserved.

package com.basilv.examples.mutableproperties.ex4;

import java.util.*;

public class Order
{
  private Customer customer;

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

}


class Customer
{
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  private List<Order> orders = new ArrayList<Order>();

  public List<Order> getOrders() {
    return orders;
  }

  public void addOrder(Order order) {
    if (order == null) {
      return;
    }
    orders.add(order);
    order.setCustomer(this);
  }
}
