package com.danielfreitassc.backend.controllers.quiz;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielfreitassc.backend.dtos.answer.AnswerRequestDto;
import com.danielfreitassc.backend.dtos.common.MessageResponseDto;
import com.danielfreitassc.backend.services.answer.AnswerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/answers")
public class AnserController {
    private final AnswerService answerService;

    @PostMapping
    public MessageResponseDto saveAnswer(@RequestBody @Valid AnswerRequestDto answerRequestDto) {
        return answerService.saveAnswer(answerRequestDto);
    }
}
