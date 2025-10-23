package oop.ex2A2.ex;

import oop.ex2A2.Ex1;

public class Ex3 extends Ex1 {
  public int method4() {
    //System.out.println(method2());
    // method2 must NOT be visible here
    return this.field;
  }
}