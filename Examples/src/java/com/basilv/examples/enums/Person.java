// Copyright 2006 by Basil Vandegriend.  All rights reserved.

package com.basilv.examples.enums;

import java.util.Date;

public class Person
{
  private Integer id; // Will be null for new records.

  private String firstName;
  private String lastName;
  private Integer age;
  private Date birthDay;

  public Person(String firstName, String lastName,
    Integer age, Date birthDate) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    this.birthDay = birthDate;
  }

  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }

  public String getLastName() {
    return lastName;
  }
  public void setLastName(String s) {
    this.lastName = s;
  }

  public Date getBirthDay() {
    return birthDay;
  }
  public void setBirthDay(Date date) {
    this.birthDay = date;
  }

  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String s) {
    this.firstName = s;
  }

  public Integer getAge() {
    return age;
  }
  public void setAge(Integer integer) {
    this.age = integer;
  }

}
