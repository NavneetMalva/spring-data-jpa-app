package com.springdatajpa.springboot.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
/*
@NamedQuery(
        name="Product.findByPrice",
        query="SELECT p from Product p where p.price=?1"
)
*/

@NamedQueries(
        {
                @NamedQuery(
                        name = "Product.findAllOrderByNameDesc",
                        query = "SELECT p from Product p ORDER By p.name DESC"
                ),
                @NamedQuery(
                        name = "Product.findByPrice",
                        query = "SELECT p from Product p where p.price = :price"
                )
        }
)
/*
@NamedNativeQuery(
        name="Product.findByDescription",
        query="SELECT * from products p where p.description=?1",
        resultClass = Product.class
)
*/
@NamedNativeQueries(
        {
                @NamedNativeQuery(
                        name="Product.findByDescriptionNamedParam",
                        query="SELECT * from products p where p.description=:description",
                        resultClass = Product.class
                ),
                @NamedNativeQuery(
                        name = "Product.findAllOrderByNameASC",
                        query = "select * from products order by name asc",
                        resultClass = Product.class
                )

        }
)

@Table(
        name = "products",
        schema = "ecommerce",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "sku_unique",
                        columnNames = "stock_keeping_unit"
                )
        }
)
public class Product {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_generator"
    )
    @SequenceGenerator(
            name = "product_generator",
            sequenceName = "product_sequence_name",
            allocationSize = 1
    )
    private Long id;

    @Column(name = "stock_keeping_unit", nullable = false)
    private String sku;

    @Column(nullable = false)
    private String name;

    private String description;
    private BigDecimal price;
    private boolean active;
    private String imageUrl;

    @CreationTimestamp
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    /**
     * many side holds the foreign key
     * Owning side: The side that contains the @JoinColumn annotation (or where the foreign key is stored)
     * and controls the mapping of the relationship.
     * the Product entity (child side) would be the owning side of the relationship,
     * because it holds the foreign key that refers to Category
     */
    @ManyToOne()
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private ProductCategory category;

    // Using lombok library so we don't need to create getter & setters each time.
    /*
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    */

}
