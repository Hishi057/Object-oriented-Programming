import oop.ex4B3.*;

class Main4B3 {
  public static void main(String[] args) {
    Expression[] es = new Expression[5];
    es[0] = Expression.getExpression(new Context("10"));
    es[1] = Expression.getExpression(new Context("false"));
    es[2] = Expression.getExpression(new Context("if < 10 + 4 5 then + 10 49 else 100"));
    es[3] = Expression.getExpression(new Context("if true then 10 else < + 1 5 1"));
    es[4] = Expression.getExpression(new Context("< 10 + false 2"));

    System.out.println("Step checking");
    System.out.println("Check by StepVisitor");
    for(Expression exp : es) {
      System.out.println("\t" + exp + " is calculated within " + new StepVisitor().calc(exp) + " steps");
    }

    
    System.out.println("Check by calcSteps method");
    for(Expression exp : es) {
      System.out.println("\t" + exp + " is calculated within " + exp.calcSteps() + " steps");
    }
    
    System.out.println();

    System.out.println("Evaluation");
    System.out.println("Evaluate by evaluate method");
    for(Expression exp : es) {
      try {
        System.out.println("\t" + exp + " is evaluated to " + exp.evaluate());
      } catch(RuntimeException e) {
        System.out.println("\t" + exp + " cannot be evaluated");
      }
    }

    System.out.println("Evaluate by EvaluateVisitor");
    for(Expression exp : es) {
      try {
        System.out.println("\t" + exp + " is evaluated to " + (new EvaluateVisitor().evaluate(exp)));
      } catch(RuntimeException e) {
        System.out.println("\t" + exp + " cannot be evaluated");
      }
    }

  }
}