package com.team3.ossproject.todolist.service;

import com.team3.ossproject.todolist.domain.Priority;
import com.team3.ossproject.todolist.domain.Status;
import com.team3.ossproject.todolist.domain.Task;
import com.team3.ossproject.todolist.domain.Type;
import com.team3.ossproject.todolist.dto.request.CreateTaskRequest;
import com.team3.ossproject.todolist.repository.TaskRepository;
import com.team3.ossproject.user.domain.User;
import com.team3.ossproject.user.exception.UserErrorCode;
import com.team3.ossproject.user.exception.UserException;
import com.team3.ossproject.user.repository.UserRepository;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TodolistService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository; // UserRepository 추가

    public String createTodolist(Long userId, CreateTaskRequest request) {
        // userId로 User 엔티티 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        // Enum 값 처리
        Type type = request.getTypeAsEnum();
        Priority priority = request.getPriorityAsEnum();

        // Task 생성
        Task task = Task.builder()
                .user(user) // User 객체 설정
                .title(request.getTitle())
                .description(request.getDescription())
                .type(type)
                .status(Status.PENDING) // 상태는 항상 "PENDING"으로 설정
                .day(request.getDay() != null ? request.getDay() : 0) // null일 경우 기본값 설정
                .week(request.getWeek() != null ? request.getWeek() : 0)
                .month(request.getMonth() != null ? request.getMonth() : 0)
                .year(request.getYear() != null ? request.getYear() : 0)
                .priority(priority)
                .build();

        // Task 저장
        Task savedTask = taskRepository.save(task);

        return savedTask.getTitle();
    }
}
