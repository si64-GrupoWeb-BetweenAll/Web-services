package main.pe.com.betweenAll.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DTOGroupSummary {
    private String nameGroup;
    private String nameCategory;
    private Integer countUsers;
}
