package com.basilv.examples.dom4j;

import java.io.File;

import org.dom4j.*;
import org.dom4j.io.SAXReader;

public class MazeXmlConverter
{
  private static final String MAZE_ELEMENT = "maze";
  private static final String HEIGHT_ATTRIBUTE = "height";
  private static final String WIDTH_ATTRIBUTE = "width";
  private static final String WALL_ELEMENT = "wall";
  private static final String START_X_ATTRIBUTE = "startX";
  private static final String START_Y_ATTRIBUTE = "startY";
  private static final String END_X_ATTRIBUTE = "endX";
  private static final String END_Y_ATTRIBUTE = "endY";

  public String toXml(Maze maze) {
    Document document = DocumentHelper.createDocument();
    Element root = document.addElement(MAZE_ELEMENT)
      .addAttribute(WIDTH_ATTRIBUTE, Integer.toString(maze.getSize().width))
      .addAttribute(HEIGHT_ATTRIBUTE, Integer.toString(maze.getSize().height))
      .addText(nullToEmpty(maze.getDescription()));

    for (Wall wall : maze.getWalls()) {
      root.addElement(WALL_ELEMENT)
        .addAttribute(START_X_ATTRIBUTE, Integer.toString(wall.getStart().x))
        .addAttribute(START_Y_ATTRIBUTE, Integer.toString(wall.getStart().y))
        .addAttribute(END_X_ATTRIBUTE, Integer.toString(wall.getEnd().x))
        .addAttribute(END_Y_ATTRIBUTE, Integer.toString(wall.getEnd().y));
    }

    return document.asXML();
  }

  private String nullToEmpty(String text) {
    if (text == null) {
      return "";
    } else {
      return text;
    }
  }
  
  public Maze fromXml(File xmlFile) {
    SAXReader reader = new SAXReader();
    try {
      Document document = reader.read(xmlFile);
      return fromXml(document);
    } catch (DocumentException e) {
      throw new RuntimeException("Error reading file [" + xmlFile.getAbsolutePath() + "].", e);
    }
  }

  // For testing.
  public Maze fromXml(String xml) {
    try {
      Document document = DocumentHelper.parseText(xml);
      return fromXml(document);
    } catch (DocumentException e) {
      throw new RuntimeException("Error parsing xml string.", e);
    }
  }
  
  private Maze fromXml(Document document) {
    Element root = document.getRootElement();

    int width = getElementIntAttribute(root, WIDTH_ATTRIBUTE);
    int height = getElementIntAttribute(root, HEIGHT_ATTRIBUTE);

    Maze maze = new Maze(width, height);
    maze.setDescription(root.getText());

    for (Object elementObj : root.elements(WALL_ELEMENT)) {
      Element element = (Element) elementObj;

      int startX = getElementIntAttribute(element, START_X_ATTRIBUTE);
      int startY = getElementIntAttribute(element, START_Y_ATTRIBUTE);
      int endX = getElementIntAttribute(element, END_X_ATTRIBUTE);
      int endY = getElementIntAttribute(element, END_Y_ATTRIBUTE);

      maze.addWall(startX, startY, endX, endY);
    }

    return maze;
  }

  private int getElementIntAttribute(Element element, String attributeName) {
    Attribute attribute = getNonNullAttributeForElement(element, attributeName);
    return Integer.parseInt(attribute.getValue());
  }

  private Attribute getNonNullAttributeForElement(Element element, String attributeName) {
    Attribute attribute = element.attribute(attributeName);
    if (attribute == null) {
      throw new RuntimeException("Element [" + element.getName() + "] missing attribute named [" + attributeName + "].");
    }
    return attribute;
  }

}