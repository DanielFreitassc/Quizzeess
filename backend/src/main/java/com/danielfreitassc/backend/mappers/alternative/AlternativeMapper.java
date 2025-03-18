package com.danielfreitassc.backend.mappers.alternative;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.danielfreitassc.backend.dtos.alternative.AlternativeRequestDto;
import com.danielfreitassc.backend.dtos.alternative.AlternativeResponseDto;
import com.danielfreitassc.backend.models.quiz.AlternativeEntity;

@Mapper(componentModel = "spring")
public interface AlternativeMapper {
    AlternativeResponseDto toDto(AlternativeEntity alternativeEntity);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "questionEntity.id", source = "questionId")
    AlternativeEntity toEntity(AlternativeRequestDto alternativeRequestDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "questionEntity.id", source = "questionId")
    void toUpdate(AlternativeRequestDto alternativeRequestDto, @MappingTarget AlternativeEntity alternativeEntity);
}
