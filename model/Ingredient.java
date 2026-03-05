package com.example.vbeta01.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table (name = "INGREDIENTS")
public class Ingredient {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Double amount;
    private String unit;

    @Column(name = "GROUP_NAME")
    @Enumerated
    private Group group;

    public Ingredient() {}

    public Ingredient(String n, Double a, String u, Group g)  {
        this.name = n;
        this.amount = a;
        this.unit = u;
        this.group = g;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

        public void setName(String name) {
            this.name = name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Group getGroup() {
        return this.group;
    }

    public void setGroup(Group x) {
        this.group = x;
    }
}
