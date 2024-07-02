package ru.isands.test.estore.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@IdClass(ElectroShopPK.class)
@Table(name = "store_eshop")
public class ElectroShop {

    /**
     * Идентификатор магазина
     */
    @Id
    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    /**
     * Идентификатор электротовара
     */
    @Id
    @ManyToOne
    @JoinColumn(name = "electro_item_id", nullable = false)
    private ElectroItem electroItem;

    /**
     * Оставшееся количество
     */
    @Column(name = "count_", nullable = false)
    int count;
}