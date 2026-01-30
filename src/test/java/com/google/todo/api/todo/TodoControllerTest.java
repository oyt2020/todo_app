package com.google.todo.api.todo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.todo.api.todo.request.TodoCreateRequest;
import com.google.todo.service.todo.TodoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TodoController.class)
class TodoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    TodoService todoService;


    @Test
    @DisplayName("할 일 생성")
    void create_todo() throws Exception {
        given(todoService.createTodo("CI/ CD 공부")).willReturn(1L);

        TodoCreateRequest request =new TodoCreateRequest();

        String json = """
                {
                    "title": "CI/ CD 공부"
                }
                """;

        mockMvc.perform(post("/api/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));


        verify(todoService).createTodo("CI/ CD 공부");
    }

    @Test
    @DisplayName("실패 케이스 제목 없음 400 에러")
    void create_todo_validation_fail() throws Exception {
        String json = """
                {
                    "title" = ""
                }
                """;

        mockMvc.perform(post("/api/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());
    }

    //5. 단건 조회 API 테스트 (GET) 부터

}