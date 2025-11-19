package oop.ex4B3;

public interface Visitor {
  // 式の各種要素に対応するメソッド
  public void visit(Number e);
  public void visit(Bool e);
  public void visit(Addition e);
  public void visit(LessThan e);
  public void visit(IfThenElse e);
}
