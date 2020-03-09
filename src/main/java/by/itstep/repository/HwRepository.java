package by.itstep.repository;

import by.itstep.model.HW;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HwRepository extends CrudRepository<HW, Long> {

    List<HW> findByTitle(String title);

}