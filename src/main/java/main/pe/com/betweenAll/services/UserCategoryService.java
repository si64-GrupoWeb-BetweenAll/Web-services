package main.pe.com.betweenAll.services;

import main.pe.com.betweenAll.dtos.DTOUserCategorySummary;
import main.pe.com.betweenAll.entities.UserCategory;

import java.util.List;
public interface UserCategoryService {
    public UserCategory save(UserCategory userCategory);
    public void delete(Long id, boolean forced);
    public List<UserCategory> listAll();
    public List<DTOUserCategorySummary> listUserCategorySummary(Long id);

}
