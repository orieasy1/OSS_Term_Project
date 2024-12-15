package com.team3.ossproject.todolist.controller;

import com.team3.ossproject.global.dto.ApiResponse;
import com.team3.ossproject.global.dto.StringResponseDto;
import com.team3.ossproject.global.util.SecurityUtil;
import com.team3.ossproject.todolist.dto.request.CreateTaskRequest;
import com.team3.ossproject.todolist.dto.response.CreateTaskResponse;
import com.team3.ossproject.todolist.service.TodolistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


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
        String title = todolistService.createTodolist(SecurityUtil.getCurrentUserId(), request);
        return ResponseEntity.ok(new StringResponseDto("Todo task" + title + " created Successfully!"));
    }

}
