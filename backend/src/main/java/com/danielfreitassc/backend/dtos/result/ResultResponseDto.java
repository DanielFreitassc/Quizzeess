package com.danielfreitassc.backend.dtos.result;

import java.sql.Timestamp;

import com.danielfreitassc.backend.dtos.user.UserResponseDto;

public record ResultResponseDto(
    Long id,
    UserResponseDto userResponseDto,
    Long score,
    Timestamp completedAt
) {
    
}
