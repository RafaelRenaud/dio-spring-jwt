package dio.dio.spring.jwt.security;

import dio.dio.spring.jwt.model.type.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class JWTObject {

    private String subject;
    private Date issuedAt;
    private Date expiration;
    private List<UserRole> roles;
}
