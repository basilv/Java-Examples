// Copyright 2006 by Basil Vandegriend.  All rights reserved.

package com.basilv.examples.enums;

public class TerrainUsage
{
  public static Terrain getFromString(String terrainId) {
    return Terrain.valueOf(terrainId);
  }

  public static void main(String[] args) {

    // Iterate through all terrain values
    for (Terrain terrain : Terrain.values()) {
      System.out
        .println("Terrain: '" + terrain.getDisplayName() + "'"
          + (terrain.blocksLineOfSight() ? " blocks line of sight"
            : "")
          + (terrain.preventsMovement() ? " prevents movement"
            : "") 
          + " Ordinal: " + terrain.ordinal() 
          + " Identifier: " + terrain.name()
          + ".");
    }

    // Iterate through all terrain values
    for (Terrain terrain : Terrain.values()) {
      System.out.println(
          "Terrain ordinal=" + terrain.ordinal() 
          + " Identifier=" + terrain.name());
    }

    // Given identifier, find corresponding terrain instance.
    String terrainIdentifier = "WALL";
    Terrain terrain = Terrain.valueOf(terrainIdentifier);
    System.out.println("\nTerrain identifier '"
      + terrainIdentifier + "' produces terrain "
      + terrain.getDisplayName());
  }
}
