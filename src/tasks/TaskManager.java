package tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskManager {
    private Map<String, Task> tasks;

    public TaskManager() {
        tasks = new HashMap<>();
    }

    public void addTask(Task task) {
        tasks.put(task.getTaskId(), task);
    }

    public void updateTask(Task updatedTask) {
        tasks.put(updatedTask.getTaskId(), updatedTask);
    }

    public Task getTaskById(String taskId) {
        return tasks.get(taskId);
    }

    public void removeTaskById(String taskId) {
        tasks.remove(taskId);
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public List<Task> getAllSubtasksOfEpic(Epic epic) {
        List<Task> subtasks = new ArrayList<>();
        for (Task task : tasks.values()) {
            if (task instanceof Epic) {
                Epic e = (Epic) task;
                if (e.getTaskId().equals(epic.getTaskId())) {
                    subtasks.addAll(e.getSubtasks());
                }
            }
        }
        return subtasks;
    }

    // Additional methods for managing statuses as per the provided rules
    public void manageStatus(Task task) {
        if (task instanceof Epic) {
            Epic epic = (Epic) task;
            List<Task> subtasks = epic.getSubtasks();
            if (subtasks.isEmpty() || allSubtasksDone(subtasks)) {
                epic.setStatus(TaskStatus.DONE);
            } else if (allSubtasksNew(subtasks)) {
                epic.setStatus(TaskStatus.NEW);
            } else {
                epic.setStatus(TaskStatus.IN_PROGRESS);
            }
        }
    }

    private boolean allSubtasksDone(List<Task> subtasks) {
        for (Task subtask : subtasks) {
            if (subtask.getStatus() != TaskStatus.DONE) {
                return false;
            }
        }
        return true;
    }

    private boolean allSubtasksNew(List<Task> subtasks) {
        for (Task subtask : subtasks) {
            if (subtask.getStatus() != TaskStatus.NEW) {
                return false;
            }
        }
        return true;
    }
}
