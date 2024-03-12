package tasks;


public class Task {
    private String taskId;
    private String title;
    private String description;
    private TaskStatus status;


    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Task(String taskId, String title, String description, TaskStatus status) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.status = status;
    }


    public String getTaskId() {
        return taskId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
