package com.danielfreitassc.backend.services.quiz;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.danielfreitassc.backend.dtos.common.MessageResponseDto;
import com.danielfreitassc.backend.dtos.quiz.QuizRequestDto;
import com.danielfreitassc.backend.dtos.quiz.QuizResponseDto;
import com.danielfreitassc.backend.mappers.quiz.QuizMapper;
import com.danielfreitassc.backend.models.quiz.QuizEntity;
import com.danielfreitassc.backend.repositories.quiz.QuizRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;
    private final QuizMapper quizMapper;


    public MessageResponseDto create(QuizRequestDto quizRequestDto) {
        quizRepository.save(quizMapper.toEntity(quizRequestDto));
        return new MessageResponseDto("Quiz criado!");
    }

    public Page<QuizResponseDto> getQuizzes(Pageable pageable) {
        return quizRepository.findAll(pageable).map(quizMapper::toDto);
    }

    public QuizResponseDto getQuiz(Long id) {
        return quizMapper.toDto(findQuizOrThrow(id));
    }

    public MessageResponseDto updateQuiz(Long id, QuizRequestDto quizRequestDto) {
        QuizEntity quizEntity = findQuizOrThrow(id);
        quizMapper.toUpdate(quizRequestDto, quizEntity);
        quizRepository.save(quizEntity);
        return new MessageResponseDto("Quiz atualiado!");
    }

    public MessageResponseDto deleteQuiz(Long id) {
        quizRepository.delete(findQuizOrThrow(id));
        return new MessageResponseDto("Quiz removido!");
    }

    private QuizEntity findQuizOrThrow(Long id) {
        Optional<QuizEntity> quiz = quizRepository.findById(id);
        if(quiz.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Quiz não encontrado!");
        return quiz.get();
    }
}
