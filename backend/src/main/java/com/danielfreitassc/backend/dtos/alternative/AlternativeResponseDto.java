package com.danielfreitassc.backend.dtos.alternative;

import com.danielfreitassc.backend.models.quiz.QuestionEntity;

public record AlternativeResponseDto(
    Long id,
    String alternativeLetter,
    String body,
    QuestionEntity questionEntity
) {
    
}
