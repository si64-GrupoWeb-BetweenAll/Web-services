package main.pe.com.betweenAll.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import main.pe.com.betweenAll.entities.GroupUser;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@AllArgsConstructor
public class DTOGroupParticipantsSummary {
    private String nameGroup;
    private Integer amountParticipants;
    private String descriptionGroup;
    private String categoryGroup;

    @OneToMany(mappedBy = "dtoGroupParticipantsSummary",  cascade = {CascadeType.REMOVE})
    List<GroupUser> GroupUserList;
}
