package com.example.demo.persistence;

import com.example.demo.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;

public class DataManager {

    private static final ObjectMapper mapper =
            new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    private static final String PANTRY_FILE = "pantry.json";
    private static final String COOKBOOK_FILE = "cookbook.json";

    public static void savePantry(Pantry pantry) {
        try {
            mapper.writeValue(new File(PANTRY_FILE), pantry);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Pantry loadPantry() {
        try {
            return mapper.readValue(new File(PANTRY_FILE), Pantry.class);
        } catch (IOException e) {
            return new Pantry();
        }
    }

    public static void saveCookbook(Cookbook cookbook) {
        try {
            mapper.writeValue(new File(COOKBOOK_FILE), cookbook);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Cookbook loadCookbook() {
        try {
            return mapper.readValue(new File(COOKBOOK_FILE), Cookbook.class);
        } catch (IOException e) {
            return new Cookbook("My Cookbook");
        }
    }
}
