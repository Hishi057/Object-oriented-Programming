import oop.ex2A2.*;
import oop.ex2A2.ex.Ex3;

class Main2A2 {
  public static void main(String[] args) {
    Ex1 ex1 = new Ex1();
    //System.out.println(ex1.field);
    // ex1.field should not be visible here
    System.out.println(ex1.method1());
    // System.out.println(ex1.method2());
    // ex1.method2 should not be visible here
    Ex2 ex2 = new Ex2();
    ex2.method3();
    Ex3 ex3 = new Ex3();
    System.out.println(ex3.method4());
  }
}