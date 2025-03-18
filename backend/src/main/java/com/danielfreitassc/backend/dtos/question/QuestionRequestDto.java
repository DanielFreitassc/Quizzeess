package com.danielfreitassc.backend.dtos.question;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record QuestionRequestDto(
    @NotBlank(message = "Enunciado é necessário!")
    String statement,
    char correctAlternative,
    @NotNull(message = "Id de quiz é obrigatorio!")
    Long quizId
) {
    
}
