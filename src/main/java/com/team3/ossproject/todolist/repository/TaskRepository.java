package com.team3.ossproject.todolist.repository;

import com.team3.ossproject.todolist.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
