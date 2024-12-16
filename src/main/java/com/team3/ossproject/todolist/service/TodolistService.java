package com.team3.ossproject.todolist.service;

import com.team3.ossproject.todolist.domain.Priority;
import com.team3.ossproject.todolist.domain.Status;
import com.team3.ossproject.todolist.domain.Task;
import com.team3.ossproject.todolist.domain.Type;
import com.team3.ossproject.todolist.dto.request.TaskCreateRequest;
import com.team3.ossproject.todolist.dto.request.TaskUpdateRequest;
import com.team3.ossproject.todolist.dto.response.TaskCreateResponse;
import com.team3.ossproject.todolist.dto.response.TaskResponse;
import com.team3.ossproject.todolist.dto.response.TaskUpdateResponse;
import com.team3.ossproject.todolist.exception.TaskErrorCode;
import com.team3.ossproject.todolist.exception.TaskException;
import com.team3.ossproject.todolist.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TodolistService {

    private final TaskRepository taskRepository; // UserRepository 추가

    public TaskCreateResponse createTask(Type type, TaskCreateRequest request) {

        // Enum 값 처리
        Priority priority = request.getPriorityAsEnum();

        // Task 생성
        Task task = Task.builder()
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

        String todoat = generateTodoAt(type, request);

        return TaskCreateResponse.builder()
                .title(savedTask.getTitle())
                .type(savedTask.getType().getName())
                .todoat(todoat)
                .build();
    }

    private String generateTodoAt(Type type, TaskCreateRequest request) {

        if (type.equals(Type.DAY)) {
            return request.getYear() + "-" + String.format("%02d", request.getMonth()) + "-" + String.format("%02d", request.getDay());
        } else if (type.equals(Type.WEEK)) {
            return request.getYear() + "-" + String.format("%02d", request.getMonth()) + " Week " + request.getWeek();
        } else if (type.equals(Type.MONTH)) {
            return request.getYear() + "-" + String.format("%02d", request.getMonth());
        } else {
            throw new TaskException(TaskErrorCode.INVALID_TYPE);
        }
    }

    public TaskResponse getTodolist(Type type, int year, int month, int week, int day) {
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

        // taskId가 유효한지 확인
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskException(TaskErrorCode.TASK_NOT_FOUND));

        // 타입 검증
        if (!task.getType().equals(type)) {
            throw new TaskException(TaskErrorCode.INVALID_TYPE);
        }


        // Task 업데이트
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setPriority(Priority.fromName(request.getPriority()));

        // 저장
        Task updatedTask = taskRepository.save(task);

        // 응답 생성
        return TaskUpdateResponse.builder()
                .taskId(updatedTask.getTaskId())
                .title(updatedTask.getTitle())
                .description(updatedTask.getDescription())
                .priority(updatedTask.getPriority().getName())
                .updatedAt(updatedTask.getUpdatedAt())
                .build();
    }

    public void deleteTask(Long taskId, String username) {
        // Task 존재 여부 확인
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskException(TaskErrorCode.TASK_NOT_FOUND));

        // 삭제
        taskRepository.delete(task);
    }

    public TaskUpdateResponse updateTaskStatus (Long taskId, Status status) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskException(TaskErrorCode.TASK_NOT_FOUND));

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
}
