package oop.ex4B3;

public class LessThan extends BinaryOperator {
  @Override
  public void parse(Context con) {
    if(con.currentToken().equals("<")) {
      con.nextToken();
      parseOperands(con);
    }
    else {
      throw new RuntimeException("Not lessthan");
    }
  }

  @Override
  public void accept(Visitor v) {
    v.visit(this);
  }

  @Override
  public String toString() {
    return "< " + getLeftExpression() + " " + getRightExpression();
  }

  @Override
  public Value evaluate() {
    return new Bool(getLeftExpression().evaluate().getNumber() < getRightExpression().evaluate().getNumber());
  }

  @Override
  public int calcSteps() {
    return getLeftExpression().calcSteps() + getRightExpression().calcSteps() + 1;
  }
}