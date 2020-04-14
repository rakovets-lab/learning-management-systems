package by.itstep.repository;

import by.itstep.model.HomeWork;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HomeWorkRepository extends CrudRepository<HomeWork, Long> {
    List<HomeWork> findByTitle(String title);
}