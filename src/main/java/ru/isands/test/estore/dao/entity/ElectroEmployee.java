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
    @ManyToOne
    @JoinColumn(name = "electro_type_id", nullable = false)
    private ElectroType electroType;

    /**
     * Идентификатор сотрудника
     */
    @Id
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
}