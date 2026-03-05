package com.example.vbeta01.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;


@Entity
@Table (name = "INGREDIENTS")
@Data
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Ingredient {

    @EqualsAndHashCode.Exclude

    @Id
    @GeneratedValue
    Long id;
    String name;

    @Column(name = "GROUP_NAME")
    @Enumerated(EnumType.STRING)
    Group group;
}
