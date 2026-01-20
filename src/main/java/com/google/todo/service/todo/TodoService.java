package com.google.todo.service.todo;


import com.google.todo.domain.todo.Todo;
import com.google.todo.domain.todo.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TodoService {
    private final TodoRepository todoRepository;

    // 할 일 생성
    public Long createTodo(String title){
        Todo todo = Todo.create(title);
        return todoRepository.save(todo).getId();
    }
}
