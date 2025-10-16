class Main1B3 {
  public static void main(String[] args) {
    // 以下はStudentの実装後コメントアウトを外す
    
    Student s = new Student("Toko", "22B01234");
    System.out.println("Name: " + s.getName());
    System.out.println("ID: " + s.getID());
    s.introduce();

    s.setName("Moriguchi");
    s.setID("03B24949");

    System.out.println("Name: " + s.getName());
    System.out.println("ID: " + s.getID());
    s.introduce();

    Person p = s;
    System.out.println("Name: " + p.getName());
    
  }
}