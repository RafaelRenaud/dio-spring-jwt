package dio.dio.spring.jwt.repository;

import dio.dio.spring.jwt.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(@Param("username") String username);

    boolean existsByUsername(String username);

}
