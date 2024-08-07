package com.tasks.taskmanager.controller;
import com.tasks.taskmanager.dto.TaskDto;
import com.tasks.taskmanager.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/search")
    public List<TaskDto> searchTasks(@RequestParam String title) {
        return taskService.searchTasksByTitle(title);
    }

    @GetMapping("/today")
    public List<TaskDto> getTasksForToday() {
        return taskService.getTasksForToday();
    }

    @GetMapping("/week")
    public List<TaskDto> getTasksForThisWeek() {
        return taskService.getTasksForThisWeek();
    }

    @GetMapping("/sorted")
    public List<TaskDto> getTasksSortedByDate() {
        return taskService.getTasksSortedByDate();
    }
    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
        TaskDto createdTask = taskService.createTask(taskDto);
        return ResponseEntity.ok(createdTask);
    }
    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        List<TaskDto> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }
    @GetMapping("/incomplete")
    public List<TaskDto> getIncompleteTasks() {
        return taskService.getIncompleteTasks();
    }

    @GetMapping("/range")
    public List<TaskDto> getTasksByDateRange(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return taskService.getTasksByDateRange(startDate, endDate);
    }
}
