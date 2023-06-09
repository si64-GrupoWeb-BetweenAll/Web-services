package main.pe.com.betweenAll.repositories;

import main.pe.com.betweenAll.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group,Long> {

    Group findByname(String name);
    List<Group> findByCategory_Id(Long id);
    Group findBydescription(String description);
    Group findByimage(String image);

    List<Group> findBynameContaining(String name);
}
