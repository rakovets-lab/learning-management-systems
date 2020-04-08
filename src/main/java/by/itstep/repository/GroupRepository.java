package by.itstep.repository;

import by.itstep.model.Group;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GroupRepository extends CrudRepository<Group, Long> {
    @Query("select g from Group g where g.groupId=:id")
    Optional<Group> findByGroupId(@Param("id") Long id);
}
