package com.example.demo.persistence;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Cookbook;

import tools.jackson.databind.ObjectMapper;

@Repository
public class CookbookRepository {

    private final ObjectMapper mapper;
    private final File file = new File("cookbook.json");

    public CookbookRepository(ObjectMapper mapper) {
        this.mapper = new ObjectMapper();
    }

    public Cookbook load() throws IOException {
        if (!file.exists()) {
            return new Cookbook("My Cookbook");
        }
        return mapper.readValue(file, Cookbook.class);
    }

    public void save(Cookbook cookbook) throws IOException {
        mapper.writeValue(file, cookbook);
    }
}
