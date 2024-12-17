package com.team3.ossproject.todolist.repository;

import com.team3.ossproject.todolist.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t WHERE t.year = :year AND t.month = :month AND t.day = :day")
    List<Task> findByYearAndMonthAndDay(@Param("year") int year, @Param("month") int month, @Param("day") int day);

    @Query("SELECT t FROM Task t WHERE t.year = :year AND t.month = :month AND t.week = :week")
    List<Task> findByYearAndMonthAndWeek(@Param("year") int year, @Param("month") int month, @Param("week") int week);

    @Query("SELECT t FROM Task t WHERE t.year = :year AND t.month = :month")
    List<Task> findByYearAndMonth(@Param("year") int year, @Param("month") int month);
}
