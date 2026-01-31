package com.google.todo.api.todo;


import com.google.todo.api.todo.request.TodoCreateRequest;
import com.google.todo.api.todo.request.TodoUpdateRequest;
import com.google.todo.api.todo.response.TodoResponse;
import com.google.todo.domain.todo.Todo;
import com.google.todo.domain.todo.TodoStatus;
import com.google.todo.global.response.ApiResponse;
import com.google.todo.service.todo.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://35.193.65.32") // 프론트 주소
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ApiResponse<Long> create(@RequestBody @Valid TodoCreateRequest request) {
        Long id = todoService.createTodo(request.getTitle());
        return ApiResponse.success(id);
    }

    @GetMapping("/{id}")
    public ApiResponse<TodoResponse> get(@PathVariable Long id) {
        Todo todo = todoService.getTodo(id);
        return ApiResponse.success(new TodoResponse(todo));
    }

    @GetMapping
    public ApiResponse<List<TodoResponse>> getTodos(
            @RequestParam(required = false)TodoStatus status
    ){

        List<Todo> todos = (status == null)
                ? todoService.getTodos()
                : todoService.getTodosByStatus(status);

        List<TodoResponse> response = todos.stream()
                .map(TodoResponse::new)
                .toList();

        return ApiResponse.success(response);
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> update(
            @PathVariable Long id,
            @RequestBody @Valid TodoUpdateRequest request
    ){
        todoService.updateTodo(id, request.getTitle());
        return ApiResponse.success(null);
    }

    @PatchMapping("/{id}/complete")
    public ApiResponse<Void> complete(@PathVariable Long id){

        todoService.completeTodo(id);
        return ApiResponse.success(null);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id){

        todoService.deleteTodo(id);
        return ApiResponse.success(null);
    }

}
