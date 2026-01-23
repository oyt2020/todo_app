package com.google.todo.domain.todo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;


import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TodoRepositoryTest {

    @Autowired
    TodoRepository todoRepository;

    @Test
    @DisplayName("할 일 저장 및 조회")
    void save_and_find(){
        Todo todo = Todo.create("CI/CD 공부");

        Todo saved = todoRepository.save(todo);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getStatus()).isEqualTo(TodoStatus.PENDING);
    }

    @Test
    @DisplayName("상태로 할 일 조회")
    void find_by_status(){
        todoRepository.save(Todo.create("테스트1"));
        todoRepository.save(Todo.create("테스트2"));

        var todos =  todoRepository.findByStatus(TodoStatus.PENDING);

        assertThat(todos).hasSize(2);
    }
}