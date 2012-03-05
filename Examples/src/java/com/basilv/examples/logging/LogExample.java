// Copyright 2006 by Basil Vandegriend.  All rights reserved.

package com.basilv.examples.logging;

import org.apache.log4j.Logger;

public class LogExample
{
  private static Logger logger = Logger
    .getLogger(LogExample.class);

  void doWork() {
    logger.debug("Debugging message");
    try {
      logger.info("Info message");
    } catch (RuntimeException e) {
      logger.error(
        "Error message with exception included.", e);
      throw e;
    }
  }
}