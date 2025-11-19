package oop.ex4B3;

public class StepVisitor implements Visitor {
  private int step;

  public int calc(Expression e) {
    e.accept(this);
    return step;
  }

  public void visit(Number e) {
    step = 1; // 返し値の代わり
  }

  public void visit(Bool e) {
    step = 1;
  }

  public void visit(Addition e) {
    e.getLeftExpression().accept(this);
    int left = step;
    e.getRightExpression().accept(this);
    step += left + 1; // 左、右、1の和
  }

  public void visit(LessThan e) {
    e.getLeftExpression().accept(this);
    int left = step;
    e.getRightExpression().accept(this);
    step += left + 1;
  }

  public void visit(IfThenElse e) {
    // 条件式と、then節とelse節のステップ数の大きい方と1の和
    e.getConditional().accept(this);
    int cond = step;
    e.getThenPart().accept(this);
    int stepthen = step;
    e.getElsePart().accept(this);
    int stepelse = step;
    if(stepthen > stepelse) {
      step = cond + stepthen + 1;
    }
    else {
      step = cond + stepelse + 1;
    }
  }
}