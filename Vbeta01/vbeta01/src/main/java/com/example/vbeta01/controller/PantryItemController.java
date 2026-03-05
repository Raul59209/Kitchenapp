package com.example.vbeta01.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.vbeta01.dto.PantryItemDTO;
import com.example.vbeta01.mapper.PantryItemMapper;
import com.example.vbeta01.model.Ingredient;
import com.example.vbeta01.model.PantryItem;
import com.example.vbeta01.model.User;
import com.example.vbeta01.persistance.IngredientRepository;
import com.example.vbeta01.persistance.PantryItemRepository;
import com.example.vbeta01.persistance.UserRepository;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;




@RestController
@RequestMapping("users/{userId}/pantry_items")
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class PantryItemController {
    PantryItemRepository pantryItemRepository;
    IngredientRepository ingredientRepository;
    UserRepository userRepository;

    @GetMapping
    public Iterable<PantryItem> getPantry(@PathVariable Long userId) {
        return pantryItemRepository.findByUserId(userId);
    }
    
    @SuppressWarnings("null")
    @GetMapping("/{pantryId}")
    public PantryItem getPantryItemById(@PathVariable Long userId, @PathVariable Long pantryId) {
        return pantryItemRepository.findById(pantryId).orElseThrow();
    }

    @GetMapping("/search")
    public Iterable<PantryItem> getPantryItemsByIngredientName(@PathVariable Long userId, @RequestParam("name") String name) {
        return this.pantryItemRepository.findByIngredientName(name);
    }

    @PostMapping
    public PantryItem addPantryItem(@PathVariable @NonNull Long userId, @RequestBody PantryItemDTO dto) {
        User user = userRepository.findById(userId).orElseThrow();
        @SuppressWarnings("null")
        Ingredient ingredient = ingredientRepository.findById(dto.ingredientId()).orElseThrow();
        PantryItem item = PantryItemMapper.toEntity(dto, user, ingredient);
        @SuppressWarnings("null")
        PantryItem saved = this.pantryItemRepository.save(item);
        return saved;
    }
    
    @PutMapping("/{pantryItemId}")
    public PantryItem updatePantryItem(
        @PathVariable("pantryItemId") Long itemId,
        @RequestBody PantryItemDTO dto) {
            @SuppressWarnings("null")
            PantryItem item = pantryItemRepository.findById(itemId).orElseThrow();
            if (dto.amount() != null) { item.setAmount(dto.amount()); }
            if (dto.unit() != null) { item.setUnit(dto.unit()); }
            if (dto.expirationDate() != null) { item.setExpirationDate(dto.expirationDate()); }
            if (dto.notes() != null) { item.setNotes(dto.notes()); }
            @SuppressWarnings("null")
            PantryItem saved = this.pantryItemRepository.save(item);
            return saved;
    }

    @DeleteMapping("/{pantryItemId}")
    public void deletePantryItem(@PathVariable("pantryItemId") @NonNull Long pantryItemId) {
        this.pantryItemRepository.deleteById(pantryItemId);
    }
}

