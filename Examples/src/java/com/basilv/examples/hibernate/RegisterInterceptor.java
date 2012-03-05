// Copyright 2008 by Basil Vandegriend.  All rights reserved.

package com.basilv.examples.hibernate;

import org.hibernate.*;

public class RegisterInterceptor
{
  @SuppressWarnings("null")
  void register() {
    SessionFactory sessionFactory = null;
    Session session = sessionFactory.openSession(new AuditInterceptor());
    session.close();
  }
  
}
