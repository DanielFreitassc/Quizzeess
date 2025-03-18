package com.danielfreitassc.backend.services.alternative;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.danielfreitassc.backend.dtos.alternative.AlternativeRequestDto;
import com.danielfreitassc.backend.dtos.alternative.AlternativeResponseDto;
import com.danielfreitassc.backend.dtos.common.MessageResponseDto;
import com.danielfreitassc.backend.mappers.alternative.AlternativeMapper;
import com.danielfreitassc.backend.models.quiz.AlternativeEntity;
import com.danielfreitassc.backend.repositories.alternative.AlternativeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlternativeService {
    private final AlternativeRepository alternativeRepository;
    private final AlternativeMapper alternativeMapper;

    public MessageResponseDto create(AlternativeRequestDto alternativeRequestDto) {
        alternativeRepository.save(alternativeMapper.toEntity(alternativeRequestDto));
        return new MessageResponseDto("Alternativa criada!");
    }

    public Page<AlternativeResponseDto> getAlternatives(Pageable pageable) {
        return alternativeRepository.findAll(pageable).map(alternativeMapper::toDto);
    }

    public AlternativeResponseDto getAlternative(Long id) {
        return alternativeMapper.toDto(findAlternativeOrThrow(id));
    }

    public MessageResponseDto updateAlternative(Long id, AlternativeRequestDto alternativeRequestDto) {
        AlternativeEntity alternativeEntity = findAlternativeOrThrow(id);
        alternativeMapper.toUpdate(alternativeRequestDto, alternativeEntity);
        alternativeRepository.save(alternativeEntity);
        return new MessageResponseDto("Alternativa atualizada!");
    }

    public MessageResponseDto deleteAlternative(Long id) {
        alternativeRepository.delete(findAlternativeOrThrow(id));
        return new MessageResponseDto("Alternativa removida");
    }

    private AlternativeEntity findAlternativeOrThrow(Long id) {
        Optional<AlternativeEntity> alternative = alternativeRepository.findById(id);
        if(alternative.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhum alternativa encontra");
        return alternative.get();
    }
}
