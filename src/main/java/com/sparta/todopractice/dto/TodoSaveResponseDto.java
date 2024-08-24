package com.sparta.todopractice.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TodoSaveResponseDto {

    private final Long id;
    private final String todo;
    private final String managerName;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public TodoSaveResponseDto(
            Long id,
            String todo,
            String managerName,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt
    ) {
        this.id = id;
        this.todo = todo;
        this.managerName = managerName;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
