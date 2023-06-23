package main.pe.com.betweenAll.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DTOGroupUserSummary {
    private String nameGroup;
    private String nameUser;
    private Long idGroup;
    private Long idUser;
}
