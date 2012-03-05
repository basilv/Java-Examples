// Copyright 2008 by Basil Vandegriend.  All rights reserved.

package com.basilv.examples.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME) 
@Target( {ElementType.TYPE, ElementType.METHOD }) 
public @interface MyAnnotation {
  int myProperty();
  String myPropertyWithDefault() default "";
}
