package com.example.demo.persistence;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Pantry;

import tools.jackson.databind.ObjectMapper;

@Repository
public class PantryRepository {

    private final ObjectMapper mapper;
    private final File file = new File("pantry.json");

    public PantryRepository(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public Pantry load() throws IOException {
        if (!file.exists()) {
            return new Pantry();
        }
        return mapper.readValue(file, Pantry.class);
    }

    public void save(Pantry pantry) throws IOException {
        mapper.writeValue(file, pantry);
    }
}
