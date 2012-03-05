// Copyright 2008 by Basil Vandegriend.  All rights reserved.

package com.basilv.examples.annotations;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.*;
import javax.servlet.http.*;

public class DispatchingServlet extends HttpServlet
{
  @Override 
  protected void doGet(HttpServletRequest request,
    HttpServletResponse response) throws ServletException,
    IOException {
    response.setContentType("text/html;charset=UTF-8");

    String url = request.getPathInfo();
    ActionExecutor executor = new ActionExecutor(request,
      response);

    boolean actionFound = false;
    for (Method method : ActionExecutor.class.getMethods()) {
      if (method.isAnnotationPresent(WebAction.class)) {
        WebAction action = method.getAnnotation(
          WebAction.class);
        if (url.equals(action.url())) {
          actionFound = true;
          try {
            method.invoke(executor, (Object[]) null);
          } catch (Exception e) {
            throw new RuntimeException(
              "Error invoking method [" + method.getName()
                + "].", e);
          }
        }
      }
    }
    if (!actionFound) {
      request.setAttribute("message", "No action found");
    }
    RequestDispatcher rd = request
      .getRequestDispatcher("/view.jsp");
    rd.forward(request, response);
  }

  @Override 
  protected void doPost(HttpServletRequest request,
    HttpServletResponse response) throws ServletException,
    IOException {
    doGet(request, response);
  }
}