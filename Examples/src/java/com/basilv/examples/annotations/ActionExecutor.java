// Copyright 2008 by Basil Vandegriend.  All rights reserved.

package com.basilv.examples.annotations;

import javax.servlet.http.*;

public class ActionExecutor
{
  private HttpServletRequest request;
  @SuppressWarnings("unused") 
  private HttpServletResponse response;

  public ActionExecutor(HttpServletRequest request,
    HttpServletResponse response) {
    this.request = request;
    this.response = response;
  }

  private void setMessage(String message) {
    request.setAttribute("message", message);
  }

  @WebAction(url = "/test") 
  public void doTest() {
    // Set a message for demo purposes. Normally execute some logic.
    setMessage("test");
  }

  @WebAction(url = "/submit") 
  public void submitWork() {
    // Set a message for demo purposes. Normally execute some logic.
    setMessage("submit");
  }
}
