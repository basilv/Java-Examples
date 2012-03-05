// Copyright 2006 by Basil Vandegriend.  All rights reserved.

package com.basilv.examples.enums;

import java.util.*;

import javax.swing.table.AbstractTableModel;

public class PersonTableModel extends AbstractTableModel
{

  /**
   * Represents a column of the table.
   */
  static enum Column {
    FIRST_NAME("First Name") {
      @Override public Object getValue(Person person) {
        return person.getFirstName();
      }

      @Override public int getWidthInCharacters() {
        return 10;
      }
    },

    LAST_NAME("Last Name") {
      @Override public Object getValue(Person person) {
        return person.getLastName();
      }

      @Override public int getWidthInCharacters() {
        return 20;
      }
    },

    AGE("Age") {
      @Override public Object getValue(Person person) {
        return person.getAge();
      }

      @SuppressWarnings("unchecked") @Override public Class getColumnClass() {
        return Integer.class;
      }

      @Override public int getWidthInCharacters() {
        return 5;
      }
    },

    BIRTHDAY("Birthday") {
      @Override public Object getValue(Person person) {
        return person.getBirthDay();
      }

      @SuppressWarnings("unchecked") @Override public Class getColumnClass() {
        return Date.class;
      }

      @Override public int getWidthInCharacters() {
        return 12;
      }
    };

    
    private String displayName;

    private Column(String displayName) {
      assert displayName != null
        && displayName.length() > 0;
      this.displayName = displayName;
    }

    public String getDisplayName() {
      return displayName;
    }

    /**
     * Return the value for this column for the specified
     * person.
     */
    public abstract Object getValue(Person person);

    /**
     * Return the class of Object returned by this column.
     */
    @SuppressWarnings("unchecked") public Class getColumnClass() {
      return String.class; // Default value
    }

    /**
     * Return the number of characters needed to display the
     * header and data for this column.
     */
    public abstract int getWidthInCharacters();
  }

  private List<Person> personList = new ArrayList<Person>();

  public void setPersonList(List<Person> list) {
    assert list != null;
    personList.clear();
    personList.addAll(list);
    fireTableDataChanged();
  }

  public int getColumnCount() {
    return Column.values().length;
  }

  public int getRowCount() {
    return personList.size();
  }

  public Object getValueAt(int rowIndex, int columnIndex) {
    Person person = getPerson(rowIndex);
    Column column = getColumn(columnIndex);
    return column.getValue(person);
  }

  @Override public String getColumnName(int columnIndex) {
    return getColumn(columnIndex).getDisplayName();
  }

  @Override public Class<?> getColumnClass(int columnIndex) {
    Column column = getColumn(columnIndex);
    return column.getColumnClass();
  }

  Person getPerson(int rowIndex) {
    return personList.get(rowIndex);
  }

  private Column getColumn(int columnIndex) {
    return Column.values()[columnIndex];
  }

}
