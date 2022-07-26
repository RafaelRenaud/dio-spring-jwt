package dio.dio.spring.jwt.service;

import dio.dio.spring.jwt.model.entity.User;

public interface UserService {
    User findUser(String username);

    void createUser(User user);
}
