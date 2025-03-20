package com.danielfreitassc.backend.dtos.result;

import jakarta.validation.constraints.NotNull;

public record ResultRequestDto(
    @NotNull(message = "ID do usuário é necessário!")
    Long userId,
    @NotNull(message = "O campo score é necessário!")
    Long score
) {
    
}
