/* Copyright 2000 by Basil Vandegriend.  All rights reserved. */

package com.basilv.examples.dom4j;

/**
 * Unit tests for a single class.
 */
public class MazeXmlConverterTest extends junit.framework.TestCase
{
  public MazeXmlConverterTest(String name) {
    super(name);
  }

  public void testEmpty() {
    Maze maze = new Maze(3, 5);
    verifyConvertToXmlAndBack(maze);
  }

  public void testWithSomeWalls() {
    Maze maze = new Maze(5, 4);
    maze.setDescription("A maze");
    maze.addWall(1, 0, 1, 1);
    maze.addWall(0, 2, 1, 2);
    maze.addWall(1, 3, 1, 4);
    maze.addWall(2, 1, 2, 3);
    maze.addWall(2, 1, 3, 1);
    maze.addWall(3, 3, 3, 4);
    maze.addWall(3, 3, 4, 3);
    maze.addWall(4, 2, 4, 4);
    maze.addWall(4, 1, 5, 1);

    verifyConvertToXmlAndBack(maze);
  }

  private void verifyConvertToXmlAndBack(Maze originalMaze) {
    MazeXmlConverter converter = new MazeXmlConverter();
    String xml = converter.toXml(originalMaze);
    assertNotNull(xml);

    Maze newMaze = converter.fromXml(xml);
    assertNotNull(newMaze);
    assertEquals(originalMaze.getSize(), newMaze.getSize());

    if (originalMaze.getDescription() == null) {
      assertEquals("", newMaze.getDescription());
    } else {
      assertEquals(originalMaze.getDescription(), newMaze.getDescription());
    }

    assertEquals(originalMaze.getWalls().size(), newMaze.getWalls().size());
    for (int i = 0; i < originalMaze.getWalls().size(); i++) {
      Wall originalWall = originalMaze.getWalls().get(i);
      Wall newWall = newMaze.getWalls().get(i);
      assertEquals(originalWall, newWall);
    }

  }

}
