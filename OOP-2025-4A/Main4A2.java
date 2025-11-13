import java.util.Scanner;

class Main4A2 {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);

    try {
      while (true) {
        String str = scan.nextLine();
        if (str.trim().isEmpty()) {
          break;
        } else {
          System.out.println("INPUT: " + str);
        }
      }
    } catch (Exception e) {
      System.out.println("エラー発生: " + e.getMessage());
    }

    scan.close();
  }
}
