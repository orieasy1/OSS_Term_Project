package com.team3.ossproject.todolist.service;

import com.team3.ossproject.todolist.domain.Priority;
import com.team3.ossproject.todolist.domain.Status;
import com.team3.ossproject.todolist.domain.Task;
import com.team3.ossproject.todolist.domain.Type;
import com.team3.ossproject.todolist.dto.request.CreateTaskRequest;
import com.team3.ossproject.todolist.dto.request.TaskUpdateRequest;
import com.team3.ossproject.todolist.dto.response.TaskResponse;
import com.team3.ossproject.todolist.dto.response.TaskUpdateResponse;
import com.team3.ossproject.todolist.exception.TaskErrorCode;
import com.team3.ossproject.todolist.exception.TaskException;
import com.team3.ossproject.todolist.repository.TaskRepository;
import com.team3.ossproject.user.domain.User;
import com.team3.ossproject.user.exception.UserErrorCode;
import com.team3.ossproject.user.exception.UserException;
import com.team3.ossproject.user.repository.UserRepository;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public TaskResponse getTodolist(Type type, int year, int month, int week, int day, Long userId) {
        // userId로 User 엔티티 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        List<Task> tasks;

        if (type == Type.DAY) {
            tasks = taskRepository.findByYearAndMonthAndDay(year, month, day);
        } else if (type == Type.WEEK) {
            tasks = taskRepository.findByYearAndMonthAndWeek(year, month, week);
        } else if (type == Type.MONTH) {
            tasks = taskRepository.findByYearAndMonth(year, month);
        } else {
            throw new TaskException(TaskErrorCode.INVALID_TYPE);
        }

        if (tasks.isEmpty()) {
            throw new TaskException(TaskErrorCode.TASK_NOT_FOUND);
        }

        return TaskResponse.builder()
                .type(type.getName())
                .todoat(type == Type.DAY ? day : (type == Type.WEEK ? week : month))
                .todolist(tasks.stream()
                        .map(task -> TaskResponse.Todolist.builder()
                                .title(task.getTitle())
                                .description(task.getDescription())
                                .status(task.getStatus().name())
                                .priority(task.getPriority().name())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

    public TaskUpdateResponse updateTask(Type type, Long taskId, TaskUpdateRequest request) {
        Task task = getTaskByIdOrThrow(taskId);

        if (!task.getType().equals(type)) {
            throw new TaskException(TaskErrorCode.INVALID_TYPE);
        }

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setPriority(Priority.fromName(request.getPriority()));

        Task updatedTask = taskRepository.save(task);

        return TaskUpdateResponse.builder()
                .taskId(updatedTask.getTaskId())
                .title(updatedTask.getTitle())
                .description(updatedTask.getDescription())
                .priority(updatedTask.getPriority().getName())
                .updatedAt(updatedTask.getUpdatedAt())
                .build();
    }

    public void deleteTask(Long taskId, Long userId) {
        Task task = getTaskByIdOrThrow(taskId);

        if (!task.getUser().getId().equals(userId)) {
            throw new TaskException(TaskErrorCode.UNAUTHORIZED_ACCESS);
        }

        taskRepository.delete(task);
    }

    public TaskUpdateResponse updateTaskStatus(Long taskId, Status status) {
        if (status == null) {
            throw new TaskException(TaskErrorCode.INVALID_STATUS);
        }

        Task task = getTaskByIdOrThrow(taskId);
        task.setStatus(status);

        Task updatedTask = taskRepository.save(task);

        return TaskUpdateResponse.builder()
                .taskId(updatedTask.getTaskId())
                .title(updatedTask.getTitle())
                .description(updatedTask.getDescription())
                .priority(updatedTask.getPriority().getName())
                .updatedAt(updatedTask.getUpdatedAt())
                .build();
    }

    private Task getTaskByIdOrThrow(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskException(TaskErrorCode.TASK_NOT_FOUND));
    }

}