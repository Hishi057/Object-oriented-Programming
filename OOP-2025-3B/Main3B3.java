import oop.ex3B3.Task;
import oop.ex3B3.TaskList;

public class Main3B3 {
  public static void main(String[] args) {
    TaskList list = new TaskList();
    list.addTask(new Task(1, "Do homework"));
    list.addTask(new Task(2, "Check thesis"));
    list.addTask(new Task(1, "Rating"));
    list.addTask(new Task(1, "Do homework"));
    list.addTask(new Task(4, "Attend meeting"));
    list.printList();
    if(list.removeTask(3)) { // remove "Rating" task or "Do homework" task
      System.out.println("Task 3 is deleted");
    }
    else {
      System.out.println("No task is deleted");
    }
    list.printList();
    if(list.removeTask(5)) { // do nothing
      System.out.println("Task 5 is deleted");
    }
    else {
      System.out.println("No task is deleted");
    }
    list.addTask(new Task(7, "Game"));
    list.printList();
  }
}
