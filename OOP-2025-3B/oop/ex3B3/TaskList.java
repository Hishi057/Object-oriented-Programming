package oop.ex3B3;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> tasks = new ArrayList<Task>();

    private void sortTasks() {
        tasks.sort(null);
        return;
    }

    public void addTask(Task task) {
        tasks.add(task);
        return;
    }

    public void printList() {
        sortTasks();
        System.out.println("Task list");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ": " + tasks.get(i).toString());
        }
        return;
    }

    public boolean removeTask(int index) {
        if (index < 1 || index > tasks.size()) {
            return false;
        }

        tasks.remove(index - 1);
        return true;
    }
}
