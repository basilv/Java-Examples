// Copyright 2008 by Basil Vandegriend.  All rights reserved.

package com.basilv.examples.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)// or source or compile
@Target( { ElementType.TYPE, ElementType.METHOD }) 
public @interface WebAction {
  String url();
}