package com.team3.ossproject.todolist.controller;

import com.team3.ossproject.global.dto.ApiResponse;
import com.team3.ossproject.todolist.domain.Type;
import com.team3.ossproject.todolist.dto.response.TaskResponse;
import com.team3.ossproject.todolist.service.TodolistService;
import com.team3.ossproject.global.util.SecurityUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.temporal.ChronoField;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class TodolistControllerTest {

    @InjectMocks
    private TodolistController todolistController;

    @Mock
    private TodolistService todolistService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSearchTask() {
        // Arrange
        TaskResponse mockResponse = new TaskResponse();
        LocalDate now = LocalDate.now();
        int defaultYear = now.getYear();
        int defaultMonth = now.getMonthValue();
        int defaultDay = now.getDayOfMonth();
        int defaultWeek = now.get(ChronoField.ALIGNED_WEEK_OF_YEAR);

        when(todolistService.getTodolist(eq(Type.DAY), eq(defaultYear), eq(defaultMonth), eq(defaultWeek), eq(defaultDay), anyLong()))
                .thenReturn(mockResponse);

        // Act
        ResponseEntity<ApiResponse<TaskResponse>> response = todolistController.searchTask("DAY", null, null, null, null);

        // Assert
        assertNotNull(response);
        assertEquals(mockResponse, response.getBody().getData());
        verify(todolistService, times(1)).getTodolist(eq(Type.DAY), eq(defaultYear), eq(defaultMonth), eq(defaultWeek), eq(defaultDay), anyLong());
    }

    @Test
    void testSearchTaskWithCustomParams() {
        // Arrange
        TaskResponse mockResponse = new TaskResponse();
        int year = 2024;
        int month = 5;
        int week = 3;
        int day = 15;

        when(todolistService.getTodolist(eq(Type.WEEK), eq(year), eq(month), eq(week), eq(day), anyLong()))
                .thenReturn(mockResponse);

        // Act
        ResponseEntity<ApiResponse<TaskResponse>> response = todolistController.searchTask("WEEK", year, month, week, day);

        // Assert
        assertNotNull(response);
        assertEquals(mockResponse, response.getBody().getData());
        verify(todolistService, times(1)).getTodolist(eq(Type.WEEK), eq(year), eq(month), eq(week), eq(day), anyLong());
    }

    @Test
    void testSearchTaskInvalidType() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            todolistController.searchTask("INVALID", null, null, null, null);
        });
    }
}
S