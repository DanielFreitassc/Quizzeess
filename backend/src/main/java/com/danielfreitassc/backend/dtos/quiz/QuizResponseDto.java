package com.danielfreitassc.backend.dtos.quiz;

public record QuizResponseDto(
    Long id,
    String name,
    String description,
    String category,
    String image,
    String createdAt
) {
    
}
