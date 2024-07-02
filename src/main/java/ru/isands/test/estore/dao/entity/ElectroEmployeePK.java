package ru.isands.test.estore.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ElectroEmployeePK implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Идентификатор типа электроники
     */
    Long electroTypeId;

    /**
     * Идентификатор сотрудника
     */
    Long employeeId;
}