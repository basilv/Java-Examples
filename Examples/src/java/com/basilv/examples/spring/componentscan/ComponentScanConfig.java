// Copyright 2009 by Basil Vandegriend.  All rights reserved.

package com.basilv.examples.spring.componentscan;

import org.springframework.config.java.annotation.Configuration;
import org.springframework.config.java.plugin.context.*;

@Configuration
@AnnotationDrivenConfig // Needed for @Autowire to work
//Scan doesn't work due to runtime error under Spring 3.0.0-M2, but working after switched to Spring 2.5.6
@ComponentScan("com.basilv.examples.spring.componentscan") 
public class ComponentScanConfig
{
  // No explicit configuration needed
}
