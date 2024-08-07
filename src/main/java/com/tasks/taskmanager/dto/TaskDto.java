package com.tasks.taskmanager.dto;
import com.tasks.taskmanager.entity.Task;
import lombok.Data;
import java.time.LocalDate;
@Data
public class TaskDto {
    private Long id;

    private String title;

    private String description;

    private LocalDate date;

    private boolean completed;
    public static TaskDto fromEntity(Task task) {
        TaskDto dto = new TaskDto();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setDate(task.getDate());
        dto.setCompleted(task.isCompleted());
        return dto;
    }

    public static Task toEntity(TaskDto dto) {
        Task task = new Task();
        task.setId(dto.getId());
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setDate(dto.getDate());
        task.setCompleted(dto.isCompleted());
        return task;
    }
}
