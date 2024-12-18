package com.team3.ossproject.todolist.controller;

import com.team3.ossproject.global.dto.ApiResponse;
import com.team3.ossproject.global.dto.StringResponseDto;
import com.team3.ossproject.global.util.SecurityUtil;
import com.team3.ossproject.todolist.domain.Status;
import com.team3.ossproject.todolist.domain.Type;
import com.team3.ossproject.todolist.dto.request.CreateTaskRequest;
import com.team3.ossproject.todolist.dto.request.TaskUpdateRequest;
import com.team3.ossproject.todolist.dto.response.CreateTaskResponse;
import com.team3.ossproject.todolist.dto.response.TaskResponse;
import com.team3.ossproject.todolist.dto.response.TaskUpdateResponse;
import com.team3.ossproject.todolist.service.TodolistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoField;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/todolist")
@Tag(name = "Todolist Controller", description = "[Todolist] Todolist API")
public class TodolistController {

    private final TodolistService todolistService;

    @PostMapping
    @Operation(summary = "Add To-do Task")
    public ResponseEntity<StringResponseDto> addTask(
            @RequestBody CreateTaskRequest request){

        Long userId = SecurityUtil.getCurrentUserId();
        String title = todolistService.createTodolist(userId, request);
        return ResponseEntity.ok(new StringResponseDto("Todo task" + title + " created Successfully!"));
    }

    @GetMapping("/list/{type}")
    @Operation(summary = "GetTo-do Task")
    public ResponseEntity<ApiResponse<TaskResponse>> searchTask(
            @PathVariable String type,
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "month", required = false) Integer month,
            @RequestParam(value = "week", required = false) Integer week,
            @RequestParam(value = "day", required = false) Integer day){

        Type enumtype = Type.fromName(type);

        // 현재 시간으로 기본값 설정
        LocalDate now = LocalDate.now();
        year = (year != null) ? year : now.getYear();
        month = (month != null) ? month : now.getMonthValue();
        day = (day != null) ? day : now.getDayOfMonth();
        week = (week != null)? week : now.get(ChronoField.ALIGNED_WEEK_OF_YEAR);

        System.out.println("year:" + year + "month: "+ month + "week: " + week + "day: " + day);
        TaskResponse response = todolistService.getTodolist(enumtype, year, month, week, day, SecurityUtil.getCurrentUserId());
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @PutMapping("/{type}/{task_id}")
    @Operation(summary = "Update a To-do Task")
    public ResponseEntity<ApiResponse<TaskUpdateResponse>> updateTask(
            @PathVariable String type,
            @PathVariable("task_id") Long taskId,
            @RequestBody TaskUpdateRequest request,
            @RequestHeader("Authorization") String token) {

        Type enumType = Type.fromName(type); // 유효한 Type인지 검증
        TaskUpdateResponse response = todolistService.updateTask(enumType, taskId, request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @DeleteMapping("/{task_id}")
    public ResponseEntity<ApiResponse<StringResponseDto>> deleteTask(
            @PathVariable("task_id") Long taskId) {

        // 서비스 호출
        todolistService.deleteTask(taskId, SecurityUtil.getCurrentUserId());

        // StringResponseDto 생성
        StringResponseDto response = new StringResponseDto("Task deleted successful");

        // ApiResponse에 StringResponseDto 반환
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @PatchMapping("/{task_id}/{status}")
    public ResponseEntity<ApiResponse<TaskUpdateResponse>> updateTaskStatus(
            @PathVariable("task_id") Long taskId,
            @PathVariable("status") String status) {
        Status enumStatus = Status.fromName(status);

        TaskUpdateResponse response = todolistService.updateTaskStatus(taskId, enumStatus);
        return ResponseEntity.ok(ApiResponse.success(response));

    }

}
