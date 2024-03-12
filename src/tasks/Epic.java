package tasks;

import java.util.List;

public class Epic extends Task {
    private List<Task> subtasks;

    public Epic(String taskId, String title, String description, TaskStatus status, List<Task> subtasks) {
        super(taskId, title, description, status);
        this.subtasks = subtasks;
    }

    public void addSubtask(Task subtask){
        subtasks.add(subtask);
    }


    public List<Task> getSubtasks() {
        return subtasks;
    }
}
