package com.springsecurity.model;

import com.springsecurity.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull(message = "Username cannot be empty")
    private String username;
    @NotNull(message = "Password cannot be null")
    @Pattern(
            regexp = "^(?!.*(.)\\1)[A-Z](?=.*[a-z])(?=.*\\d).{9,}$",
            message = "Password must be at least 10 characters long, start with a capital letter, contain at least one lowercase letter, one number, and no consecutive repeating characters."
    )
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}
