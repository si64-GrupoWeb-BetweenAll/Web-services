package main.pe.com.betweenAll.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class DTOUserCategorySummary {
    private String nameUser;
    private String nameCategory;
    private Long idUser;
    private Long idCategory;
}
