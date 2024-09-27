package com.springdatajpa.springboot.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String imageUrl;
    private BigDecimal price;
    private int quantity;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", imageUrl='" + imageUrl + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    // Bidirectional mapping
    // default fetch type of *ToOne is EAGER.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id", referencedColumnName = "id")
    private Order order;

    /**
     *     In a relational database, the foreign key is generally placed in the table that represents the "many" side of the relationship.
     *     In this case, the OrderItem entity (child) will store the foreign key (order_id) that refers to the Order entity (parent).
     *
     *     In a bidirectional relationship, only one side can be the owning side, and the other is the inverse side.
     */

    /**
     * The foreign key always belongs in the child table (order_items) because each OrderItem needs to reference its parent Order.
     * The @JoinColumn is placed on the ManyToOne side (OrderItem) because this side manages the foreign key in the database schema.
     * The OneToMany side in Order is the inverse side of the relationship and does not directly handle the foreign key.
     */


}
