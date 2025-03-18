package com.danielfreitassc.backend.dtos.quiz;

import jakarta.validation.constraints.NotBlank;

public record QuizRequestDto(
    @NotBlank(message = "Nome é necessário!")
    String name,
    @NotBlank(message = "Descrição é necessária!")
    String description,
    @NotBlank(message = "Categoria é necessária!")
    String category,
    @NotBlank(message = "Imagem é necessária!")
    String image
) {
    
}
