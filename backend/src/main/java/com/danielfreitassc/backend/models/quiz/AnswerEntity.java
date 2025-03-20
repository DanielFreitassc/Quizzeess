package com.danielfreitassc.backend.models.quiz;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import com.danielfreitassc.backend.models.user.UserEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "answers")
@Entity(name = "answers")
public class AnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private QuestionEntity questionEntity;

    @ManyToOne
    @JoinColumn(name = "alternative_id", nullable = false)
    private AlternativeEntity alternativeEntity;

    @CreationTimestamp
    private Timestamp selectedAt;
}
