package com.danielfreitassc.backend.dtos.question;

import com.danielfreitassc.backend.models.quiz.QuizEntity;

public record QuestionResponseDto(
    Long id,
    String statement,
    char correctAlternative,
    QuizEntity quizEntity
) {
    
}
