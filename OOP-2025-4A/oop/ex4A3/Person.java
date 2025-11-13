package oop.ex4A3;

public class Person {
  private final String sur;
  private final String given;

  public Person(String sur, String given) {
    this.sur = sur;
    this.given = given;
  }

  @Override public String toString() {
    return sur + " " + given;
  }

  @Override public boolean equals(Object o) {
    if(o instanceof Person) {
      return sur.equals(((Person)o).sur) && given.equals(((Person)o).given);
    }
    return false;
  }

  @Override public int hashCode() {
    return sur.hashCode() + given.hashCode();
  }
}
