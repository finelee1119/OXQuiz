package com.example.OXQuiz.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class OXQuiz {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @Column (name = "quiz", length = 100, nullable = false)
    private String quiz;

    @Column (name = "answer", nullable = false)
    private String answer;

    @Column (name = "writer", length = 30)
    private String writer;
}
