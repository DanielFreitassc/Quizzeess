package com.danielfreitassc.backend.controllers.quiz;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielfreitassc.backend.dtos.common.MessageResponseDto;
import com.danielfreitassc.backend.dtos.fullquiz.FullQuizRequestDto;
import com.danielfreitassc.backend.dtos.fullquiz.QuestionWithAlternativesDto;
import com.danielfreitassc.backend.services.fullquiz.QuizOrchestratorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/full-quizzes")
public class QuizOrchestratorController {
    private final QuizOrchestratorService quizOrchestratorService;

    @PostMapping
    public ResponseEntity<MessageResponseDto> createFullQuiz(@Valid @RequestBody FullQuizRequestDto fullQuizRequestDto) {
        MessageResponseDto response = quizOrchestratorService.createFullQuiz(fullQuizRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public QuestionWithAlternativesDto getQuestionWithAlternatives(@PathVariable Long id) {
        return quizOrchestratorService.getQuestionWithAlternatives(id);
    }
}
