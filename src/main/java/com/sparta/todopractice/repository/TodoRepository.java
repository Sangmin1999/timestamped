package com.sparta.todopractice.repository;

import com.sparta.todopractice.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findAllByCreatedAtBetweenOrderByModifiedAtDesc(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
