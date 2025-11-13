package oop.ex4A3;

import java.util.HashMap;
import java.util.Map;

public class IDTable {
  Map<Person, String> person_to_id = new HashMap<Person, String>();
  Map<String, Person> id_to_person = new HashMap<String, Person>();

  //IDTable() {
  //}

  public String getID(Person p) {
    return person_to_id.get(p);
  }

  public Person getName(String id) {
    return id_to_person.get(id);
  }

  public void register(Person p, String id) {
    // 念の為消去
    remove(p);
    
    person_to_id.put(p, id);
    id_to_person.put(id, p);
  }

  public boolean remove(Person p) {
    if(person_to_id.containsKey(p)) {
      id_to_person.remove(person_to_id.get(p));
      person_to_id.remove(p);
      return true;
    }
    return false;
  }

  public boolean remove(String id) {
    if (id_to_person.containsKey(id)) {
      person_to_id.remove(id_to_person.get(id));
      id_to_person.remove(id);
      return true;
    }
    return true;
  }
}
