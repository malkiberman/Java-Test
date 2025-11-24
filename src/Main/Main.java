package Main;

import Models.TaskStatus;
import Models.Task;
import Repositories.TaskRepository;
import Services.TaskService;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 1, 2};

        TaskRepository taskRepository = new TaskRepository();
        TaskService taskService = new TaskService(taskRepository);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("====== TODO MENU ======");
            System.out.println("1. Add task");
            System.out.println("2. Update task");
            System.out.println("3. Delete task");
            System.out.println("4. Mark task as DONE");
            System.out.println("5. Search task");
            System.out.println("6. List tasks sorted by status");
            System.out.println("7. Exit");
            System.out.print("Choose option: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {

                case 1:
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();

                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();

                    taskRepository.add(new Task(title, description, TaskStatus.NEW));

                    break;

                case 2:

                    System.out.print("Enter id to update: ");
                    int updateId = Integer.parseInt(scanner.nextLine());

                    System.out.print("Enter new title: ");
                    String newTitle = scanner.nextLine();

                    System.out.print("Enter new description: ");
                    String newDescription = scanner.nextLine();

                    taskRepository.add(new Task(newTitle, newDescription, TaskStatus.NEW));
                    break;

                case 3:

                    System.out.print("Enter id to delete: ");
                    int deleteId = Integer.parseInt(scanner.nextLine());

                    taskRepository.delete(deleteId);
                    break;

                case 4:

                    System.out.print("Enter id to mark as DONE: ");
                    int doneId = Integer.parseInt(scanner.nextLine());

                    taskService.done(doneId);
                    break;

                case 5:

                    System.out.print("Enter text to search: ");
                    String searchText = scanner.nextLine();

                    List<Task> searchResults = taskService.searchTask(searchText);
                    printList(searchResults);

                    break;

                case 6:

                    List<Task> sorted = taskService.sortByStatus();
                    printList(sorted);
                    break;

                case 7:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid option");
                    break;
            }
            System.out.println("-----------------------------");
        }
    }

    private static void printList(List<Task> tasks) {
        if (tasks == null || tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }
        for (Task task : tasks) {
            System.out.println(task);
        }
    }
}
