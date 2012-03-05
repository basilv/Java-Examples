package com.basilv.examples.java5;

import java.lang.reflect.Array;
import java.util.*;


class BusinessEntity {
  private Integer id;

  public BusinessEntity(Integer id) {
    this.id = id;
  }
  
  public Integer getId() {
    return id;
  }

  public int getIdAsPrimitiveInt() {
    return id; // Unboxing (Integer to int). What happens if id is null???
  }

  @Override // Annotation (Java standard) 
  public String toString() {
    return "Id:" + id;
  }
  
  public BusinessEntity getMyself() {
    return this;
  }
  
  
}

class PersonEntity extends BusinessEntity {

  // Enumerated Type (enum)
  // Can get list of values with AddressType.values();
  // Can get particular instance with AddressType.valueOf("Mailing");
  // Can even have abstract methods that particular enumerated value constant overrides.
  public enum AddressType {
    MAILING("Mailing", 1),
    BILLING("Billing", 2);
    
    private String displayName;
    
    // Annotation (warning type Eclipse-specific)
    @SuppressWarnings("unused")  
    private int id;
    
    private AddressType(String displayName, int id) {
        this.displayName = displayName;
        this.id = id;
    }

    public String getDisplayName() {
      return displayName;
    }

  }
  
  public PersonEntity(Integer id) {
    super(id);
  }

  private AddressType addressType;

  public AddressType getAddressType() {
    return addressType;
  }
  public void setAddressType(AddressType addressType) {
    this.addressType = addressType;
  }

  @Override 
  // Covariant return - note return type of method in parent class.
  public PersonEntity getMyself() { 
    return this;
  }

}

// Generic type definition.
// This can get more complex with the use of wildcards.
abstract class BusinessService<T extends BusinessEntity> {
  public abstract List<T> findAll();
  
  public abstract T loadByPrimaryKey(Long id);
  
  
  // Static generic method
  public static <T> T[] toArray(List<T> list, Class<T> clazz) {
    // This construct is not allowed because the specific type of the generic
    // is not kept at runtime - this is called erasure.
    // T[] array = new T[]();

    // Instead, pass in the class as a 'Type Token'
    // Note that the class Class is itself genericized.
    
    @SuppressWarnings("unchecked") // Annotation - needed for cast. 
    T[] array = (T[]) Array.newInstance(clazz, 0);
    return list.toArray(array);
  }
}

// Instantiation of a generic type
public class PersonServiceImpl extends BusinessService<PersonEntity> {

  @Override
  @Deprecated // Annotation (Java standard)
  public List<PersonEntity> findAll() {
    // Using a list from the collections API
    List<PersonEntity> list = new ArrayList<PersonEntity>();
    list.add(new PersonEntity(123)); // Autoboxing (int to Integer)
    return list;
  }
  
  @Override public PersonEntity loadByPrimaryKey(Long id) {
    List<PersonEntity> personList = findAll();
    for (PersonEntity person : personList) { // Enhanced for loop over list.
      if (person.getId().equals(id)) {
        return person;
      }
    }
    
    PersonEntity[] backupArray = {
      new PersonEntity(1),
      new PersonEntity(2),
    };
    
    for (PersonEntity person : backupArray) { // Enhanced for loop over array
      if (person.getId().equals(id)) {
        return person;
      }
    }
    
    return null;
  }
  
  // Use of generic wildcard.
  // Use of Varargs (variable arguments)
  public Map<Integer,? extends PersonEntity> collectIntoMap(PersonEntity... people) {
    // Using a map from the collections API
    Map<Integer, PersonEntity> map = new HashMap<Integer,PersonEntity>();
    for (PersonEntity person : people) {
      map.put(person.getId(), person);
    }
    return map;
  }
  
}

// Other notes:
// - You can define your own annotations. Used mostly by frameworks (i.e. EJB 3)