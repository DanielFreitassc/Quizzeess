package com.danielfreitassc.backend.dtos.answer;

import java.sql.Timestamp;

import com.danielfreitassc.backend.models.quiz.AlternativeEntity;
import com.danielfreitassc.backend.models.quiz.QuestionEntity;
import com.danielfreitassc.backend.models.user.UserEntity;

public record AnswerResponseDto(
    Long id,
    UserEntity userEntity,
    QuestionEntity questionEntity,
    AlternativeEntity alternativeEntity,
    Timestamp selectedAt
) {
    
}
