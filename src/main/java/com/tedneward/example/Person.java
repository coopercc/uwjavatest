package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable<Person>{
  private int age;
  private String name;
  private double salary;
  private String ssn;
  private boolean propertyChangeFired = false;
  private int count = 0;
  
  public Person() {
    this("", 0, 0.0d);
  }
  
  public Person(String n, int a, double s) {
    name = n;
    age = a;
    salary = s;
    count++;
  }

  public void setAge(int a) {
    if (a < 0) {
      throw new IllegalArgumentException();
    } else {
      age = a;
    }
  }

  public int getAge() {
    return age;
  }

  public void setName(String s) {
    if (s == null) {
      throw new IllegalArgumentException();
    } else {
      name = s;
    }
  }

  public String getName() {
    return name;
  }

  public void setSalary(double s) {
    this.salary = s;
  }

  public double getSalary() {
    return salary;
  }

  public int count() {
    return count;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null) {
        return false;
    }
    if (getClass() != o.getClass()) {
      return false;
    }
    Person other = (Person)o;
    if (!this.name.equals(other.getName())) {
        return false;
    }
    if (this.age != other.getAge()) {
        return false;
    }
    return true;
  }

  //return negative if this is less than other
  //double - int
  public int compareTo(Person other) {
    double ret = other.getSalary() - this.salary;
    if (ret > 0) {
      return 1;
    } else if (ret < 0) {
      return -1;
    } else {
      return 0;
    }
  }
  
  public static ArrayList<Person> getNewardFamily() {
    ArrayList<Person> ret = new ArrayList<Person>();
    ret.add(new Person("Ted", 41, 250000));
    ret.add(new Person("Charlotte", 43, 150000));
    ret.add(new Person("Michael", 22, 10000));
    ret.add(new Person("Matthew", 15, 0));
    return ret;
  }

  public void setSSN(String value) {
    String old = ssn;
    ssn = value;
    
    this.pcs.firePropertyChange("ssn", old, value);
    propertyChangeFired = true;
  }
  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }

  public double calculateBonus() {
    return salary * 1.10;
  }
  
  public String becomeJudge() {
    return "The Honorable " + name;
  }
  
  public int timeWarp() {
    return age + 10;
  }
  
  //[Person name:Fird Birfle age:20 salary:195750.22]
  public String toString() {
    return "[Person name:" + name + " age:" + age + " salary:" + salary + "]";
  }

  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }

  public static class AgeComparator implements Comparator<Person> {

    public int compare(Person a, Person b) {
      return a.getAge() - b.getAge();
    }
    
  }
}