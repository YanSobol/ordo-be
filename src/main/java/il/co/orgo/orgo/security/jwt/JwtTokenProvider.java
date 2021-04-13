package il.co.orgo.orgo.security.jwt;


import il.co.orgo.orgo.model.Role;
import il.co.orgo.orgo.model.User;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

// Jwt token provider: class for generation of jwt tokens
@Component
public class JwtTokenProvider {

    @Value("${jwt.token.secret}")
    private String encryption;

    @Value("${jwt.token.expired}")
    private Long validityInMilliseconds;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @PostConstruct
    protected void init(){
        encryption = Base64.getEncoder().encodeToString(encryption.getBytes());
    }

    public String createToken(User user){

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] encryptionByteKey = DatatypeConverter.parseBase64Binary(encryption);
        Key signingKey = new SecretKeySpec(encryptionByteKey,signatureAlgorithm.getJcaName());

        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("roles", getRolesNames(user.getRoles()));
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public Authentication getAuthentication(String token){
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities());
    }

    private String getUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(encryption).build().parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if(bearerToken != null && bearerToken.startsWith("Bearer_")){
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    public boolean validateToken(String token){

        try{
            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(encryption).build().parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date());
        }
        catch (JwtException | IllegalArgumentException exception){
            throw new JwtAuthenticationException("JWT token expired or invalid");
        }

    }

    private List<String> getRolesNames(List<Role> userRoles){
        List<String> roleNames = new ArrayList<>();

        userRoles.forEach(role -> {
            roleNames.add(role.getName());
        });
        return roleNames;
    }
}
