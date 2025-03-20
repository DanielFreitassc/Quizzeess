package com.danielfreitassc.backend.mappers.quiz;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.danielfreitassc.backend.dtos.quiz.QuizRequestDto;
import com.danielfreitassc.backend.dtos.quiz.QuizResponseDto;
import com.danielfreitassc.backend.models.quiz.QuizEntity;

@Mapper(componentModel = "spring")
public interface QuizMapper {
    QuizResponseDto toDto(QuizEntity quizEntity);
    
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "createdAt",ignore = true)
    QuizEntity toEntity(QuizRequestDto quizRequestDto);

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "createdAt",ignore = true)
    void toUpdate(QuizRequestDto quizRequestDto, @MappingTarget QuizEntity quizEntity);
}
