package main.pe.com.betweenAll.repositories;

import main.pe.com.betweenAll.entities.Authority;
import main.pe.com.betweenAll.entities.AuthorityName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority,Long> {
    public Authority findByName(AuthorityName name);

}
