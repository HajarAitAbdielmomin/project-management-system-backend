package com.app.services.implementation;

import com.app.services.TokenBlacklistService;
import com.app.util.jwt.Jwt;
import io.jsonwebtoken.Claims;
import io.lettuce.core.RedisConnectionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class TokenBlacklistServiceImpl implements TokenBlacklistService {
    private final RedisTemplate<String, String> redisTemplate;
    private final Jwt jwtUtil;

    @Autowired
    public TokenBlacklistServiceImpl(RedisTemplate<String, String> redisTemplate,@Lazy Jwt jwtUtil) {
        this.redisTemplate = redisTemplate;
        this.jwtUtil = jwtUtil;
    }
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

        }
        catch (RedisConnectionException e) {
        log.error("Redis connection error: {}", e.getMessage());
        throw new RuntimeException("Unable to connect to Redis", e);
        }
        catch (Exception e) {
            log.error("Error blacklisting token: {}", e.getMessage());
            throw new RuntimeException("Error blacklisting token", e);
        }
    }
    @Override
    public boolean isTokenBlacklisted(String token) {
        try {
            return Boolean.TRUE.equals(redisTemplate.hasKey("blacklisted_" + token));
        } catch (RedisConnectionException e) {
            log.error("Redis connection error: {}", e.getMessage());
            return false;
        }
    }
}
