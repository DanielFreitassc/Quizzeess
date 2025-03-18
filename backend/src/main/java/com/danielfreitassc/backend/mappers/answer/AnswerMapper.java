package com.danielfreitassc.backend.mappers.answer;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.danielfreitassc.backend.dtos.answer.AnswerRequestDto;
import com.danielfreitassc.backend.dtos.answer.AnswerResponseDto;
import com.danielfreitassc.backend.models.quiz.AnswerEntity;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    AnswerResponseDto toDto(AnswerEntity answerEntity);

    @Mapping(target = "id", ignore =  true)
    @Mapping(target = "selectedAt", ignore =  true)
    @Mapping(target = "alternativeEntity.id", source = "alternativeId")
    @Mapping(target = "questionEntity.id", source = "questionId")
    @Mapping(target = "userEntity.id",source = "userId")
    AnswerEntity toEntity(AnswerRequestDto answerRequestDto);

    @Mapping(target = "id", ignore =  true)
    @Mapping(target = "selectedAt", ignore =  true)
    @Mapping(target = "alternativeEntity.id", source = "alternativeId")
    @Mapping(target = "questionEntity.id", source = "questionId")
    @Mapping(target = "userEntity.id",source = "userId")
    void toUpdate(AnswerRequestDto answerRequestDto, @MappingTarget AnswerEntity answerEntity);
}
