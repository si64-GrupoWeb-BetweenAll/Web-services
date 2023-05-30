package main.pe.com.betweenAll.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import main.pe.com.betweenAll.entities.UserCategory;
@Repository

public interface UserCategoryRepository extends JpaRepository<UserCategory, Long> {
}
