// Copyright 2008 by Basil Vandegriend.  All rights reserved.

package com.basilv.examples.jaxb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.*;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.*;

import org.junit.Test;

public class XmlBindingToolsTest
{
  @XmlRootElement(name = "xmltest")
  static class XmlTest
  {
    private String id;

    @XmlAttribute
    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

  }

  @Test
  public void parseXml() throws Exception {

    String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
      + "<xmltest id=\"test\"/>";

    StringReader reader = new StringReader(xml);
    XmlTest xmlTest = XmlBindingTools.parseXML(reader,
      XmlTest.class);
    assertNotNull(xmlTest);
    assertEquals("test", xmlTest.getId());
  }

  @Test
  public void generateXml() throws Exception {
    XmlTest xmlTest = new XmlTest();
    xmlTest.setId("test");
    StringWriter writer = new StringWriter();
    XmlBindingTools.generateXML(xmlTest, writer);
    assertEquals(
      "<?xml version=\"1.0\" encoding=\"UTF-8\" "
        + "standalone=\"yes\"?>\n<xmltest id=\"test\"/>\n",
      writer.getBuffer().toString());
  }

  @Test
  public void parseInvalidXml() throws Exception {

    String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
      + "<xmltest id=\"test\"><fake/></xmltest>";

    StringReader reader = new StringReader(xml);
    try {
      XmlBindingTools.parseXML(reader, XmlTest.class);
      fail("Expected Exception");
    } catch (JAXBException e) {
      // Expected case.
    }
  }

}
