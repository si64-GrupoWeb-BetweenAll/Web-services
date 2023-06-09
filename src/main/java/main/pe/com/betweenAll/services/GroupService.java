package main.pe.com.betweenAll.services;

import main.pe.com.betweenAll.dtos.DTOGroupParticipantsSummary;
import main.pe.com.betweenAll.dtos.DTOGroupSummary;
import main.pe.com.betweenAll.dtos.DTOGroupsJoinnedSummary;
import main.pe.com.betweenAll.entities.Group;

import java.util.List;

public interface GroupService {
    public List<Group> listByName(String name);
    public Group listById(Long id);
    public List<Group> listAll();
    public Group save(Group group, Long idUser, Long idCategory);
    public void delete(Long id,boolean forced);
    public List<DTOGroupSummary> listGroupSummary();

    public List<DTOGroupsJoinnedSummary> listGroupByUserSummary(Long id);

    public  List<DTOGroupParticipantsSummary> listGroupParticipantsSummary();
    public  DTOGroupParticipantsSummary groupParticipantsSummary(Long id);

}
