package com.danielfreitassc.backend.services.fullquiz;

import com.danielfreitassc.backend.dtos.alternative.AlternativeRequestDto;
import com.danielfreitassc.backend.dtos.alternative.AlternativeResponseDto;
import com.danielfreitassc.backend.dtos.common.MessageResponseDto;
import com.danielfreitassc.backend.dtos.fullquiz.FullQuizRequestDto;
import com.danielfreitassc.backend.dtos.fullquiz.QuestionWithAlternativesDto;
import com.danielfreitassc.backend.dtos.question.QuestionRequestDto;
import com.danielfreitassc.backend.dtos.question.QuestionResponseDto;
import com.danielfreitassc.backend.models.quiz.QuizEntity;
import com.danielfreitassc.backend.services.alternative.AlternativeService;
import com.danielfreitassc.backend.services.question.QuestionService;
import com.danielfreitassc.backend.services.quiz.QuizService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class QuizOrchestratorService {
    private final QuizService quizService;
    private final QuestionService questionService;
    private final AlternativeService alternativeService;

    @Transactional
    public MessageResponseDto createFullQuiz(FullQuizRequestDto fullQuizRequestDto) {
        QuizEntity quizEntity = quizService.createAndReturnEntity(fullQuizRequestDto.quiz());
        Long quizId = quizEntity.getId();

        Map<Long, Long> questionIdMap = new HashMap<>();

        for (QuestionRequestDto questionDto : fullQuizRequestDto.questions()) {
            var questionEntity = questionService.createAndReturnEntity(new QuestionRequestDto(
                questionDto.statement(),
                questionDto.correctAlternative(),
                quizId
            ));
            questionIdMap.put(questionEntity.getId(), questionEntity.getId());
        }

        for (AlternativeRequestDto alternativeDto : fullQuizRequestDto.alternatives()) {
            Long questionId = alternativeDto.questionId();  
            
            if (questionIdMap.containsKey(questionId)) {  
                alternativeService.create(new AlternativeRequestDto(
                    alternativeDto.alternativeLetter(),
                    alternativeDto.body(),
                    questionId
                ));
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Alternativa com questionId inválido.");
            }
        }

        return new MessageResponseDto("Quiz completo criado com sucesso!");
    }

    public QuestionWithAlternativesDto getQuestionWithAlternatives(Long id) {
        QuestionResponseDto question = questionService.getQuestion(id);

        List<AlternativeResponseDto> alternatives = alternativeService.getAlternativesByQuestionId(id);

        return new QuestionWithAlternativesDto(question, alternatives);
    }
}
