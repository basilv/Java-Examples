/* Copyright 2000 by Basil Vandegriend.  All rights reserved. */

package com.basilv.examples.spring.explicit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.*;
import org.springframework.config.java.context.JavaConfigApplicationContext;

import com.basilv.examples.spring.*;

public class ExplicitConfigTest
{
  private JavaConfigApplicationContext context;

  @Before
  public void setUp() {
    context = new JavaConfigApplicationContext(ExplicitConfig.class);
  }

  @Test
  public void repositoryConfig() {

    ResumeRepository resumeRepository = context.getBean(ResumeRepository.class);
    Set<Resume> resumes = resumeRepository.findAll();
    assertNotNull(resumes);
    assertTrue(!resumes.isEmpty());
  }

  @Test
  public void serviceConfig() {

    CalculatorService service = context.getBean(CalculatorService.class);
    assertNotNull(service);

    assertFalse(service.validate(new Resume()));
  }

}
