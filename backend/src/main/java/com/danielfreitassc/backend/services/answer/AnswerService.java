package com.danielfreitassc.backend.services.answer;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.danielfreitassc.backend.dtos.answer.AnswerRequestDto;
import com.danielfreitassc.backend.dtos.result.ResultRequestDto;
import com.danielfreitassc.backend.dtos.result.ResultResponseDto;
import com.danielfreitassc.backend.mappers.answer.AnswerMapper;
import com.danielfreitassc.backend.mappers.result.ResultMapper;
import com.danielfreitassc.backend.models.quiz.AlternativeEntity;
import com.danielfreitassc.backend.models.quiz.AnswerEntity;
import com.danielfreitassc.backend.models.quiz.QuestionEntity;
import com.danielfreitassc.backend.models.result.ResultEntity;
import com.danielfreitassc.backend.models.user.UserEntity;
import com.danielfreitassc.backend.repositories.alternative.AlternativeRepository;
import com.danielfreitassc.backend.repositories.answer.AnswerRepository;
import com.danielfreitassc.backend.repositories.question.QuestionRepository;
import com.danielfreitassc.backend.repositories.result.ResultRepository;
import com.danielfreitassc.backend.repositories.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AlternativeRepository alternativeRepository;
    private final QuestionRepository questionRepository;
    private final ResultRepository resultRepository;
    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;
    private final ResultMapper resultMapper;
    private final AnswerMapper answerMapper;

    public ResultResponseDto saveAnswer(AnswerRequestDto answerRequestDto) {
        UserEntity userEntity = findUserOrThrow(answerRequestDto.userId());
        AlternativeEntity alternativeEntity = findAlternativeOrThrow(answerRequestDto.alternativeId());
        QuestionEntity questionEntity = findQuestionOrThrow(answerRequestDto.questionId());
    
        // Verifica se o usuário já respondeu essa questão
        if (answerRepository.existsByUserEntity_IdAndQuestionEntity_Id(answerRequestDto.userId(), answerRequestDto.questionId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Você já respondeu essa questão!");
        }
    
        // Salva a resposta do usuário
        AnswerEntity answerEntity = answerMapper.toEntity(answerRequestDto);
        answerRepository.save(answerEntity);
    
        // Verifica se a alternativa escolhida está correta
        long score = alternativeEntity.getAlternativeLetter().equals(questionEntity.getCorrectAlternative()) ? 10L : 0L;
    
        // Cria e salva o resultado do usuário
        ResultEntity resultEntity = resultMapper.toEntity(new ResultRequestDto(userEntity.getId(), score));
        resultRepository.save(resultEntity);
    
        return resultMapper.toDto(resultEntity);
    }

    public Page<ResultResponseDto> getAnswer(Pageable pageable, Long id) {
        return resultRepository.findByUserEntity_Id(id,pageable).map(resultMapper::toDto); 
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
