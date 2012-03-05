// Copyright 2009 by Basil Vandegriend.  All rights reserved.

package com.basilv.examples.spring.autowire;

import org.springframework.beans.factory.annotation.Autowired;

import com.basilv.examples.spring.*;

public class CalculatorServiceImpl implements CalculatorService
{
  @Autowired
  private ResumeRepository resumeRepository;
  

  public boolean validate(Resume resume) {
      for (Resume repoResume : resumeRepository.findAll()) {
        if (repoResume.getName().equals(resume.getName())) {
          return true;
        }
      }
      
      return false;
  }

  public void setResumeRepository(ResumeRepository resumeRepository) {
    this.resumeRepository = resumeRepository;
  }
}
