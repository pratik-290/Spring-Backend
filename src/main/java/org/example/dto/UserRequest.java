package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.intellij.lang.annotations.JdkConstants;

public class UserRequest {

    private String username;
    private String password;
    private String role;

    @NotBlank(message = "Username cannot be empty")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6, message = "Password must be at least 6 characters")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotBlank(message = "Role cannot be empty")
    @Pattern(
            regexp = "USER|ADMIN",
            message = "Role must be USER or ADMIN"
    )
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}