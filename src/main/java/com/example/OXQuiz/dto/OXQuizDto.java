package com.example.OXQuiz.dto;

import com.example.OXQuiz.entity.OXQuiz;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OXQuizDto {
    private Long id;

    @NotBlank(message = "퀴즈를 입력하세요.")
    private String quiz;

    @NotBlank(message = "정답(O/X)을 입력하세요.")
    private String answer;

    private String writer;

    // Entity를 Dto로 변환
    public static OXQuizDto fromOXQuizEntity(OXQuiz oxquiz) {
        return new OXQuizDto(
                oxquiz.getId(),
                oxquiz.getQuiz(),
                oxquiz.getAnswer(),
                oxquiz.getWriter()
        );
    }

    // Dto를 Entity로 변환
    public static OXQuiz fromOXQuizDto(OXQuizDto dto) {
        OXQuiz oxQuiz = new OXQuiz();
        oxQuiz.setId(dto.getId());
        oxQuiz.setQuiz(dto.getQuiz());
        oxQuiz.setAnswer(dto.getAnswer());
        oxQuiz.setWriter(dto.getWriter());
        return oxQuiz;
    }
}
