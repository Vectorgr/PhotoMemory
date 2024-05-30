package net.flashbacks.api.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static net.flashbacks.api.security.Const.TOKEN_EXPIRATION_TIME;
import static net.flashbacks.api.security.Const.SUPER_SECRET_KEY;

@Configuration
public class JWTAuthenticationConfig {
    private Key getSigningKey(String secretKey) {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String getJWTToken(String username, List<String> roles) {
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("espinozajgeJWT")
                .setSubject(username)
                .claim("roles",roles)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
                .signWith(getSigningKey(SUPER_SECRET_KEY),  SignatureAlgorithm.HS512).compact();

        return "Bearer " + token;
    }
}