package com.lilkhalil.test.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.lilkhalil.test.exceptions.EmptyStringException;

@Service
public class CharacterService {
    
    public Map<Character, Integer> getSymbolsNumber(String str) throws EmptyStringException {

        if (str.isEmpty()) throw new EmptyStringException("Empty string has been found!");

        Map<Character, Integer> map = new HashMap<>();

        for (char character : str.toCharArray()) {
            map.put(character, map.getOrDefault(character, 0) + 1);
        }

        map = map.entrySet().stream()
            .sorted(Entry.<Character, Integer>comparingByValue().reversed())
            .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (prev, next) -> next, LinkedHashMap::new));
        
        return map;
    }

}
