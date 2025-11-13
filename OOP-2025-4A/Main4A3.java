import oop.ex4A3.*;

class Main4A3 {
  public static void checkID(IDTable t, Person p) {
    String id = t.getID(p);
    if(id == null) {
      System.out.println(p + "は見つかりませんでした");
    }
    else {
      System.out.println(p + "->" + id);
    }
  }

  public static void checkName(IDTable t, String id) {
    Person p = t.getName(id);
    if(p == null) {
      System.out.println(id + "は見つかりませんでした");
    }
    else {
      System.out.println(id + "<-" + p);
    }
  }

  public static void checkRemove(IDTable t, Person p) {
    if(t.remove(p)) {
      System.out.println(p + "を消去しました");
    }
    else {
      System.out.println(p + "は登録されていませんでした");
    }
  }

  public static void checkRemove(IDTable t, String id) {
    if(t.remove(id)) {
      System.out.println(id + "を消去しました");
    }
    else {
      System.out.println(id + "は登録されていませんでした");
    }
  }


  public static void main(String[] args) {
    IDTable t = new IDTable();

    checkID(t, new Person("小林", "隆志"));
    checkName(t, "03_24949");

    t.register(new Person("小林", "隆志"), "03_24949");

    checkID(t, new Person("小林", "隆志"));
    checkName(t, "03_24949");

    t.register(new Person("工大", "太郎"), "22B12345");

    checkID(t, new Person("工大", "太郎"));
    checkName(t, "22B12345");

    checkRemove(t, new Person("工大", "花子"));
    checkRemove(t, "22B12345");

    checkID(t, new Person("工大", "太郎"));
    checkName(t, "22B12345");

    t.register(new Person("小林", "隆志"), "07M38381");

    checkID(t, new Person("小林", "隆志"));
    checkName(t, "03_24949");
  }
}
