package oop.ex4B3;

abstract public class Expression implements Element{
  abstract public void parse(Context con);
  abstract public String toString();
  abstract public Value evaluate();
  abstract public int calcSteps();

  public static Expression getExpression(Context con) {
    String cur = con.currentToken();
    try {
      Integer.valueOf(cur);
      Number n = new Number();
      n.parse(con);
      return n;
    } catch (NumberFormatException e) { }
    if(cur.equals("true") || cur.equals("false")) {
      Bool b = new Bool();
      b.parse(con);
      return b;
    }
    if(cur.equals("if")) {
      IfThenElse i = new IfThenElse();
      i.parse(con);
      return i;
    }
    if(cur.equals("+")) {
      Addition a = new Addition();
      a.parse(con);
      return a;
    }
    if(cur.equals("<")) {
      LessThan lt = new LessThan();
      lt.parse(con);
      return lt;
    }
    throw new RuntimeException("Unsupported identifier: " + cur);
  }
}
