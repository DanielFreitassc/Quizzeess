package com.danielfreitassc.backend.dtos.user;

import java.time.LocalDate;

import com.danielfreitassc.backend.models.user.UserLanguage;

public record  UserResponseDto(
    Long id,
    String fullName,
    String username,
    String image,
    LocalDate birthDate,
    UserLanguage language
) {
    
}
