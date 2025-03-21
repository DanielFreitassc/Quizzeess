package com.danielfreitassc.backend.repositories.alternative;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danielfreitassc.backend.models.quiz.AlternativeEntity;

public interface AlternativeRepository extends JpaRepository<AlternativeEntity, Long> {

    boolean existsByAlternativeLetterAndQuestionEntity_Id(Character alternativeLetter, Long questionId);
    List<AlternativeEntity> findByQuestionEntity_Id(Long questionId);
}
