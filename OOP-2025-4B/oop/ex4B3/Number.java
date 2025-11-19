package oop.ex4B3;

public class Number extends Value {
  private int val;

  public Number() {
    val = 0;
  }

  public Number(int i) {
    val = i;
  }

  public int getValue() {
    return val;
  }

  @Override
  public int getNumber() {
    return val;
  }

  @Override
  public void parse(Context con) {
    val = Integer.valueOf(con.nextToken());
  }

  @Override
  public void accept(Visitor v) {
    v.visit(this);
  }

  @Override
  public String toString() {
    return Integer.toString(val);
  }

  @Override
  public Value evaluate() {
    return this;
  }

  @Override
  public int calcSteps(){
    return 1;
  }
}
