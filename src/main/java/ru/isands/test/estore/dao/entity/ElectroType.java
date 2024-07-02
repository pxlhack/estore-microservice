package ru.isands.test.estore.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "electro_type")
public class ElectroType {

    /**
     * Идентификатор типа электроники
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    Long id;

    /**
     * Наименование типа электроники
     */
    @Column(length = 150, nullable = false)
    private String name;
}