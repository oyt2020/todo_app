package com.google.todo.domain.todo;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "todos")
public class Todo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 100)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TodoStatus status;


    protected Todo(String title){
        this.title = title;
        this.status = TodoStatus.PENDING;
    }

    public static Todo create(String title){
        return new Todo(title);
    }

    public void complete(){
        this.status = TodoStatus.COMPLETED;
    }

    public void updateTitle(String title){
        this.title = title;
    }
}
