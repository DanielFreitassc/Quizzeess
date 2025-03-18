package com.danielfreitassc.backend.dtos.answer;

public record AnswerRequestDto(
    Long userId,
    Long questionId,
    Long alternativeId
) {
    
}
