package dio.dio.spring.jwt.model.entity;

import dio.dio.spring.jwt.model.type.UserRole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "`user`")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`", nullable = false)
    private Long id;

    @Column(name = "`name`", length = 50, nullable = false)
    private String name;

    @Column(name = "`username`", length = 64, nullable = false)
    private String username;

    @Column(name = "`password`", length = 100, nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "`roles`", nullable = false)
    private List<UserRole> role = new ArrayList<>();

}