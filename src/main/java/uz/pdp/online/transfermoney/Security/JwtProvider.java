package uz.pdp.online.transfermoney.Security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component  //Bean qilish uchun
public class JwtProvider {
    long expireTime = 36_000_000;  // set time to user for logged in system
    String keyWord = "Bu tokenning maxfiy so`zi hech kim bilmasin.Bu so`z ham keyinchalik generatsiya qilinadi";

    public String generateToken(String username) {
        Date expireDate = new Date(System.currentTimeMillis() + expireTime);

        String token = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, keyWord)
                .compact();
        System.out.println(token);
        return token;
    }

    public String getUsernameFromToken(String token) {
        String username = Jwts
                .parser()
                .setSigningKey(keyWord)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return username;
    }

    public boolean validateToken(String token) {
        try {
            Jwts
                    .parser()
                    .setSigningKey(keyWord)
                    .parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            System.err.println("Invalid JWT signature");
        } catch (MalformedJwtException e) {
            System.err.println("Invalid JWT token");
        } catch (ExpiredJwtException e) {
            System.err.println("Expired JWT token");
        } catch (UnsupportedJwtException e) {
            System.err.println("Unsupported JWT token");
        } catch (IllegalArgumentException e) {
            System.err.println("JWT calims is empty");
        }
        return false;
    }
}
