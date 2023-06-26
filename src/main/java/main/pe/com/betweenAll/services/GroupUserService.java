package main.pe.com.betweenAll.services;

import main.pe.com.betweenAll.dtos.DTOGroupParticipantsSummary;
import main.pe.com.betweenAll.dtos.DTOGroupUserSummary;
import main.pe.com.betweenAll.entities.Group;
import main.pe.com.betweenAll.entities.GroupUser;

import java.util.List;

public interface GroupUserService {
    public GroupUser save(GroupUser groupUser, Long idGroup, Long idUser);
    public void delete(Long id, boolean forced);
    public List<GroupUser> listAll();
    public GroupUser listById(Long id);
    public List<DTOGroupUserSummary> listGroupUserSummary();
}
