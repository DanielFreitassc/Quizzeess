package com.danielfreitassc.backend.mappers.result;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.danielfreitassc.backend.dtos.result.ResultRequestDto;
import com.danielfreitassc.backend.dtos.result.ResultResponseDto;
import com.danielfreitassc.backend.models.result.ResultEntity;

@Mapper(componentModel = "spring")
public interface ResultMapper {
    @Mapping(target = "userResponseDto",source = "userEntity")
    ResultResponseDto toDto(ResultEntity resultEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "completedAt", ignore = true)
    @Mapping(target = "userEntity.id", source = "userId")
    ResultEntity toEntity(ResultRequestDto resultRequestDto);
}
