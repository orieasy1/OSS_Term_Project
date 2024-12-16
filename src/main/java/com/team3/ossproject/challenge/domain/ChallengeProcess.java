package com.team3.ossproject.challenge.domain;

import com.team3.ossproject.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChallengeProcess extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "process_id")
    private Long processId;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(name = "is_completed", nullable = false)
    private boolean isCompleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="challenge_id", nullable = false)
    private Challenge challenge;

}