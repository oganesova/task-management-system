package com.tasks.taskmanager.service;

import com.tasks.taskmanager.dto.TaskDto;
import com.tasks.taskmanager.entity.Task;
import com.tasks.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<TaskDto> searchTasksByTitle(String title) {
        List<Task> tasks = taskRepository.findByTitleContaining(title);
        return tasks.stream()
                .map(TaskDto::fromEntity)
                .collect(Collectors.toList());
    }

    public List<TaskDto> getTasksForToday() {
        LocalDate today = LocalDate.now();
        List<Task> tasks = taskRepository.findByDate(today);
        return tasks.stream()
                .map(TaskDto::fromEntity)
                .collect(Collectors.toList());
    }
    public TaskDto createTask(TaskDto taskDto) {
        Task task = TaskDto.toEntity(taskDto);
        Task savedTask = taskRepository.save(task);
        return TaskDto.fromEntity(savedTask);
    }
    public List<TaskDto> getTasksForThisWeek() {
        LocalDate startOfWeek = LocalDate.now().with(java.time.temporal.TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY));
        LocalDate endOfWeek = startOfWeek.plusDays(6);
        List<Task> tasks = taskRepository.findByDateBetween(startOfWeek, endOfWeek);
        return tasks.stream()
                .map(TaskDto::fromEntity)
                .collect(Collectors.toList());
    }
    public List<TaskDto> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(TaskDto::fromEntity)
                .collect(Collectors.toList());
    }
    public List<TaskDto> getTasksSortedByDate() {
        List<Task> tasks = taskRepository.findAll(org.springframework.data.domain.Sort.by(org.springframework.data.domain.Sort.Order.asc("date")));
        return tasks.stream()
                .map(TaskDto::fromEntity)
                .collect(Collectors.toList());
    }

    public List<TaskDto> getIncompleteTasks() {
        List<Task> tasks = taskRepository.findByCompleted(false);
        return tasks.stream()
                .map(TaskDto::fromEntity)
                .collect(Collectors.toList());
    }

    public List<TaskDto> getTasksByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Task> tasks = taskRepository.findByDateBetween(startDate, endDate);
        return tasks.stream()
                .map(TaskDto::fromEntity)
                .collect(Collectors.toList());
    }
}
