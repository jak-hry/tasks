package com.crud.tasks.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "tasksDto")
public class TaskDto {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "name")
    private String title;
    @Column(name = "description")
    private String content;

    public TaskDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
