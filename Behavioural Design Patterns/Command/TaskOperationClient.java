// Command Interface

import java.util.ArrayList;
import java.util.List;

interface Command {
    void execute();
    void undo();
}

// Concrete Command for Adding Task
class AddTaskCommand implements Command {
    private TaskStorage taskStorage;
    private Task task;

    public AddTaskCommand(TaskStorage taskStorage, Task task) {
        this.taskStorage = taskStorage;
        this.task = task;
    }

    public void execute() {
        taskStorage.tasks.add(task);
        System.out.println("Task added: " + task.getName());
    }

    public void undo() {
        taskStorage.tasks.remove(task);
        System.out.println("Task added undo: " + task.getName());
    }
}

// Concrete Command for Deleting Task
class DeleteTaskCommand implements Command {
    private TaskStorage taskStorage;
    private Task task;

    public DeleteTaskCommand(TaskStorage taskStorage, Task task) {
        this.taskStorage = taskStorage;
        this.task = task;
    }

    public void execute() {
        taskStorage.tasks.remove(task);
        System.out.println("Task deleted: " + task.getName());
    }

    public void undo() {
        taskStorage.tasks.add(task);
        System.out.println("Task deleted undo: " + task.getName());
    }
}

// Invoker donot have any knowledge regarding the which command is being executed
class TaskInvoker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeCommand() {
        command.execute();
    }

    public void undo() {
        command.undo();
    }
}

// Receiver
class TaskStorage{
    public List<Task> tasks = new ArrayList<>();

    public void listTasks() {
        System.out.println("Tasks: ");
        for (Task task : tasks) {
            System.out.println(task.getName());
        }
    }
}

class Task{
    private String name;

    public Task(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
// Client
public class TaskOperationClient {
    public static void main(String[] args) {
        TaskStorage taskManager = new TaskStorage();
        Task task1 = new Task("Task 1");

        Command addTaskCommand = new AddTaskCommand(taskManager, task1);
        Command deleteTaskCommand = new DeleteTaskCommand(taskManager, task1);

        TaskInvoker invoker = new TaskInvoker();

        invoker.setCommand(addTaskCommand);
        invoker.executeCommand(); // Adds the task
        invoker.undo();
        
        taskManager.listTasks();

        invoker.setCommand(deleteTaskCommand);
        invoker.executeCommand(); // Deletes the task
    }
}
