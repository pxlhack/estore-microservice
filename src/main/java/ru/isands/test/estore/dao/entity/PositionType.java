package ru.isands.test.estore.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "position_type")
public class PositionType {

    /**
     * Идентификатор должнонсти
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    Long id;

    /**
     * Наименование должности
     */
    @Column(length = 150, nullable = false)
    private String name;
}