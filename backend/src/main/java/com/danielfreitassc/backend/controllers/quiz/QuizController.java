package com.danielfreitassc.backend.controllers.quiz;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielfreitassc.backend.dtos.common.MessageResponseDto;
import com.danielfreitassc.backend.dtos.quiz.QuizRequestDto;
import com.danielfreitassc.backend.dtos.quiz.QuizResponseDto;
import com.danielfreitassc.backend.services.quiz.QuizService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/quizzes")
public class QuizController {
    private final QuizService quizService;

    @PostMapping
    public MessageResponseDto create(@RequestBody @Valid QuizRequestDto quizRequestDto) {
        return quizService.create(quizRequestDto);
    }

    @GetMapping
    public Page<QuizResponseDto> getQuizzes(Pageable pageable) {
        return quizService.getQuizzes(pageable);
    }

    @GetMapping("/{id}")
    public QuizResponseDto getQuiz(@PathVariable Long id) {
        return quizService.getQuiz(id);
    }

    @PutMapping("/{id}")
    public MessageResponseDto updateQuiz(@PathVariable Long id,@RequestBody @Valid QuizRequestDto quizRequestDto) {
        return quizService.updateQuiz(id, quizRequestDto);
    }

    @DeleteMapping("/{id}")
    public MessageResponseDto deleteQuiz(@PathVariable Long id) {
        return quizService.deleteQuiz(id);
    }
}
