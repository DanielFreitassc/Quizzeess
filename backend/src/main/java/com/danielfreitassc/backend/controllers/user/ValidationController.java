package com.danielfreitassc.backend.controllers.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielfreitassc.backend.dtos.user.ValidationResponseDTO;
import com.danielfreitassc.backend.services.user.ValidationService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/validation")
public class ValidationController {
    private final ValidationService validationService;


    @GetMapping
    public ResponseEntity<ValidationResponseDTO> validate(HttpServletRequest request) {
        return validationService.validate(request);
    }
}
