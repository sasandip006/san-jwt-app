package com.example.demo.service;

import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {


    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        return creatToken(claims, userName);
    }

    private String creatToken(Map<String, Object> claims, String userName) {
        return Jwts.builder().issuer("Sandeep").subject("sandh").claim("Name", "Sandeep") // subject should be match
                                                                                          // with username
                .issuedAt(Date.from(Instant.ofEpochMilli(398573535))).claims(claims)
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256,
                        java.util.Base64.getUrlDecoder().decode("Yn2kjibddFAWtnPJ2AF1L8WXmohJMCvigQggaEypa5E="))
                .compact();
    }


//    return Jwts.builder().setIssuer("WORLDLINE").setSubject("WORLD").claim("name", "sandeep andhale").claim("scope", "admins").setIssuedAt(Date.from(Instant.of EpochSecond (123465443)))
//    expiration (Date. from(Instant.ofEpochSecond (5845334))) ---not required 
//    setClaims (claims).setExpiration (new Date(System.currentTimeMillis() 1000 60 60 10))
//   signWith(generalKey(), SignatureAlgorithm. HS256) signWith(SignatureAlgorithm. HS256,
//    Base64.getUrlDecoder().decode("Yn2kjibddFAWtnP32AF1L8WXmoh JMCvigQggaEypa5E="))
//   TextCodec.BASE64.decode("Yn2kjibddFAWtnP12AF118WXmoh JMCvigoggaEypa5E=");
//   parserBuilder().setSigningkey (Keys.hmacShakeyFor("jgjfjh".getBytes())).build().parseClaimsJws(token);
//    String username claimsJws.getBody().getSubject();
    //builder().setClaims (claims).setSubject(userllame).setIssuedAt (new Date(System.currentTimeMillis()));

 public static SecretKey generalKey() { 
     byte[] encodekey=Decoders.BASE64.decode("sandeep6547andhaleHHsandeep6547andhaleHHsandeep6547andhaleHHsandeep6547andhaleHHsandeep6547andhale");// Base64.getDecoder().decode("");
    return Keys.hmacShaKeyFor(encodekey);
    }

    public Boolean validateTocken(String token, UserDetails details) {
        final String username = extractUserName(token);
        return (username.equals(details.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> calimResolver) {
        final Claims claim = extractAllClaims(token);
        return calimResolver.apply(claim);
    }

    private Claims extractAllClaims(String token) {
//     return Jwts.parser().verifyWith(generalKey()).build().parseUnsecuredClaims(token).getPayload();
        return Jwts.parser()
                .setSigningKey(Base64.getUrlDecoder().decode("Yn2kjibddFAWtnPJ2AF1L8WXmohJMCvigQggaEypa5E=")).build()
                .parseClaimsJws(token).getBody();

    }

}
