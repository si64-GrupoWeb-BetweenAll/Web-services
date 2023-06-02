package main.pe.com.betweenAll.repositories;

import main.pe.com.betweenAll.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
