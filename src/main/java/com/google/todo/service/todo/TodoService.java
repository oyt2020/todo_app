package com.google.todo.service.todo;


import com.google.todo.domain.todo.Todo;
import com.google.todo.domain.todo.TodoRepository;
import com.google.todo.domain.todo.TodoStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    //단건 조회
    @Transactional(readOnly = true)
    public Todo getTodo(Long todoId){
        return todoRepository.findById(todoId)
                .orElseThrow(()-> new IllegalArgumentException("Todo not found"));
    }

    // 전체 조회
    @Transactional(readOnly = true)
    public List<Todo> getTodos(){
        return todoRepository.findAll();
    }

    // 상태 별 조회 (완료 또는 미완료)
    @Transactional(readOnly = true)
    public List<Todo> getTodosByStatus(TodoStatus status){
        return todoRepository.findByStatus(status);
    }

    // 제목 업데이트
    public void updateTodo(Long todoId, String title){
        Todo todo = getTodo(todoId);
        todo.updateTitle(title);
    }

    // 완료 상태 변경
    public void completeTodo(Long todoId){
        Todo todo = getTodo(todoId);
        todo.complete();
    }

    // 삭제
    public void deleteTodo(Long todoId){
        todoRepository.deleteById(todoId);
    }







}
