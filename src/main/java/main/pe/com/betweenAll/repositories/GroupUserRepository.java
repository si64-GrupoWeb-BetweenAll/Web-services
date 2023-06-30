package main.pe.com.betweenAll.repositories;

import main.pe.com.betweenAll.entities.GroupUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupUserRepository extends JpaRepository <GroupUser, Long> {
    List<GroupUser> findByUser_Id(Long id);
    List<GroupUser> findByGroup_Id(Long id);

    GroupUser findByUser_IdAndGroup_Id(Long idUser, Long idGroup);
}
