class Main1B1 {
  public static void main(String[] args) {
    System.out.println("The number of arguments: " + args.length);

    if (args.length >= 0) {
      int i = 0;
      for (String arg : args) {
        System.out.println("args[" + i + "] " + arg);
        i++;
      }
    }
  }
}
