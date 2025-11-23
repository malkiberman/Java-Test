package Main;

import Models.TaskStatus;
import Models.Task;
import Repositories.TaskRepository;
import Services.TaskService;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        TaskRepository taskRepository = new TaskRepository();
        TaskService taskService = new TaskService(taskRepository);

        Scanner scanner = new Scanner(System.in);


}
