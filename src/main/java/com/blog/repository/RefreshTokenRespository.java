package com.blog.repository;

import com.blog.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRespository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByUserId(Long uerId);
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
