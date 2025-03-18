package com.danielfreitassc.backend.services.question;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.danielfreitassc.backend.dtos.common.MessageResponseDto;
import com.danielfreitassc.backend.dtos.question.QuestionRequestDto;
import com.danielfreitassc.backend.dtos.question.QuestionResponseDto;
import com.danielfreitassc.backend.mappers.question.QuestionMapper;
import com.danielfreitassc.backend.models.quiz.QuestionEntity;
import com.danielfreitassc.backend.repositories.question.QuestionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    public MessageResponseDto create(QuestionRequestDto questionRequestDto) {
        questionRepository.save(questionMapper.toEntity(questionRequestDto));
        return new MessageResponseDto("Questão criada!");
    }

    public Page<QuestionResponseDto> getQuestions(Pageable pageable) {
        return questionRepository.findAll(pageable).map(questionMapper::toDto);
    }

    public QuestionResponseDto getQuestion(Long id) {
        return questionMapper.toDto(findQuestionOrThrow(id));
    }

    public MessageResponseDto updateQuestion(Long id, QuestionRequestDto questionRequestDto) {
        QuestionEntity questionEntity = findQuestionOrThrow(id);
        questionMapper.toUpdate(questionRequestDto, questionEntity);
        questionRepository.save(questionEntity);
        return new MessageResponseDto("Questão atualiza!");
    }

    public MessageResponseDto deleteQuestion(Long id) {
        questionRepository.delete(findQuestionOrThrow(id));
        return new MessageResponseDto("Questão removida");
    }

    private QuestionEntity findQuestionOrThrow(Long id) {
        Optional<QuestionEntity> question = questionRepository.findById(id);
        if(question.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhuma questão encontrada!");
        return question.get();
    }
}
