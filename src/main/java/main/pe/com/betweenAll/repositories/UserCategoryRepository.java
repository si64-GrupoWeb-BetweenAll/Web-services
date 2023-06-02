package main.pe.com.betweenAll.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import main.pe.com.betweenAll.entities.UserCategory;

import java.util.List;

@Repository

public interface UserCategoryRepository extends JpaRepository<UserCategory, Long> {
    List<UserCategory> findByUser_Id(Long id);
    List<UserCategory> findByCategory_Id(Long id);
}
