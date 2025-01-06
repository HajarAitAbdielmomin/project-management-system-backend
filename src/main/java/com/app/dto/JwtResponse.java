package com.app.dto;

import lombok.*;
import java.util.List;

@Setter
@Getter
@Builder
public class JwtResponse {
    private String token;
    @Builder.Default
    private String type = "Bearer";
    private Long id;
    private String email;
    private List<String> roles;
}
