package by.georgprog.epicmusicstore.service.jwt;

import by.georgprog.epicmusicstore.dto.user.AuthUserRequest;
import io.jsonwebtoken.Claims;

import java.util.Map;
import java.util.function.Function;

public interface JwtService {

    String generateToken(AuthUserRequest authentication);

    String generateToken(Map<String, Object> extraClaims, AuthUserRequest authentication);

    boolean isTokenValid(String token);

    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);
}