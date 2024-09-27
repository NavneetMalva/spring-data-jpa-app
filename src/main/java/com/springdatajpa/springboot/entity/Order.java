package com.springdatajpa.springboot.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String orderTrackingNumber;
    private int totalQuantity;
    private BigDecimal totalPrice;
    private String status;

    @CreationTimestamp
    private LocalDateTime dateCreated;
    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderTrackingNumber='" + orderTrackingNumber + '\'' +
                ", totalQuantity=" + totalQuantity +
                ", totalPrice=" + totalPrice +
                ", status='" + status + '\'' +
                '}';
    }

    // This will be child side for OneToOne Bidirectional Mapping between Order and Address
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "order")
    private Address billingAddress;

    /*
    * default fetch type for one to many is LAZY
    * In a
    * @OneToMany relationship, by default, the foreign key is placed in the child table (OrderItem in this case).
    * Which results in the OrderItem table having a foreign key to the Order table (a column order_id in order_items).
    */

    /*
    * default Fetch types for Mapping
    * @OneToOne - EAGER
    * @ManyToOne - EAGER
    * @OneToMany - LAZY
    * @ManyToMany - LAZY
    * */

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="order_id", referencedColumnName = "id")
    private Set<OrderItem> orderItems = new HashSet<>();

    public BigDecimal getTotalAmount(){
        BigDecimal amount = new BigDecimal(0.0);
        for(OrderItem item: this.orderItems){
            amount = amount.add(item.getPrice());
        }
        return amount;
    }

}