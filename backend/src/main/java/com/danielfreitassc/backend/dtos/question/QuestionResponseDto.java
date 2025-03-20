package com.danielfreitassc.backend.dtos.question;

import com.danielfreitassc.backend.models.quiz.QuizEntity;

public record QuestionResponseDto(
    Long id,
    String statement,
    Character correctAlternative,
    QuizEntity quizEntity
) {
    
}
