package main.pe.com.betweenAll.services;

import main.pe.com.betweenAll.dtos.DTOGroupParticipantsSummary;
import main.pe.com.betweenAll.dtos.DTOGroupUserSummary;
import main.pe.com.betweenAll.entities.GroupUser;

import java.util.List;

public interface GroupUserService {
    public GroupUser save(GroupUser groupUser);
    public void delete(Long id, boolean forced);
    public List<GroupUser> listAll();

    public List<DTOGroupUserSummary> listGroupUserSummary();
    public  List<DTOGroupParticipantsSummary> listGroupParticipantsSummary();
}
