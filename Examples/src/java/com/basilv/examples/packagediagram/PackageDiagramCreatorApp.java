package com.basilv.examples.packagediagram;

import java.io.*;
import java.util.*;

import jdepend.framework.*;

public class PackageDiagramCreatorApp
{

  private static void generateImage(String fileName,
    String graphVizDotFormattedGraph) throws IOException,
    InterruptedException {
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

  private static String getGraphVizNodeForPackage(
    JavaPackage javaPackage) {

    String rootPackage = "com.basilv.examples.packagediagram";
    String packageName = javaPackage.getName();
    if (!packageName.startsWith(rootPackage)) {
      return null;
    }

    return packageName.replace(".", "_");
  }

  @SuppressWarnings("unchecked")
  public static void createPackageDependencyDiagram()
    throws Exception {

    JDepend jdepend = new JDepend();
    jdepend.addDirectory("dist/classes");
    Collection<JavaPackage> packages = jdepend.analyze();

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

    generateImage("packages", builder.toString());
  }

  public static void main(String[] args) throws Exception {
    createPackageDependencyDiagram();
    System.exit(0);
  }
}
