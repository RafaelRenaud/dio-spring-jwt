package dio.dio.spring.jwt.controller;

import dio.dio.spring.jwt.model.dto.LoginDTO;
import dio.dio.spring.jwt.model.dto.SessionDTO;
import dio.dio.spring.jwt.model.entity.User;
import dio.dio.spring.jwt.security.JWTCreator;
import dio.dio.spring.jwt.security.JWTObject;
import dio.dio.spring.jwt.security.SecurityConfig;
import dio.dio.spring.jwt.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class LoginController {
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private UserServiceImpl service;

    @PostMapping("/login")
    public SessionDTO login(@RequestBody LoginDTO login){
        User user = service.findUser(login.getUsername());

        if(user != null){
            boolean passwordValid = encoder.matches(login.getPassword(), user.getPassword());

            if(!passwordValid){
                throw new RuntimeException("Senha inválida para o login informado!");
            }

            SessionDTO session = new SessionDTO();
            session.setLogin(user.getUsername());

            JWTObject jwtObject = new JWTObject();
            jwtObject.setIssuedAt(new Date(System.currentTimeMillis()));
            jwtObject.setExpiration(new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION));
            jwtObject.setRoles(user.getRole());

            session.setToken(JWTCreator.create(SecurityConfig.PREFIX, SecurityConfig.KEY, jwtObject));
            return session;
        }else{
            throw new RuntimeException("Erro ao tentar login!");
        }
    }
}
