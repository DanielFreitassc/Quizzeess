package com.danielfreitassc.backend.dtos.alternative;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AlternativeRequestDto(
    @NotNull(message = "Indicar letra da alternativa!")
    Character alternativeLetter,
    @NotBlank(message = "Enunciado da alternativa é necessária!")
    String body,
    @NotNull(message = "Id da questão é obrigatorio!")
    Long questionId
) {
    
}
