package main.pe.com.betweenAll.servicesimpl;

import main.pe.com.betweenAll.entities.Category;
import main.pe.com.betweenAll.entities.Group;
import main.pe.com.betweenAll.repositories.CategoryRepository;
import main.pe.com.betweenAll.repositories.GroupRepository;
import main.pe.com.betweenAll.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    GroupRepository groupRepository;

    public List<Category> listAll() {
        List<Category> categories;
        categories=categoryRepository.findAll();
        for(Category c: categories){
            c.setGroupList(null);
        }
        return categories;
    }
    public List<Category> listByName(String name) {
        List<Category> categories;
        categories=categoryRepository.findByNameContaining(name);
        for(Category c: categories){
            c.setGroupList(null);
        }
        return categories;
    }
    public Category listById(Long id) {
        Category categories;
        categories=categoryRepository.findById(id).get();
        categories.setGroupList(null);
        return categories;
    }
    public List<Category> listAllWithGroup(){
        List<Category> categories;
        categories=categoryRepository.findAll();
        return categories;
    }
    @Transactional
    public Category save(Category category){
        Category newCategory = categoryRepository.save(new Category(category.getName(), category.getState()));
        return newCategory;
    }

    @Transactional
    public void delete(Long id, boolean forced){
        Category category = categoryRepository.findById(id).get();
        categoryRepository.delete(category);
    }
}