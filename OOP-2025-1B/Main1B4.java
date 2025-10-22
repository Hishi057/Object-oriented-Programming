import oop.ex1B4.*;

public class Main1B4 {
  public static void main(String[] args) {
    Figure c1 = new Circle(4.0);
    Figure c2 = new Circle(3.0);

    System.out.println(c1.getArea());
    System.out.println(c2.getArea());

    System.out.println(c1.getPerimeter());
    System.out.println(c2.getPerimeter());

    Figure r1 = new Rectangle(2.0, 3.0);
    Figure r2 = new Rectangle(12.0, 0.5);
    Figure r3 = new Rectangle(3.1416, 1.5708);

    System.out.println(r1.getArea());
    System.out.println(r2.getArea());
    System.out.println(r3.getArea());

    System.out.println(r1.getPerimeter());
    System.out.println(r2.getPerimeter());
    System.out.println(r3.getPerimeter());
  }
}