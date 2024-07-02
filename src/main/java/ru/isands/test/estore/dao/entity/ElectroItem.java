
package ru.isands.test.estore.dao.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class ElectroItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Идентификатор электротовара
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_", unique = true, nullable = false)
    Long id;

    /**
     * Наименование товара
     */
    @Column(length = 150, nullable = false)
    String name;

    /**
     * Тип товара
     */
    @Column(name = "etypeId", nullable = false)
    Long etypeId;

    /**
     * Цена в рублях
     */
    @Column(name = "price", nullable = false)
    Long price;

    /**
     * Количество
     */
    @Column(name = "count", nullable = false)
    int count;

    /**
     * Признак архивности товара
     */
    @Column(name = "archive", nullable = false)
    boolean archive;

    /**
     * Описание товара
     */
    @Column(nullable = false, columnDefinition = "text")
    String description;
}
