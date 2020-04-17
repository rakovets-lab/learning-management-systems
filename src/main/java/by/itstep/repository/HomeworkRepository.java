package by.itstep.repository;

import by.itstep.model.jpa.Homework;
import org.springframework.data.repository.CrudRepository;

public interface HomeworkRepository extends CrudRepository<Homework, Long> {
}
