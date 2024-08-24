package com.sparta.todopractice.dto;

import lombok.Getter;

@Getter
public class TodoUpdateResponseDto {

    private final Long id;
    private final String todo;
    private final String managerName;

    public TodoUpdateResponseDto(Long id, String todo, String managerName) {
        this.id = id;
        this.todo = todo;
        this.managerName = managerName;
    }
}
