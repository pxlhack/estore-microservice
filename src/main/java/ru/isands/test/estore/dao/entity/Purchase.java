package ru.isands.test.estore.dao.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "store_purchase")
public class Purchase implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Идентификатор покупки
     */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "purchase_counter")
    @TableGenerator(name = "purchase_counter", pkColumnName = "name", pkColumnValue = "ru.isands.test.estore.dao.entity.Purchase", table = "counter", valueColumnName = "currentid", allocationSize = 1)
    @Column(name = "id_", unique = true, nullable = false)
    Long id;

    /**
     * Идентификатор товара
     */
    @Column(name = "electroId", nullable = false)
    Long electroId;

    /**
     * Идентификатор сотрудника
     */
    @Column(name = "employeeId", nullable = false)
    Long employeeId;

    /**
     * Идентификатор магазина
     */
    @Column(name = "shopId", nullable = false)
    Long shopId;

    /**
     * Дата совершения покупки
     */
    @Column(name = "purchaseDate", nullable = false)
    Date purchaseDate;

    /**
     * Тип покупки
     */
    @ManyToOne
    @JoinColumn(name = "purchase_type_id", nullable = false)
    private PurchaseType purchaseType;

}