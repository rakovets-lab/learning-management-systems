package by.itstep.lms.repository;

import by.itstep.lms.entity.Group;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GroupRepository extends CrudRepository<Group, Long> {
    Optional<Group> findByGroupId(Long id);
}
