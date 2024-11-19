package com.ssafy.ssafyhome.util;

import com.ssafy.ssafyhome.domain.entity.Member;
import com.ssafy.ssafyhome.domain.enums.Role;
import com.ssafy.ssafyhome.exception.UnauthorizedException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class JWTUtil {

  private enum TokenType {
    ACCESS, REFRESH
  }

  @Value("${jwt.secret}")
  private String SECRET_KEY;

  @Value("${jwt.access-token.expiretime}")
  private long ACCESS_TOKEN_EXPIRE_TIME;

  @Value("${jwt.refresh-token.expiretime}")
  private long REFRESH_TOKEN_EXPIRE_TIME;

  public String createAccessToken(Member member) {
    return createToken(member, TokenType.ACCESS, ACCESS_TOKEN_EXPIRE_TIME);
  }

  public String createRefreshToken(Member member) {
    return createToken(member, TokenType.REFRESH, REFRESH_TOKEN_EXPIRE_TIME);
  }

  private String createToken(Member member, TokenType tokenType, long expireTime) {
    return Jwts.builder()
        .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
        .setSubject(String.valueOf(member.getId()))
        .claim("name", member.getName())
        .claim("role", member.getRole())
        .claim("token_type", tokenType)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + expireTime))
        .signWith(getSigningKey())
        .compact();
  }

  private Key getSigningKey() {
    byte[] keyBytes = SECRET_KEY.getBytes();
    return Keys.hmacShaKeyFor(keyBytes);
  }

  public boolean validateToken(String token) {
    if (token == null) return false;

    try {
      return Jwts.parserBuilder()
          .setSigningKey(getSigningKey())
          .build()
          .parseClaimsJws(token)
          .getBody()
          .getExpiration()
          .after(new Date());
    } catch (ExpiredJwtException e) {
      throw new UnauthorizedException("만료된 토큰입니다.");
    } catch (Exception e) {
      throw new UnauthorizedException("유효하지 않은 토큰입니다.");
    }
  }

  private Claims parseClaims(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(getSigningKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  public int getId(String token) {
    return Integer.parseInt(parseClaims(token).getSubject());
  }

  public String getEmail(String token) {
    return parseClaims(token).get("email", String.class);
  }

  public Role getRole(String token) {
    return parseClaims(token).get("role", Role.class);
  }
}