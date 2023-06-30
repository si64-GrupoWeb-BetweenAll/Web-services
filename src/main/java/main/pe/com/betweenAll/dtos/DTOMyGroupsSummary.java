package main.pe.com.betweenAll.dtos;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DTOMyGroupsSummary {
    private String imageGroup;
    private String nameGroup;
    private Number amountParticipants;
    private String descriptionGroup;
    private String nameCategory;
}
