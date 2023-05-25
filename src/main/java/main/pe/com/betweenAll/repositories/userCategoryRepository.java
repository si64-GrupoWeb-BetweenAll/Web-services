package main.pe.com.betweenAll.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import main.pe.com.betweenAll.entities.userCategory;
@Repository

public interface userCategoryRepository extends JpaRepository<userCategory, Long> {
}
