package ru.isands.test.estore.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ElectroShopPK implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Идентификатор магазина
     */
    private Long shop;

    /**
     * Идентификатор электротовара
     */
    private Long electroItem;
}