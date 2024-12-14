package com.team3.ossproject.challenge.domain;


import com.team3.ossproject.user.domain.User;
import com.team3.ossproject.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Challenge extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long challengeId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Enumerated(EnumType.STRING)
    private Duration duration;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(nullable = false)
    private int count;

    @Column(name = "progress_rate", nullable = false)
    private float progressRate;

    @Column(name = "remaining_days", nullable = false)
    private int remainingDays;

    @Column(name = "finished_at")
    private LocalDateTime finishedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "challenge")
    private List<ChallengeProcess> processes;

    @Builder
    public Challenge(String title,
                String description,
                LocalDateTime startDate,
                Duration duration,
                Status status,
                int count,
                float progressRate,
                int remainingDays,
                LocalDateTime finishedAt) {
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.status = status;
        this.count = count;
        this.progressRate = progressRate;
        this.remainingDays = remainingDays;
        this.finishedAt = finishedAt;
    }
}
