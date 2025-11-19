package oop.ex4B3;

public class EvaluateVisitor implements Visitor {
  private Value v;

  @Override
  public void visit(Number e){
    v = new Number(e.getValue());
  }

  @Override
  public void visit(Bool e){
    v = new Bool(e.getValue());
  }

  @Override
  public void visit(Addition e){
    e.getLeftExpression().accept(this);
    Value left = v;
    e.getRightExpression().accept(this);
    Value right = v;
    v = new Number(left.getNumber() + right.getNumber());
  }

  @Override
  public void visit(LessThan e){
    e.getLeftExpression().accept(this);
    Value left = v;
    e.getRightExpression().accept(this);
    Value right = v;
    v = new Bool(left.getNumber() < right.getNumber());
  }

  @Override
  public void visit(IfThenElse e){
    e.getConditional().accept(this);
    Value cond = v;
    if(cond.getBool()){
      e.getThenPart().accept(this);
    }else {
      e.getElsePart().accept(this);
    }
  }


  public Value evaluate(Expression e) {
    e.accept(this);
    return v;
  }
}