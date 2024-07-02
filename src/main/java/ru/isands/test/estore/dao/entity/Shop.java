package ru.isands.test.estore.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "shop")
public class Shop {

    /**
     * Идентификатор магазина
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    Long id;

    /**
     * Наименование магазина
     */
    @Column(length = 150, nullable = false)
    private String name;

    /**
     * Адрес магазина
     */
    @Column(nullable = false, columnDefinition = "text")
    private String address;
}