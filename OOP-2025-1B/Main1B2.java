class Main1B2 {
  public static void main(String[] args) {
    Person p1 = new Person();
    Person p2 = new Person();
    // 以下はPersonのフィールドとメソッドの実装後コメントアウトを外す
    
    System.out.println("P1 Name: " + p1.getName());
    System.out.println("P2 Name: " + p2.getName());
    p1.setName("Taro");
    p2.setName("Hanako");
    System.out.println("P1 Name: " + p1.getName());
    System.out.println("P2 Name: " + p2.getName());
    
  }
}