package com.example.vbeta01.mapper;

import com.example.vbeta01.dto.PantryItemDTO;
import com.example.vbeta01.model.Ingredient;
import com.example.vbeta01.model.PantryItem;
import com.example.vbeta01.model.User;

public class PantryItemMapper {
    
    public static PantryItem toEntity(PantryItemDTO dto, User user, Ingredient ingredient) {
        PantryItem item = new PantryItem();
        item.setUser(user);
        item.setIngredient(ingredient);
        item.setAmount(dto.amount());
        item.setUnit(dto.unit());
        item.setExpirationDate(dto.expirationDate());
        item.setNotes(dto.notes());
        return item;
    }
}
