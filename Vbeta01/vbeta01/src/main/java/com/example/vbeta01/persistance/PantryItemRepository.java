package com.example.vbeta01.persistance;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vbeta01.model.PantryItem;

public interface PantryItemRepository extends JpaRepository<PantryItem, Long> {
    List<PantryItem> findByUserId(Long userId);
    List<PantryItem> findByExpirationDate(LocalDate expirationDate);
    List<PantryItem> findByIngredientName(String name);
}
