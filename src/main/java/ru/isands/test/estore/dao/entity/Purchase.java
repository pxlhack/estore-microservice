package ru.isands.test.estore.dao.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "store_purchase")
public class Purchase implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Идентификатор покупки
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    Long id;

    /**
     * Идентификатор товара
     */
    @ManyToOne
    @JoinColumn(name = "electro_item_id", nullable = false)
    private ElectroItem electroItem;

    /**
     * Идентификатор сотрудника
     */
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    /**
     * Идентификатор магазина
     */
    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

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