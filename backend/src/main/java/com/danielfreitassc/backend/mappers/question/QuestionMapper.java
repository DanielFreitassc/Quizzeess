package com.danielfreitassc.backend.mappers.question;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.danielfreitassc.backend.dtos.question.QuestionRequestDto;
import com.danielfreitassc.backend.dtos.question.QuestionResponseDto;
import com.danielfreitassc.backend.models.quiz.QuestionEntity;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    QuestionResponseDto toDto(QuestionEntity questionEntity);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "quizEntity.id", source = "quizId")
    QuestionEntity toEntity(QuestionRequestDto questionRequestDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "quizEntity.id", source = "quizId")
    void toUpdate(QuestionRequestDto questionRequestDto, @MappingTarget QuestionEntity questionEntity);
}
