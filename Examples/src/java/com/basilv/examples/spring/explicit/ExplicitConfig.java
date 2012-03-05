// Copyright 2009 by Basil Vandegriend.  All rights reserved.

package com.basilv.examples.spring.explicit;

import org.springframework.config.java.annotation.*;

import com.basilv.examples.spring.*;

@Configuration
public class ExplicitConfig
{
  @Bean
  public ResumeRepository resumeRepository() {
    return new HardcodedResumeRepository();
  }

  @Bean
  public CalculatorService calculatorService() {
    return new CalculatorServiceImpl(
      resumeRepository()); // Inject dependency
  }
}
