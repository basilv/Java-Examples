// Copyright 2002 by Basil Vandegriend.  All rights reserved.

package com.basilv.examples.dom4j;

import java.awt.Point;

/**
 * Represents a wall on the maze.
 */
public class Wall
{

  private Point start;
  private Point end;

  public Wall(int startX, int startY, int endX, int endY) {
    start = new Point(startX, startY);
    end = new Point(endX, endY);
  }

  public Point getEnd() {
    return end;
  }

  public Point getStart() {
    return start;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Wall)) {
      return false;
    }

    Wall other = (Wall) obj;
    return this.start.equals(other.start) && this.end.equals(other.end);
  }

  @Override
  public int hashCode() {
    return this.start.hashCode() + 7 * this.end.hashCode();
  }

  @Override
  public String toString() {
    return "Wall from " + start.x + ", " + start.y + " to " + end.x + ", " + end.y;
  }

}