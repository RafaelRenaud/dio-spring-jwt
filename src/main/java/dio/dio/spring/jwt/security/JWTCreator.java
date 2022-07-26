package dio.dio.spring.jwt.security;

import dio.dio.spring.jwt.model.type.UserRole;
import io.jsonwebtoken.*;

import java.util.List;
import java.util.stream.Collectors;

public class JWTCreator {

    public static final String AUTH_HEADER = "Authorization";
    public static final String ROLES_AUTHORITIES = "authorities";

    public static String create(String prefix, String key, JWTObject jwtObject){
        String token = Jwts.builder().setSubject(jwtObject.getSubject())
                .setIssuedAt(jwtObject.getIssuedAt())
                .setExpiration(jwtObject.getExpiration())
                .claim(ROLES_AUTHORITIES, checkRoles(jwtObject.getRoles()))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();

        return prefix + " " + token;
    }

    public static JWTObject create(String token, String prefix, String key)
            throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException {
        JWTObject obj = new JWTObject();
        token = token.replace(prefix, "");
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();

        obj.setSubject(claims.getSubject());
        obj.setExpiration(claims.getExpiration());
        obj.setIssuedAt(claims.getIssuedAt());
        obj.setRoles((List<UserRole>) claims.get(ROLES_AUTHORITIES));

        return obj;
    }

    private static List<String> checkRoles(List<UserRole> roles){
        return roles.stream().map(s ->
                "ROLE_".concat(s.name().replaceAll("ROLE_", "")))
                .collect(Collectors.toList());
    }
}
