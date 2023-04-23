package by.georgprog.epicmusicstore.service.jwt;

import by.georgprog.epicmusicstore.dto.AuthRequestDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {

    private ExceptionHandlerExceptionResolver exceptionResolver;

    @Override
    public String generateToken(AuthRequestDto dto) {
        return generateToken(new HashMap<>(), dto);
    }

    @Override
    public String generateToken(Map<String, Object> extraClaims, AuthRequestDto dto) {
        return Jwts.builder()
                .setHeader(JwtProperties.getHeaderParameters())
                .setClaims(extraClaims)
                .setSubject(dto.getPrincipal())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(JwtProperties.getExpirationTime())
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public boolean isTokenValid(String token) {
        Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);
        return true;
    }

    @Override
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(JwtProperties.getSecretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
