package com.app.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.util.Set;

@Setter
@Getter
@Builder
public class SignupRequestDTO {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    private Set<String> role;

}
