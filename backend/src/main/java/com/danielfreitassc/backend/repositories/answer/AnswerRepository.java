package com.danielfreitassc.backend.repositories.answer;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danielfreitassc.backend.models.quiz.AnswerEntity;

public interface AnswerRepository extends JpaRepository<AnswerEntity,Long> {

    boolean existsByUserEntity_IdAndQuestionEntity_Id(Long userId, Long questionId);
    
}
