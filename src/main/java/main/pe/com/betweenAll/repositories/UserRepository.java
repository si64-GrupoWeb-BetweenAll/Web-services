package main.pe.com.betweenAll.repositories;

import main.pe.com.betweenAll.entities.Group;
import main.pe.com.betweenAll.entities.GroupUser;
import main.pe.com.betweenAll.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {
    List<User> findBynameContaining(String name);
}
