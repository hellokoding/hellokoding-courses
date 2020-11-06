package com.hellokoding.jpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
@IdClass(EmployeePhoneId.class)
public class EmployeePhone {
    @ManyToOne
    @PrimaryKeyJoinColumn
    private Employee employee;

    @Id
    private String phone;

    private Boolean isPrimary;

    public EmployeePhone(String phone, Boolean isPrimary) {
        this.phone = phone;
        this.isPrimary = isPrimary;
    }
}
