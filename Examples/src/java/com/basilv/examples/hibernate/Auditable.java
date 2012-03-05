package com.basilv.examples.hibernate;

import java.sql.Timestamp;

/**
 * Represents an object with the standard audit fields to
 * track creation and modification information.
 * 
 * @author basil.vandegriend
 * 
 */
public interface Auditable
{

  public Timestamp getCreateTimestamp();

  public void setCreateTimestamp(Timestamp createTimestamp);

  public String getCreateUserId();

  public void setCreateUserId(String createUserId);

  public Timestamp getUpdateTimestamp();

  public void setUpdateTimestamp(Timestamp updateTimestamp);

  public String getUpdateUserId();

  public void setUpdateUserId(String updateUserId);

}
