// Copyright 2009 by Basil Vandegriend.  All rights reserved.

package com.basilv.examples.spring.componentscan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basilv.examples.spring.*;

@Service
public class CalculatorServiceImpl implements CalculatorService
{
  private ResumeRepository resumeRepository;

  @Autowired
  public CalculatorServiceImpl(ResumeRepository resumeRepository) {
    super();
    this.resumeRepository = resumeRepository;
  }
  
  public boolean validate(Resume resume) {
      for (Resume repoResume : resumeRepository.findAll()) {
        if (repoResume.getName().equals(resume.getName())) {
          return true;
        }
      }
      
      return false;
  }

}
