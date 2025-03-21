package com.danielfreitassc.backend.dtos.fullquiz;

import java.util.List;

import com.danielfreitassc.backend.dtos.alternative.AlternativeResponseDto;
import com.danielfreitassc.backend.dtos.question.QuestionResponseDto;

public record QuestionWithAlternativesDto(
    QuestionResponseDto question,
    List<AlternativeResponseDto> alternatives
) {
    
}
