package com.blog.service;

import com.blog.domain.RefreshToken;
import com.blog.repository.RefreshTokenRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRespository refreshTokenRespository;

    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRespository.findByRefreshToken(refreshToken)
                .orElseThrow(() ->
                        new IllegalArgumentException("Unexpected token"));
    }
}
