package ru.isands.test.estore.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "purchase_type")
public class PurchaseType {

    /**
     * Идентификатор типа покупки
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    /**
     * Наименование типа покупки
     */
    @Column(length = 150, nullable = false)
    private String name;
}