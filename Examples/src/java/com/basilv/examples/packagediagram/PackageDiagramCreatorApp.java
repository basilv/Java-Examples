package com.basilv.examples.packagediagram;

import java.io.*;
import java.util.*;

import jdepend.framework.*;

public class PackageDiagramCreatorApp {

  public static void main(String[] args) {
    createPackageDependencyDiagram();
    System.exit(0);
  }
  
  public static void createPackageDependencyDiagram() {
    Collection<JavaPackage> packages = analyzePackages();
    StringBuilder builder = generateGraph(packages);
    generateImage("packages", builder.toString());
  }

  @SuppressWarnings("unchecked")
  private static Collection<JavaPackage> analyzePackages() {
    JDepend jdepend = new JDepend();
    try {
      jdepend.addDirectory("dist/classes");
    } catch (IOException e) {
      throw new RuntimeException("Error adding directory for JDepend to analyze.", e);
    }
    Collection<JavaPackage> packages = jdepend.analyze();
    return packages;
  }

  private static StringBuilder generateGraph(
    Collection<JavaPackage> packages) {
    StringBuilder builder = new StringBuilder();
    builder.append("digraph packages {").append("\n");
    builder.append("node [shape=box];").append("\n");
    builder.append("rankdir=BT;").append("\n");
    Set<String> drawnDependencies = new HashSet<String>();
    for (JavaPackage javaPackage : packages) {
      String packageNodeName = getGraphVizNodeForPackage(javaPackage);
      if (packageNodeName == null) {
        continue;
      }
      builder.append(packageNodeName).append("\n");

      @SuppressWarnings("unchecked")
      Collection<JavaPackage> dependencies = javaPackage.getEfferents();
      
      for (JavaPackage dependency : dependencies) {
        String dependencyNodeName = getGraphVizNodeForPackage(dependency);
        if (dependencyNodeName == null
          || packageNodeName.equals(dependencyNodeName)) {
          continue;
        }
        String dependencyKey = packageNodeName + "->"
          + dependencyNodeName;
        if (drawnDependencies.contains(dependencyKey)) {
          continue;
        }
        builder.append(packageNodeName).append(" -> ").append(
          dependencyNodeName).append(" [weight=4]").append("\n");
        drawnDependencies.add(dependencyKey);
      }
    }
    builder.append("}\n");
    return builder;
  }

  private static String getGraphVizNodeForPackage(
    JavaPackage javaPackage) {

    String rootPackage = "com.basilv.examples.packagediagram";
    String packageName = javaPackage.getName();
    if (!packageName.startsWith(rootPackage)) {
      return null;
    }

    return packageName.replace(".", "_");
  }

  private static void generateImage(String fileName,
    String graphVizDotFormattedGraph) {
    try {
      File graphFile = createFileWithContents(fileName
        + ".txt", graphVizDotFormattedGraph);

      // This requires the GraphViz software to be installed -
      // see http://graphviz.org/
      String imageFileLocation = fileName + ".png";
      Runtime.getRuntime().exec(
        "dot -v -Tpng " + graphFile.getName() + " -o "
          + imageFileLocation);

      System.out.println("Image file available at "
        + new File(imageFileLocation).getAbsolutePath());
    } catch (IOException e) {
      throw new RuntimeException("Error generating image " + fileName, e);
    }
  }

  private static File createFileWithContents(
    String fileName, String graphVizDotFormattedGraph)
    throws IOException {
    File graphFile = new File(fileName);
    FileWriter writer = new FileWriter(graphFile, false);
    try {
      writer.append(graphVizDotFormattedGraph);
    } finally {
      writer.close();
    }
    return graphFile;
  }

}


