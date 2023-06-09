package main.pe.com.betweenAll.services;

import main.pe.com.betweenAll.entities.Card;
import main.pe.com.betweenAll.entities.Category;

import javax.swing.*;
import java.util.List;

public interface CategoryService {
    public List<Category> listAll();
    public List<Category> listByName(String name);
    public Category listById(Long id);
    public Category save(Category category);
    public void delete(Long id, boolean forced);
}
