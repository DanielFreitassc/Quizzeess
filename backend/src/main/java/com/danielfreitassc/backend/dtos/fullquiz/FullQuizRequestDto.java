package com.danielfreitassc.backend.dtos.fullquiz;

import java.util.List;

import com.danielfreitassc.backend.dtos.alternative.AlternativeRequestDto;
import com.danielfreitassc.backend.dtos.question.QuestionRequestDto;
import com.danielfreitassc.backend.dtos.quiz.QuizRequestDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record FullQuizRequestDto(
    @NotNull(message = "Os dados do quiz são obrigatórios!")
    @Valid QuizRequestDto quiz,

    @NotNull(message = "A lista de questões não pode ser nula!")
    @Valid List<QuestionRequestDto> questions,

    @NotNull(message = "A lista de alternativas não pode ser nula!")
    @Valid List<AlternativeRequestDto> alternatives
) {
    
}
