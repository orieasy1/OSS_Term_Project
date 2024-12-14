package com.team3.ossproject.todolist.service;

import com.team3.ossproject.todolist.domain.Priority;
import com.team3.ossproject.todolist.domain.Status;
import com.team3.ossproject.todolist.domain.Task;
import com.team3.ossproject.todolist.domain.Type;
import com.team3.ossproject.todolist.dto.request.CreateTaskRequest;
import com.team3.ossproject.todolist.dto.response.CreateTaskResponse;
import com.team3.ossproject.todolist.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TodolistService {
    private final TaskRepository taskRepository;

    public String createTodolist(Long userId, CreateTaskRequest request) {


        Type type = request.getTypeAsEnum();
        Priority priority = request.getPriorityAsEnum();

        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .type(type)
                .status(Status.PENDING) // 상태는 항상 "pending"으로 설정
                .day(request.getDay() != null ? request.getDay() : 0) // null일 경우 0으로 설정
                .week(request.getWeek() != null ? request.getWeek() : 0)
                .month(request.getMonth() != null ? request.getMonth() : 0)
                .year(request.getYear() != null ? request.getYear() : 0)
                .priority(priority)
                .build();

        Task savedTask = taskRepository.save(task);

        return savedTask.getTitle();
    }
}
