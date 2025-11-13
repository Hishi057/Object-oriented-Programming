import oop.ex4A1.C1;
import oop.ex4A1.C2;

class Main4A1 {
  public static void main(String[] args) {
    C1[] cs = new C2[21];

    for(int i = -10; i <= 10; ++i) {
      if(i % 2 == 0) {
        cs[i + 10] = new C1(i);
      }
      else {
        cs[i + 10] = new C2(i);
      }
    }

    for(C1 c : cs) {
      System.out.println(c);
    }
  }
  // Exception in thread "main" java.lang.ArrayStoreException: oop.ex4A1.C1 at Main4A1.main(Main4A1.java:10)
}
