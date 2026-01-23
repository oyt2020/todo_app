package com.google.todo.api.todo;


import com.google.todo.api.todo.request.TodoCreateRequest;
import com.google.todo.api.todo.request.TodoUpdateRequest;
import com.google.todo.api.todo.response.TodoResponse;
import com.google.todo.domain.todo.Todo;
import com.google.todo.domain.todo.TodoStatus;
import com.google.todo.service.todo.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public Long create(@RequestBody @Valid TodoCreateRequest request) {
        return todoService.createTodo(request.getTitle());
    }

    @GetMapping("/{id}")
    public TodoResponse get(@PathVariable Long id) {
        Todo todo = todoService.getTodo(id);
        return new TodoResponse(todo);
    }

    @GetMapping
    public List<TodoResponse> getTodos(
            @RequestParam(required = false)TodoStatus status
    ){

        List<Todo> todos = (status == null)
                ? todoService.getTodos()
                : todoService.getTodosByStatus(status);

        return todos.stream()
                .map(TodoResponse::new)
                .toList();
    }

    @PutMapping("/{id}")
    public void update(
            @PathVariable Long id,
            @RequestBody @Valid TodoUpdateRequest request
    ){
        todoService.updateTodo(id, request.getTitle());
    }

    @PatchMapping("/{id}/complete")
    public void complete(@PathVariable Long id){
        todoService.completeTodo(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        todoService.deleteTodo(id);
    }

}
