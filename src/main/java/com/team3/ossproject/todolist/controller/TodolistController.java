package com.team3.ossproject.todolist.controller;

import com.team3.ossproject.global.dto.ApiResponse;
import com.team3.ossproject.global.dto.StringResponseDto;
import com.team3.ossproject.global.util.SecurityUtil;
import com.team3.ossproject.todolist.dto.request.CreateTaskRequest;
import com.team3.ossproject.todolist.service.TodolistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/todolist")
@Tag(name = "Todolist Controller", description = "[Todolist] Todolist API")
public class TodolistController {

    private final TodolistService todolistService;

    @PostMapping
    @Operation(summary = "Add To-do Task", description = "Creates a new to-do task for the current user.")
    public ResponseEntity<ApiResponse<StringResponseDto>> addTask(
            @RequestBody CreateTaskRequest request) {

        // Fetch current user ID and create a task
        Long userId = SecurityUtil.getCurrentUserId();
        String title = todolistService.createTodolist(userId, request);

        // Prepare response
        StringResponseDto response = new StringResponseDto("Todo task '" + title + "' created successfully!");
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
