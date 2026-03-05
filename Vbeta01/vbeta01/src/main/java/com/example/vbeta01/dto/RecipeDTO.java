package com.example.vbeta01.dto;

import java.util.List;

public record RecipeDTO(String name, String description, String instructions, List<RecipeItemDTO> items) {
    // eventually add an author
}
