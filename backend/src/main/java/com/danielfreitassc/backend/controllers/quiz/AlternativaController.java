package com.danielfreitassc.backend.controllers.quiz;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielfreitassc.backend.dtos.alternative.AlternativeRequestDto;
import com.danielfreitassc.backend.dtos.alternative.AlternativeResponseDto;
import com.danielfreitassc.backend.dtos.common.MessageResponseDto;
import com.danielfreitassc.backend.services.alternative.AlternativeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/alternatives")
public class AlternativaController {
    private final AlternativeService alternativeService;

    @PostMapping
    public MessageResponseDto create(@RequestBody @Valid AlternativeRequestDto alternativeRequestDto) {
        return alternativeService.create(alternativeRequestDto);
    }

    @GetMapping
    public Page<AlternativeResponseDto> getAlternatives(Pageable pageable) {
        return alternativeService.getAlternatives(pageable);
    }

    @GetMapping("/{id}")
    public AlternativeResponseDto getAlternative(@PathVariable Long id) {
        return alternativeService.getAlternative(id);
    }

    @PutMapping("/{id}")
    public MessageResponseDto updateAlternative(@PathVariable Long id,@RequestBody @Valid AlternativeRequestDto alternativeRequestDto) {
        return alternativeService.updateAlternative(id, alternativeRequestDto);
    }

    @DeleteMapping("/{id}")
    public MessageResponseDto deleteAlternative(@PathVariable Long id) {
        return alternativeService.deleteAlternative(id);
    }
}
