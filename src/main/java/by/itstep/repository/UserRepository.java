package by.itstep.repository;

import by.itstep.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
    User findByUsername(String login);

    User findByActivationCode(String code);
}

