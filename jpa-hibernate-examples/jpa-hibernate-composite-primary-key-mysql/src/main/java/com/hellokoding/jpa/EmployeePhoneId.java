package com.hellokoding.jpa;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeePhoneId implements Serializable {
    private Employee employee;
    private String phone;

    public EmployeePhoneId() {

    }
}
