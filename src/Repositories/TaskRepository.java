package Repositories;

import Models.Task;
import Models.TaskStatus;


import java.io.*;
import java.util.*;

public class TaskRepository {
    private String filePath;
    private Map<Integer, Task> tasksMap;

    public TaskRepository(String filePath) {
        this.filePath = filePath;
        tasksMap = new HashMap<>();
    }

    public TaskRepository() {
        this("tasks.json");
    }

    private void loudFromFile() {
        File file = new File(filePath);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        if (content.length() == 0) return;

        String allContent = content.toString();
        if (allContent.indexOf("[") == 0)
            allContent = allContent.substring(1, allContent.length() - 1);

        String[] taskObjects = allContent.split("\\}");

        for (String obj : taskObjects) {
            Task task = parseToTask(obj);
            tasksMap.put(task.getId(), task);
        }


    }

    private Task parseToTask(String obj) {

        obj = obj.trim().substring(0, obj.length() - 1);

        String[] keys = obj.split(",");

        int id = Integer.parseInt(keys[0].split(":")[1].replace("\"", "").trim());
        String title = keys[1].split(":")[1].replace("\"", "").trim();
        String desc = keys[2].split(":")[1].replace("\"", "").trim();
        TaskStatus status = TaskStatus.valueOf(keys[3].split(":")[1].replace("\"", "").trim());


        return new Task(id, title, desc, status);
    }

    private void save() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            int count = 0;
            writer.println("[");
            for (Task task : tasksMap.values()) {
                writer.println("{");
                writer.println("  \"id\": " + task.getId() + ",");
                writer.println("  \"title\": \"" + task.getTitle() + "\",");
                writer.println("  \"description\": \"" + task.getDescription() + "\",");
                writer.println("  \"status\": \"" + task.getStatus() + "\"");
                writer.println("}");
                if (count++ < tasksMap.size())
                    writer.println(",");
            }

            writer.println("]");

        } catch (Exception ignored) {
        }
    }

    public void add(Task task) {
        tasksMap.put(task.getId(), task);
        save();
    }

    public void update(Task task) {
        tasksMap.put(task.getId(), task);
        save();
    }

    public void delete(int id) {
        tasksMap.remove(id);
        save();
    }

    public Task getById(int id) {
        return tasksMap.get(id);
    }

    public List<Task> listAll() {
        return new ArrayList<>(tasksMap.values());
    }
}


