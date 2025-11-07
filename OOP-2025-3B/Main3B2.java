public class Main3B2 {
  public static String buildString() {
    // fill here (without StringBuilder)
    String s = "";
    for (int i = 0; i < 10000; i++) {
      s += "a";
    }
    return s;
  }

  public static String buildStringByBuilder() {
    // fill here (with StringBuilder)
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 10000; i++) {
      sb.append("a");
    }

    return sb.toString();
  }

  public static String measureString() {
    System.out.println("String append");
    long start = System.nanoTime();
    String s = buildString();
    long end = System.nanoTime();
    System.out.println("Process time (us) : " + ((end - start) / 1000));
    return s;
  }

  public static String measureStringBuilder() {
    System.out.println("StringBuilder append");
    long start = System.nanoTime();
    String s = buildStringByBuilder();
    long end = System.nanoTime();
    System.out.println("Process time (us) : " + ((end - start) / 1000));
    return s;
  }

  public static void main(String[] args) {
    String s1 = measureStringBuilder();
    String s2 = measureString();
    System.out.println("Identity check : " + s1.equals(s2));
  }
}
