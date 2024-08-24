package com.sparta.todopractice.serviece;

import com.sparta.todopractice.dto.*;
import com.sparta.todopractice.entity.Todo;
import com.sparta.todopractice.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoServiece {

    private final TodoRepository todoRepository;

    @Transactional
    public TodoSaveResponseDto saveTodo(TodoSaveRequestDto requestDto) {
        Todo newTodo = new Todo(requestDto.getTodo(), requestDto.getManagerName(), requestDto.getPassword());
        Todo savedTodo = todoRepository.save(newTodo);

        return new TodoSaveResponseDto(
                savedTodo.getId(),
                savedTodo.getTodo(),
                savedTodo.getManagerName(),
                savedTodo.getCreatedAt(),
                savedTodo.getModifiedAt()
        );
    }

    public List<TodoSimpleResponseDto> getTodos(String date) {
        LocalDateTime startDateTime = LocalDate.parse(date).atStartOfDay();
        LocalDateTime endDateTime = LocalDate.parse(date).atTime(LocalTime.MAX);

        List<Todo> todos = todoRepository.findAllByCreatedAtBetweenOrderByModifiedAtDesc(startDateTime, endDateTime);

        List<TodoSimpleResponseDto> todoList = new ArrayList<>();
        for (Todo todo : todos) {
            TodoSimpleResponseDto dto = new TodoSimpleResponseDto(
                    todo.getId(),
                    todo.getTodo(),
                    todo.getManagerName(),
                    todo.getCreatedAt(),
                    todo.getModifiedAt()
            );
            todoList.add(dto);
        }
        return todoList;
    }

    public TodoDetailResponseDto getTodo(Long todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NullPointerException("no"));

        return new TodoDetailResponseDto(
                todo.getId(),
                todo.getTodo(),
                todo.getManagerName(),
                todo.getCreatedAt(),
                todo.getModifiedAt()
        );
    }

    @Transactional
    public TodoUpdateResponseDto updateTodo(Long todoId, TodoUpdateRequestDto requestDto) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NullPointerException("no"));

        todo.update(requestDto.getTodo(), requestDto.getManagerName());

        return new TodoUpdateResponseDto(
                todo.getId(),
                todo.getTodo(),
                todo.getManagerName()
        );
    }

    @Transactional
    public void deleteTodo(Long todoId, TodoDeleteRequestDto requestDto) {

        String password = requestDto.getPassword();
        if (password == null) {
            throw new NullPointerException("no");
        }
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NullPointerException("no"));

        if (!password.equals(todo.getPassword())) {
            throw new RuntimeException("no");
        }

        todoRepository.delete(todo);
    }
}
