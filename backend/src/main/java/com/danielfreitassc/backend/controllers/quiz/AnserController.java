package com.danielfreitassc.backend.controllers.quiz;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.danielfreitassc.backend.dtos.answer.AnswerRequestDto;
import com.danielfreitassc.backend.dtos.result.ResultResponseDto;
import com.danielfreitassc.backend.services.answer.AnswerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/answers")
public class AnserController {
    private final AnswerService answerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultResponseDto saveAnswer(@RequestBody @Valid AnswerRequestDto answerRequestDto) {
        return answerService.saveAnswer(answerRequestDto);
    }

    @GetMapping("/{id}")
    public Page<ResultResponseDto> getAnswer(Pageable pageable,@PathVariable Long id) {
        return answerService.getAnswer(pageable, id);
    }
}
