package com.basilv.examples.hibernate;

import java.io.Serializable;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

/**
 * AuditInterceptor is used to populate the audit fields on onUpdate and onSave events.
 *
 */
public class AuditInterceptor extends EmptyInterceptor
{

  private static ThreadLocal<String> userPerThread = new ThreadLocal<String>();

  /**
   * Store the user for the current thread.
   * @param user Cannot be null or empty.
   */
  public static void setUserForCurrentThread(String user) {
    userPerThread.set(user);
  }

  /**
   * Get the user for the current thread.
   * (Used primarily for testing).
   * @return the current user.
   */
  public static String getUserForCurrentThread() {
    return userPerThread.get();
  }

  @Override public boolean onFlushDirty(Object entity,
    Serializable id, Object[] currentState,
    Object[] previousState, String[] propertyNames,
    Type[] types) {

    boolean changed = false;

    if (entity instanceof Auditable) {
      changed = updateAuditable(currentState, propertyNames);
    }
    return changed;
  }

  @Override public boolean onSave(Object entity,
    Serializable id, Object[] currentState,
    String[] propertyNames, Type[] types) {

    boolean changed = false;

    if (entity instanceof Auditable) {
      changed = updateAuditable(currentState, propertyNames);
    }
    return changed;

  }

  private boolean updateAuditable(Object[] currentState,
    String[] propertyNames) {
    boolean changed = false;
    for (int i = 0; i < propertyNames.length; i++) {
      if ("createUserId".equals(propertyNames[i])) {
        if (currentState[i] == null) {
          currentState[i] = userPerThread.get();
          changed = true;
        }
      }
      if ("updateUserId".equals(propertyNames[i])) {
        currentState[i] = userPerThread.get();
        changed = true;
      }
    }
    return changed;
  }

}