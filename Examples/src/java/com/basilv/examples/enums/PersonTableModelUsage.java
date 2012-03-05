// Copyright 2006 by Basil Vandegriend.  All rights reserved.

package com.basilv.examples.enums;

import java.awt.Dimension;
import java.util.*;

import javax.swing.*;
import javax.swing.table.*;


public class PersonTableModelUsage
{

  public static void main(String[] args) {
    
    List<Person> list = new ArrayList<Person>();
    list.add(new Person("Dave", "Davidson", 
      60, createDate(1946, 03, 13)));
    list.add(new Person("Bob", "Smith", 
      40, createDate(1966, 04, 30)));
    list.add(new Person("John", "Jones", 
      20, createDate(1986, 01, 15)));
    list.add(new Person("Jimmy", "Xi", 
      30, createDate(1976, 05, 22)));
    
    PersonTableModel tableModel = new PersonTableModel();
    tableModel.setPersonList(list);
    
    JTable table = new JTable(tableModel);

    // Adjust column widths
    int widthOfTypicalCharacter = 
      table.getFontMetrics(table.getFont()).charWidth('x');
    
    TableColumnModel columnModel = table.getColumnModel();
    for (int i = 0; i < columnModel.getColumnCount(); i++) {
      TableColumn tableColumn = columnModel.getColumn(i);
      PersonTableModel.Column column = PersonTableModel.Column
        .values()[i];
      tableColumn.setPreferredWidth(widthOfTypicalCharacter
        * column.getWidthInCharacters());
    }

    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setPreferredSize(new Dimension(300, 100));
    
    JOptionPane.showMessageDialog(null, scrollPane, 
      "Person Table", JOptionPane.INFORMATION_MESSAGE);
    
    System.exit(0);
  }
  
  private static Date createDate(
    int year, int month, int dayOfMonth) {
    Calendar calendar = Calendar.getInstance();
    calendar.set(year, month - 1, dayOfMonth, 0, 0);
    return calendar.getTime();
  }
  
}
