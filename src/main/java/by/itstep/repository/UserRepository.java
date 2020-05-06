package by.itstep.repository;

import by.itstep.model.jpa.Group;
import by.itstep.model.jpa.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long>{
    User findByUsername(String login);

    User findByActivationCode(String code);

    Optional<User> findByUserId(Long userId);
}
