package com.danielfreitassc.backend.repositories.question;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danielfreitassc.backend.models.quiz.QuestionEntity;

public interface QuestionRepository extends JpaRepository<QuestionEntity,Long> {
    
}
