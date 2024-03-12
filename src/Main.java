import tasks.Epic;
import tasks.Task;
import tasks.TaskManager;
import tasks.TaskStatus;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        while (running) {
            System.out.println("Выберите действие:");
            System.out.println("1. Добавить задачу");
            System.out.println("2. Обновить задачу");
            System.out.println("3. Удалить задачу");
            System.out.println("4. Получить задачу по идентификатору");
            System.out.println("5. Получить список всех задач");
            System.out.println("6. Получить список всех подзадач определённого эпика");
            System.out.println("7. Выйти из программы");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Чтение символа новой строки после ввода числа

            switch (choice) {
                case 1:
                    System.out.println("Введите идентификатор задачи:");
                    String taskId = scanner.nextLine();
                    System.out.println("Введите название задачи:");
                    String title = scanner.nextLine();
                    System.out.println("Введите описание задачи:");
                    String description = scanner.nextLine();
                    System.out.println("Введите статус задачи (NEW, IN_PROGRESS или DONE):");
                    TaskStatus status = TaskStatus.valueOf(scanner.nextLine().toUpperCase());

                    Task newTask = new Task(taskId, title, description, status);
                    taskManager.addTask(newTask);
                    System.out.println("Задача успешно добавлена.");
                    break;

                case 2:
                    System.out.println("Введите идентификатор задачи для обновления:");
                    String updateTaskId = scanner.nextLine();
                    Task existingTask = taskManager.getTaskById(updateTaskId);
                    if (existingTask != null) {
                        System.out.println("Введите новый статус задачи (NEW, IN_PROGRESS или DONE):");
                        TaskStatus newStatus = TaskStatus.valueOf(scanner.nextLine().toUpperCase());
                        existingTask.setStatus(newStatus);
                        taskManager.updateTask(existingTask);
                        System.out.println("Задача успешно обновлена.");
                    } else {
                        System.out.println("Задача с таким идентификатором не найдена.");
                    }
                    break;

                case 3:
                    System.out.println("Введите идентификатор задачи для удаления:");
                    String deleteTaskId = scanner.nextLine();
                    Task taskToDelete = taskManager.getTaskById(deleteTaskId);
                    if (taskToDelete != null) {
                        taskManager.removeTaskById(deleteTaskId);
                        System.out.println("Задача успешно удалена.");
                    } else {
                        System.out.println("Задача с таким идентификатором не найдена.");
                    }
                    break;

                case 4:
                    System.out.println("Введите идентификатор задачи:");
                    String taskIdToGet = scanner.nextLine();
                    Task task = taskManager.getTaskById(taskIdToGet);
                    if (task != null) {
                        System.out.println("Задача найдена:");
                        System.out.println("Название: " + task.getTitle());
                        System.out.println("Описание: " + task.getDescription());
                        System.out.println("Статус: " + task.getStatus());
                    } else {
                        System.out.println("Задача с таким идентификатором не найдена.");
                    }
                    break;

                case 5:
                    List<Task> allTasks = taskManager.getAllTasks();
                    System.out.println("Список всех задач:");
                    for (Task t : allTasks) {
                        System.out.println("Идентификатор: " + t.getTaskId() + ", Название: " + t.getTitle() + ", Статус: " + t.getStatus());
                    }
                    break;

                case 6:
                    System.out.println("Введите идентификатор эпика:");
                    String epicId = scanner.nextLine();
                    Epic epic = (Epic) taskManager.getTaskById(epicId);
                    if (epic != null) {
                        List<Task> subtasks = taskManager.getAllSubtasksOfEpic(epic);
                        System.out.println("Список всех подзадач эпика " + epic.getTitle() + ":");
                        for (Task subtask : subtasks) {
                            System.out.println("Идентификатор: " + subtask.getTaskId() + ", Название: " + subtask.getTitle() + ", Статус: " + subtask.getStatus());
                        }
                    } else {
                        System.out.println("Эпик с таким идентификатором не найден.");
                    }
                    break;

                case 7:
                    running = false;
                    break;

                default:
                    System.out.println("Неверный выбор. Пожалуйста, выберите число от 1 до 7.");
                    break;
            }
        }
        scanner.close();
    }
}
