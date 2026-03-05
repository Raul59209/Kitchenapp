package com.example.vbeta01.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class User {

    @EqualsAndHashCode.Exclude
    
    @Id
    @GeneratedValue(strategy=jakarta.persistence.GenerationType.IDENTITY)
    Long id;
    
    String username;
    String password;
    String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<PantryItem> pantryList = new ArrayList<>();
}
