package com.lilkhalil.test.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lilkhalil.test.exceptions.EmptyStringException;
import com.lilkhalil.test.service.CharacterService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CharacterController {

    private final CharacterService characterService;
    
    @PostMapping("/characters")
    public ResponseEntity<?> recogniteCharacters(@RequestParam String str) throws EmptyStringException {
        return new ResponseEntity<>(characterService.getSymbolsNumber(str), HttpStatus.OK);
    }
    
}
