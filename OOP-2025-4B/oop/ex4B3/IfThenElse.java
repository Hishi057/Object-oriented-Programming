package oop.ex4B3;

public class IfThenElse extends Expression {
  private Expression cond, thenexp, elseexp;

  public Expression getConditional() {
    return cond;
  }

  public Expression getThenPart() {
    return thenexp;
  }

  public Expression getElsePart() {
    return elseexp;
  }

  @Override
  public void parse(Context con) {
    if(!con.nextToken().equals("if")) throw new RuntimeException("Not if-then-else");
    cond = Expression.getExpression(con);
    if(!con.nextToken().equals("then")) throw new RuntimeException("Not if-then-else");
    thenexp = Expression.getExpression(con);
    if(!con.nextToken().equals("else")) throw new RuntimeException("Not if-then-else");
    elseexp = Expression.getExpression(con);
  }

  @Override
  public void accept(Visitor v) {
    v.visit(this);
  }

  @Override
  public String toString() {
    return "if " + cond + " then " + thenexp + " else " + elseexp;
  }

  @Override
  public Value evaluate() {
    if(cond.evaluate().getBool()) {
      return thenexp.evaluate();
    }
    return elseexp.evaluate();
  }

  @Override
  public int calcSteps(){
    return cond.calcSteps() + thenexp.calcSteps() + elseexp.calcSteps() + 1;
  }
}