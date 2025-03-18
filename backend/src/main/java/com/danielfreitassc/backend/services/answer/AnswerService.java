package com.danielfreitassc.backend.services.answer;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.danielfreitassc.backend.dtos.answer.AnswerRequestDto;
import com.danielfreitassc.backend.dtos.common.MessageResponseDto;
import com.danielfreitassc.backend.dtos.user.UserRequestDto;
import com.danielfreitassc.backend.mappers.answer.AnswerMapper;
import com.danielfreitassc.backend.models.quiz.AlternativeEntity;
import com.danielfreitassc.backend.models.quiz.QuestionEntity;
import com.danielfreitassc.backend.models.quiz.QuizEntity;
import com.danielfreitassc.backend.models.user.UserEntity;
import com.danielfreitassc.backend.repositories.alternative.AlternativeRepository;
import com.danielfreitassc.backend.repositories.answer.AnswerRepository;
import com.danielfreitassc.backend.repositories.question.QuestionRepository;
import com.danielfreitassc.backend.repositories.quiz.QuizRepository;
import com.danielfreitassc.backend.repositories.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AlternativeRepository alternativeRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;
    private final AnswerMapper answerMapper;

    public MessageResponseDto saveAnswer(AnswerRequestDto answerRequestDto) {
        findUserOrThrow(answerRequestDto.userId());
        findAlternativeOrThrow(answerRequestDto.alternativeId());
        findQuestionOrThrow(answerRequestDto.questionId());
        return new MessageResponseDto("Passouu");
    }


    private UserEntity findUserOrThrow(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if(user.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuário não encontrado");
        return user.get();
    }

    private QuestionEntity findQuestionOrThrow(Long id) {
        Optional<QuestionEntity> question = questionRepository.findById(id);
        if(question.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhuma questão encontrada!");
        return question.get();
    }

    private AlternativeEntity findAlternativeOrThrow(Long id) {
        Optional<AlternativeEntity> alternative = alternativeRepository.findById(id);
        if(alternative.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhum alternativa encontra");
        return alternative.get();
    }
}
