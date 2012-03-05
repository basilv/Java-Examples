// Copyright 2009 by Basil Vandegriend.  All rights reserved.

package com.basilv.examples.spring;

import java.util.Set;

public interface ResumeRepository
{
  Set<Resume> findAll();
}
