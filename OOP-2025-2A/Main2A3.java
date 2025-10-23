class Main2A3 {
  static int a = 10;
  static String buf = null;

  public static int m1() {
    return a / 0;
  }

  public static int m2() {
    return buf.length();
  }

  public static int m3(int b) {
    int[] arr = new int[b];
    return 0;
  }

  public static void main(String[] args) {
    try {
      int select = Integer.parseInt(args[0]);
      switch(select) {
        case 1:
        System.out.println(m1());
        break;

        case 2:
        System.out.println(m2());
        break;

        default:
        System.out.println(m3(select));
      }
    } catch(ArithmeticException e) {
      System.err.println("From m1");
      e.printStackTrace();
    } catch(NullPointerException e) {
      System.err.println("From m2");
      e.printStackTrace();
    } catch(NegativeArraySizeException e) {
      System.err.println("From m3");
      e.printStackTrace();
    }
  }
}
