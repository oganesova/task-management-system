package com.tasks.taskmanager.controller;
import com.tasks.taskmanager.dto.TaskDto;
import com.tasks.taskmanager.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/search")
    public List<TaskDto> searchTasks(@RequestParam String title) {
        log.info("Received request to search tasks with title: {}", title);
        List<TaskDto> taskDtos = taskService.searchTasksByTitle(title);
        log.info("Returning {} tasks with title containing: {}", taskDtos.size(), title);
        return taskDtos;
    }

    @GetMapping("/today")
    public List<TaskDto> getTasksForToday() {
        log.info("Received request to get tasks for today");
        List<TaskDto> taskDtos = taskService.getTasksForToday();
        log.info("Returning {} tasks for today", taskDtos.size());
        return taskDtos;
    }

    @GetMapping("/week")
    public List<TaskDto> getTasksForThisWeek() {
        log.info("Received request to get tasks for this week");
        List<TaskDto> taskDtos = taskService.getTasksForThisWeek();
        log.info("Returning {} tasks for this week", taskDtos.size());
        return taskDtos;
    }

    @GetMapping("/sorted")
    public List<TaskDto> getTasksSortedByDate() {
        log.info("Received request to get tasks sorted by date");
        List<TaskDto> taskDtos = taskService.getTasksSortedByDate();
        log.info("Returning {} tasks sorted by date", taskDtos.size());
        return taskDtos;
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
        log.info("Received request to create task with details: {}", taskDto);
        TaskDto createdTask = taskService.createTask(taskDto);
        log.info("Task created with ID: {}", createdTask.getId());
        return ResponseEntity.ok(createdTask);
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        log.info("Received request to get all tasks");
        List<TaskDto> tasks = taskService.getAllTasks();
        log.info("Returning {} tasks", tasks.size());
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/incomplete")
    public ResponseEntity<List<TaskDto>> getIncompleteTasks() {
        log.info("Received request to get incomplete tasks");
        List<TaskDto> incompleteTasks = taskService.getIncompleteTasks();
        if (incompleteTasks.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(incompleteTasks);
        }
    }
    @GetMapping("/range")
    public List<TaskDto> getTasksByDateRange(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        log.info("Received request to get tasks from date range {} to {}", startDate, endDate);
        List<TaskDto> taskDtos = taskService.getTasksByDateRange(startDate, endDate);
        log.info("Returning {} tasks from date range {} to {}", taskDtos.size(), startDate, endDate);
        return taskDtos;
    }
}