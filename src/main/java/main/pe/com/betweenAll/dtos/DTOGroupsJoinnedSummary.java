package main.pe.com.betweenAll.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import main.pe.com.betweenAll.entities.Group;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@AllArgsConstructor
public class DTOGroupsJoinnedSummary {
    private Long idGroup;
    private String nameGroup;
    private String nameCategory;
    private String nameSocialEvent;
    private String dateSocialEvent;

}
