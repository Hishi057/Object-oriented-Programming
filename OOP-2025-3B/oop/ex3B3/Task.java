package oop.ex3B3;

public class Task implements Comparable<Task> {
    int priority;
    String description;

    public Task(int priority, String description) {
        this.priority = priority;
        this.description = description;
    }

    @Override
    public int compareTo(Task other) {
        return Integer.compare(this.priority, other.priority);
    } 

    @Override
    public String toString() {
        return Integer.toString(priority) + " " + description;
    }
}
