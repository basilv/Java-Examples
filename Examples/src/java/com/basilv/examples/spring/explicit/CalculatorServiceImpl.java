// Copyright 2009 by Basil Vandegriend.  All rights reserved.

package com.basilv.examples.spring.explicit;

import com.basilv.examples.spring.*;

public class CalculatorServiceImpl implements CalculatorService
{
  private ResumeRepository resumeRepository;
  
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
