package by.itstep.repository;

import by.itstep.model.Group;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GroupRepository extends CrudRepository<Group, Long> {
    Optional<Group> findByGroupId(Long id);
}
