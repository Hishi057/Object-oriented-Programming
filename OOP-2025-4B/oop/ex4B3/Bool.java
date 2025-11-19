package oop.ex4B3;

public class Bool extends Value {
  private boolean val;

  public Bool() {
    val = false;
  }
  
  public Bool(boolean b) {
    val = b;
  }

  public boolean getValue() {
    return val;
  }

  @Override
  public boolean getBool() {
    return val;
  }

  @Override
  public void parse(Context con) {
    String tok = con.nextToken();
    if(tok.equals("true")) {
      val = true;
    }
    else if(tok.equals("false")) {
      val = false;
    }
    else {
      throw new RuntimeException("Not bool value");
    }
  }

  @Override
  public void accept(Visitor v) {
    v.visit(this);
  }

  @Override
  public String toString() {
    return Boolean.toString(val);
  }

  @Override
  public Value evaluate() {
    return this; // Valueクラスに実装した方が早い
  }

  @Override
  public int calcSteps(){
    return 1;
  }
}
