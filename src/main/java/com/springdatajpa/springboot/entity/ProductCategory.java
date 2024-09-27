package com.springdatajpa.springboot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "product_categories")
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String categoryName;
    private String categoryDescription;

    /**
     * One ProductCategory have multiple products and Multiple products have one Category.
     *
     * mappedBy is always used on the inverse side of a bidirectional relationship to indicate
     * that this entity does not manage the foreign key column.
     *
     */

    @OneToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
    }, fetch = FetchType.LAZY, mappedBy = "category")
    private List<Product> products = new ArrayList<>();

    @Override
    public String toString() {
        return "ProductCategory{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", categoryDescription='" + categoryDescription + '\'' +
                '}';
    }
}
