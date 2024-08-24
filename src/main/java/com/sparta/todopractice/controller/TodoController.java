package com.sparta.todopractice.controller;

import com.sparta.todopractice.dto.*;
import com.sparta.todopractice.serviece.TodoServiece;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoServiece todoServiece;

    @PostMapping("todos")
    public ResponseEntity<TodoSaveResponseDto> saveTodo(@RequestBody TodoSaveRequestDto requestDto) {
        return ResponseEntity.ok(todoServiece.saveTodo(requestDto));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<TodoSimpleResponseDto>> getTodos(@RequestParam String date) {
        return ResponseEntity.ok(todoServiece.getTodos(date));
    }

    @GetMapping("/todos/{todoId}")
    public ResponseEntity<TodoDetailResponseDto> getTodo(@PathVariable Long todoId) {
        return ResponseEntity.ok(todoServiece.getTodo(todoId));
    }

    @PutMapping("/todos/{todoId}")
    public TodoUpdateResponseDto updateTodo(@PathVariable Long todoId, @RequestBody TodoUpdateRequestDto requestDto) {
        return todoServiece.updateTodo(todoId, requestDto);
    }

    @DeleteMapping("/todos/{todoId}")
    public void deleteTodo(@PathVariable Long todoId, @RequestBody TodoDeleteRequestDto requestDto) {
        todoServiece.deleteTodo(todoId, requestDto);
    }
}
