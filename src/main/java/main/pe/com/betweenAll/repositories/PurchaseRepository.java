package main.pe.com.betweenAll.repositories;

import main.pe.com.betweenAll.entities.Card;
import main.pe.com.betweenAll.entities.Purchase;
import main.pe.com.betweenAll.entities.SocialEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository <Purchase, Long> {
    List<Purchase> findByUser_Id(Long id);

    List<Purchase> findByCard_Id(Long id);

    /*@Query(value="SELECT * FROM employees WHERE email=?1 AND age<?2", nativeQuery = true)
    List<Employee> findByCityAndLowerAge (String city, Integer age);*/
}
