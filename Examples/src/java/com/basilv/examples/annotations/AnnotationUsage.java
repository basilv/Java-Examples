// Copyright 2008 by Basil Vandegriend.  All rights reserved.

package com.basilv.examples.annotations;

import java.util.ArrayList;

@SuppressWarnings("unchecked") 
public class AnnotationUsage
{
  public void doStuff() {
    ArrayList list = new ArrayList();
    list.add("");
  }

  @Override 
  public String toString() {
    return "AnnotationUsage";
  }
}
