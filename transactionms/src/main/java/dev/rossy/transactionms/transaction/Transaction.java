package dev.rossy.transactionms.transaction;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@Data // Menggantikan getter, setter, toString, equals, dan hashCode
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private long recipeId; // Relasi dengan entitas Recipe

    private Integer quantity;

    private Double totalPrice;

    @Column(name = "transaction_date", nullable = false)
    private LocalDateTime transactionDate;

    private Integer customerId;
}
