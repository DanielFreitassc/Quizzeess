package com.danielfreitassc.backend.repositories.quiz;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danielfreitassc.backend.models.quiz.QuizEntity;

public interface QuizRepository extends JpaRepository<QuizEntity,Long> {
    
}
