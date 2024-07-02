package ru.isands.test.estore.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@IdClass(ElectroEmployeePK.class)
@Table(name = "electro_employee")
public class ElectroEmployee {

    /**
     * Идентификатор типа электроники
     */
    @Id
    @Column(name = "electroTypeId", nullable = false)
    Long electroTypeId;

    /**
     * Идентификатор сотрудника
     */
    @Id
    @Column(name = "employeeId", nullable = false)
    Long employeeId;
}