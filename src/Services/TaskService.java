package Services;

import Models.Task;
import Models.TaskStatus;
import Repositories.TaskRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TaskService {

    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository ) {
        this.taskRepository = taskRepository;
    }

    public Task done(int id) {
        if (!(taskRepository.listAll().contains(id)))
            return null;
        Task task = taskRepository.getById(id);
        task.setStatus(TaskStatus.DONE);
        return task;
    }

    public List<Task> searchTask(String text) {
        List<Task> tasks = taskRepository.listAll();
        List<Task> result = new ArrayList<>();
        for (Task task:tasks){
            if(task.getDescription().contains(text)||task.getTitle().contains(text))
                result.add(task);
        }
        return result;
    }


    public List<Task> sortByStatus() {
        List<Task> tasks = taskRepository.listAll();

       tasks.sort(Comparator.comparing(Task::getStatus));
        return tasks;
    }
}
