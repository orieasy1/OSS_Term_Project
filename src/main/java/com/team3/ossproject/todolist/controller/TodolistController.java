package com.team3.ossproject.todolist.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/todolist")
@Tag(name = "Todolist Controller", description = "[Todolist] Todolist API")
public class TodolistController {

    @PostMapping
    @Operation(summary = "할일 등록")
    public String addTask(){
        return null;
    }

    @GetMapping
    @Operation(summary = "할일 할 일")
    public String findTask(){
        return null;
    }

}
