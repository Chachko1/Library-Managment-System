package org.librarymanagementsystem.DTOs;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class LoginDTO {
    @NotBlank
    @Length(min = 3,max = 20)
    private String username;
    @NotBlank
    @Length(min = 3,max = 20)
    private String password;

    public LoginDTO() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
