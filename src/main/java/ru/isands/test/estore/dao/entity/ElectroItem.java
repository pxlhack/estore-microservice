
package ru.isands.test.estore.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "electro_item")
public class ElectroItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Идентификатор электротовара
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    /**
     * Наименование товара
     */
    @Column(name = "name", length = 150, nullable = false)
    private String name;

    /**
     * Тип товара
     */
    @ManyToOne
    @JoinColumn(name = "electro_type_id", nullable = false)
    private ElectroType electroType;

    /**
     * Цена в рублях
     */
    @Column(name = "price", nullable = false)
    private Long price;

    /**
     * Количество
     */
    @Column(name = "count", nullable = false)
    private int count;

    /**
     * Признак архивности товара
     */
    @Column(name = "archive", nullable = false)
    private boolean archive;

    /**
     * Описание товара
     */
    @Column(name = "description", nullable = false, columnDefinition = "text")
    private String description;
}
