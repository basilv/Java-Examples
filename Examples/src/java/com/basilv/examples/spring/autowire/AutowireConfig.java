// Copyright 2009 by Basil Vandegriend.  All rights reserved.

package com.basilv.examples.spring.autowire;

import org.springframework.config.java.annotation.*;
import org.springframework.config.java.plugin.context.AnnotationDrivenConfig;

import com.basilv.examples.spring.*;

@Configuration
@AnnotationDrivenConfig // Needed for @Autowire to work
public class AutowireConfig
{

  @Bean
  public ResumeRepository resumeRepository() {
    return new HardcodedResumeRepository();
  }

  @Bean
  public CalculatorService calculatorService() {
    return new CalculatorServiceImpl(); // Do not need to specify dependency.
  }
  
}
