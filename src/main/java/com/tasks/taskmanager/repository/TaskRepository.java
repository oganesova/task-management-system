package com.tasks.taskmanager.repository;
import com.tasks.taskmanager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByTitleContaining(String title);

    List<Task> findByDate(LocalDate date);

    List<Task> findByDateBetween(LocalDate startDate, LocalDate endDate);

    List<Task> findByCompleted(boolean completed);
}
