// Iterator interface

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

interface TaskIterator {
    boolean hasNext();
    Task next();
}

// Concrete Iterator
class TaskListIterator implements TaskIterator {
    private Queue<Task> taskQueue;

    public TaskListIterator(List<Task> tasks) {
        taskQueue = new LinkedList<>();
        addTasks(tasks);
    }

    // Flatten the structure to make the traversal esay
    private void addTasks(List<Task> tasks) {
        for (Task task : tasks) {
            taskQueue.add(task);
            if (task instanceof TaskList) { // Recursively add nested lists
                TaskList list = (TaskList) task;
                addTasks(list.getTasks());
            }
        }
    }

    @Override
    public boolean hasNext() {
        return !taskQueue.isEmpty();
    }

    @Override
    public Task next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return taskQueue.poll();
    }
}

// Task interface
interface Task {
    String getTitle();
    void setTitle(String title);
}

// Leaf Node
class SimpleTask implements Task {
    private String title;

    SimpleTask(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }
}

// Composite TaskList
class TaskList implements Task {
    private String title;
    private List<Task> tasks;

    public TaskList(String title) {
        this.title = title;
        this.tasks = new ArrayList<>();
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(Task task) {
        tasks.remove(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    // Returns an iterator for traversal
    public TaskIterator iterator() {
        return new TaskListIterator(tasks);
    }
}

// Main class
public class IteratorClient {
    public static void main(String[] args) {
        Task simpleTask1 = new SimpleTask("task 1");
        Task simpleTask2 = new SimpleTask("task 2");

        TaskList multiTaskList = new TaskList("Multi List");
        multiTaskList.addTask(simpleTask1);
        multiTaskList.addTask(simpleTask2);

        TaskList phase1 = new TaskList("Phase 1");
        phase1.addTask(new SimpleTask("super 1 phase"));
        phase1.addTask(new SimpleTask("super 2 phase"));
        multiTaskList.addTask(phase1);

        phase1.addTask(new SimpleTask("super 3 phase"));

        TaskList phase1_2 = new TaskList("internal phase 1 2");
        phase1_2.addTask(new SimpleTask("task 1 2"));
        phase1.addTask(phase1_2);

        // Use iterator to traverse all tasks
        System.out.println("\nIterating over all tasks:");
        TaskIterator iterator = multiTaskList.iterator();
        while (iterator.hasNext()) {
            Task task = iterator.next();
            System.out.println("Iterated Task: " + task.getTitle());
        }
    }
}
