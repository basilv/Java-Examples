// Copyright 2002 by Basil Vandegriend. All rights reserved.

package com.basilv.examples.enums;

/**
 * Example of an enum with abstract methods and properties.
 */
public enum Terrain {

  NONE(""),

  WALL("Wall") {
    @Override public boolean preventsMovement() {
      return true;
    }

    @Override public boolean blocksLineOfSight() {
      return true;
    }
  },

  PIT("Pit") {
    @Override public boolean preventsMovement() {
      return true;
    }
  },

  GLASS_WALL("Glass Wall") {
    @Override public boolean preventsMovement() {
      return WALL.preventsMovement();
    }
  },
  
  FOG("Fog") {
    @Override public boolean blocksLineOfSight() {
      return true;
    }
  };

  private String displayName;

  private Terrain(String displayName) {
    this.displayName = displayName;
  }

  public String getDisplayName() {
    return displayName;
  }

  public boolean preventsMovement() {
    return false;
  }

  public boolean blocksLineOfSight() {
    return false;
  }

}