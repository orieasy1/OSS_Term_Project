package com.team3.ossproject.todolist.domain;

import com.team3.ossproject.user.domain.User;
import com.team3.ossproject.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Task extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long taskId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    private int day;

    private int week;

    private int month;

    private int year;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @Builder
    public Task(String title,
                String description,
                Type type,
                Status status,
                Priority priority,
                LocalDateTime completedAt,
                int day,
                int week,
                int month,
                int year,
                User user) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.status = status;
        this.priority = priority;
        this.completedAt = completedAt;
        this.day = day;
        this.week = week;
        this.month = month;
        this.year = year;
        this.user = user;
    }
}
