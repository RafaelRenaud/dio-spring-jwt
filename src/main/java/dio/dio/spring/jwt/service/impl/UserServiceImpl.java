package dio.dio.spring.jwt.service.impl;

import dio.dio.spring.jwt.model.entity.User;
import dio.dio.spring.jwt.repository.UserRepository;
import dio.dio.spring.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public User findUser(String username){
        return repository.findByUsername(username);
    }

    @Override
    public void createUser(User user) {
        String pass = user.getPassword();

        user.setPassword(encoder.encode(pass));
        repository.save(user);
    }
}
