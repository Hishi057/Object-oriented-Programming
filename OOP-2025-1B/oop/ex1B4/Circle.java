package oop.ex1B4;

public class Circle extends Figure {
  private final double diameter;
  public Circle(double diameter) {
    this.diameter = diameter;
  }

  public double getArea() {
    double rad = diameter / 2.0;
    return rad * rad * Math.PI;
  }
}
