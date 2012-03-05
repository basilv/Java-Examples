// Copyright 2002 by Basil Vandegriend.  All rights reserved.

package com.basilv.examples.dom4j;

import java.awt.Dimension;
import java.util.*;

/**
 * The maze is defined to be a certain # of squares wide and high. The
 * coordinate system for squares is that the top left hand corner is (0,0).
 * The maze can contain walls, which follow the grid.
 */
public class Maze
{
  private Dimension size;

  private List<Wall> walls = new ArrayList<Wall>();

  private String description;

  public Maze(int width, int height) {
    size = new Dimension(width, height);
  }

  public Dimension getSize() {
    return size;
  }

  public void addWall(int startX, int startY, int endX, int endY) {
    walls.add(new Wall(startX, startY, endX, endY));
  }

  public List<Wall> getWalls() {
    return walls;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
