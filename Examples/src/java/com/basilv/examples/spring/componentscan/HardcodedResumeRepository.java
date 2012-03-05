// Copyright 2009 by Basil Vandegriend.  All rights reserved.

package com.basilv.examples.spring.componentscan;

import java.util.*;

import org.springframework.stereotype.Repository;

import com.basilv.examples.spring.*;

@Repository
public class HardcodedResumeRepository implements ResumeRepository
{
  public Set<Resume> findAll() {
    HashSet<Resume> set = new HashSet<Resume>();
    Resume resume = new Resume();
    resume.setName("Coded, Hard");
    set.add(resume);
    
    return set;
  }
}
