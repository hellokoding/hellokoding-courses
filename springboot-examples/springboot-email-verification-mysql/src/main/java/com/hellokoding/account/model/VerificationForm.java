package com.hellokoding.account.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class VerificationForm {
    @NotEmpty
    @Email
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
