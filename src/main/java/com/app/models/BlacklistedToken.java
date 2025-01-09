package com.app.models;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.util.Date;

@RedisHash("blacklisted_token")
@Setter
@Getter
@RequiredArgsConstructor
public class BlacklistedToken {
    @Id
    private String token;
    private Date expirationDate;
}
