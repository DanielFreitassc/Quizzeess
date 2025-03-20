package com.danielfreitassc.backend.controllers.quiz;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.danielfreitassc.backend.dtos.common.MessageResponseDto;
import com.danielfreitassc.backend.dtos.question.QuestionRequestDto;
import com.danielfreitassc.backend.dtos.question.QuestionResponseDto;
import com.danielfreitassc.backend.services.question.QuestionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/questions")
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDto create(@RequestBody @Valid QuestionRequestDto questionRequestDto) {
        return questionService.create(questionRequestDto);
    }

    @GetMapping
    public Page<QuestionResponseDto> getQuestions(Pageable pageable) {
        return questionService.getQuestions(pageable);
    }

    @GetMapping("/{id}")
    public QuestionResponseDto getQuestion(@PathVariable Long id) {
        return questionService.getQuestion(id);
    }

    @PutMapping("/{id}")
    public MessageResponseDto updateQuestion(@PathVariable Long id,@RequestBody @Valid QuestionRequestDto questionRequestDto) {
        return questionService.updateQuestion(id, questionRequestDto);
    }

    @DeleteMapping("/{id}")
    public MessageResponseDto deleteQuestion(@PathVariable Long id) {
        return questionService.deleteQuestion(id);
    }
}
