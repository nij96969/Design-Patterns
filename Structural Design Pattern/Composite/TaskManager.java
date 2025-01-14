//Component Node == composite + leaf

import java.util.ArrayList;
import java.util.List;

interface Task{
    String getTitle();
    void setTitle(String title);
    void display();
}

//Leaf Node
class SimpleTask implements Task{
    private String title;

    SimpleTask(String title){
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

    @Override
    public void display() {
        System.out.println("Title :: " + title);
    }
}

//Composite
class TaskList implements Task{
    private String title;
    private List<Task> tasks;

    public TaskList(String title){
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

    @Override
    public void display() {
        System.out.println("Task List: " + title);
        for (Task task : tasks) {
            task.display();
        }
    }

    public void addTask(Task task){
        tasks.add(task);
    }

    public void deleteTask(Task task){
        tasks.remove(task);
    }
}

public class TaskManager {

    public static void main(String[] args) {
        Task simpleTask1 = new SimpleTask("task 1");
        Task simpleTask2 = new SimpleTask("task 2");

        simpleTask1.display();
        simpleTask2.display();
        
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

        multiTaskList.display();
    }
}