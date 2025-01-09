package com.app.services.implementation;

import com.app.services.TokenBlacklistService;
import com.app.util.jwt.Jwt;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class TokenBlacklistServiceImpl implements TokenBlacklistService {
    private final RedisTemplate<String, String> redisTemplate;
    private final Jwt jwtUtil;

    @Override
    public void blacklistToken(String token) {
        try {
            Claims claims = jwtUtil.getAllClaimsFromToken(token);
            Date expirationDate = claims.getExpiration();

            // Calculate time until expiration
            long ttl = (expirationDate.getTime() - System.currentTimeMillis()) / 1000;

            // Only blacklist if token hasn't expired yet
            if (ttl > 0) {
                redisTemplate.opsForValue()
                        .set("blacklisted_" + token, "true", ttl, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            log.error("Error blacklisting token: {}", e.getMessage());
            throw new RuntimeException("Error blacklisting token", e);
        }
    }
    @Override
    public boolean isTokenBlacklisted(String token) {
        return Boolean.TRUE.equals(redisTemplate.hasKey("blacklisted_" + token));
    }
}
