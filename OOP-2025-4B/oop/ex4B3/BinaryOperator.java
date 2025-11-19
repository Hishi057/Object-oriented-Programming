package oop.ex4B3;

abstract public class BinaryOperator extends Expression {
  private Expression left, right;

  protected void parseOperands(Context con) {
    left = Expression.getExpression(con);
    right = Expression.getExpression(con);
  }

  public Expression getLeftExpression() {
    return left;
  }

  public Expression getRightExpression() {
    return right;
  }
}
