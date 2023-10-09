package com.hoon.cashcocoon.config.jwt;

import com.hoon.cashcocoon.application.dto.MemberDto;
import com.hoon.cashcocoon.domain.member.Member;
import com.hoon.cashcocoon.domain.member.Role;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Slf4j
public class TokenProvider {

    @Value("${jwt.period}")
    private long ACCESS_TOKEN_VALID_PERIOD;
    private final Key jwtSecretKey;
    private final UserDetailsServiceImpl UserDetailsService;

    public TokenProvider(@Value("${jwt.secret-key}") String secretKey, UserDetailsServiceImpl UserDetailsService) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.jwtSecretKey = Keys.hmacShaKeyFor(keyBytes);
        this.UserDetailsService = UserDetailsService;
    }

    public TokenResponse generateJWT(final MemberDto memberDto) {
        final Date now = new Date();
        final Date accessTokenExpireIn = new Date(now.getTime() + ACCESS_TOKEN_VALID_PERIOD);
        final String accessToken = Jwts.builder()
                .setSubject("authorization")
                .claim("idx", memberDto.getIdx())
                .claim("email", memberDto.getEmail())
                .claim("roles", memberDto.getRoles())
                .setExpiration(accessTokenExpireIn)
                .signWith(jwtSecretKey, SignatureAlgorithm.HS256)
                .compact();

        return TokenResponse.of(accessToken, accessTokenExpireIn.getTime());
    }

    public boolean validateToken(final String token) {
        try {
            Jwts.parserBuilder().setSigningKey(jwtSecretKey).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            throw new IllegalArgumentException("잘못된 JWT 서명입니다." + e.getClass().getName());
        } catch (ExpiredJwtException e) {
            throw new IllegalArgumentException("만료된 JWT 토큰입니다." + e.getClass().getName());
        } catch (UnsupportedJwtException e) {
            throw new IllegalArgumentException("지원되지 않는 JWT 토큰입니다." + e.getClass().getName());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("JWT 토큰이 잘못되었습니다." + e.getClass().getName());
        }
    }

    public Claims parseClaims(final String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(jwtSecretKey).build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    public Authentication getAuthentication(final String token) {
        Claims claims = parseClaims(token);
        List<String> roleStrings = (List<String>) claims.get("roles");
        List<SimpleGrantedAuthority> authorities = roleStrings.stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
        final MemberDto member = MemberDto.builder()
                .idx(Long.parseLong(claims.get("idx").toString()))
                .email(claims.get("email").toString())
                .build();
        return new UsernamePasswordAuthenticationToken(member, null, authorities);
    }
}