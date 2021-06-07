package by.itstep.lms.repository;

import by.itstep.lms.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, String> {
    Image findFirstByName(String name);
    Image findFirstById(int id);
}
