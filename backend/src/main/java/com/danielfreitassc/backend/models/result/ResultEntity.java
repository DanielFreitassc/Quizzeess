package com.danielfreitassc.backend.models.result;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import com.danielfreitassc.backend.models.user.UserEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "results")
@Entity(name = "results")
public class ResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private UserEntity userEntity;
    private Long score;
    @CreationTimestamp
    private Timestamp completedAt;

}
