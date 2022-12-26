package com.tweetapp.util;

import io.jsonwebtoken.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtUtil {

    private static String secret="tweetApp";

    public String generateToken(UserDetails userProfile){
        Date expiryDate=new Date((new Date()).getTime()+1200000);
        Claims claims= Jwts.claims().setIssuer(userProfile.getUsername())
                .setIssuedAt(new Date()).setExpiration(expiryDate);
        return Jwts.builder().setClaims(claims).setSubject(userProfile.getUsername()).signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public boolean validateToken(String token){
        try {
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            if((getUserUsernameFromJWT(token).equals(claims.getSubject()))) {
                return true;
            }
        } catch (SignatureException ex) {
             throw new SignatureException ("Invalid JWT signature");
        } catch (MalformedJwtException e) {
            throw new MalformedJwtException("Invalid JWT token");
        } catch (ExpiredJwtException e) {
            throw new ExpiredJwtException(e.getHeader(),e.getClaims(),e.getLocalizedMessage());
        } catch (UnsupportedJwtException e) {
            throw new UnsupportedJwtException("JWT token is unsupported");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("JWT claims string is empty");
        }
        return false;
    }

    public String getUserUsernameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
