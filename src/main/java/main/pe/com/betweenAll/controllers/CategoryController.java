package main.pe.com.betweenAll.controllers;

import main.pe.com.betweenAll.entities.Category;
import main.pe.com.betweenAll.entities.Purchase;
import main.pe.com.betweenAll.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @GetMapping("/categorys")
    public ResponseEntity<List<Category>>getAllCategorys(){
        List<Category> categories = categoryService.listAll();
        return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
    }
    @GetMapping("/categorys/{id}")
    public ResponseEntity<Category>getCategoryById(@PathVariable("id")Long id){
        Category categories = categoryService.listById(id);
        return new ResponseEntity<Category>(categories, HttpStatus.OK);
    }
    @PostMapping("/categorys")
    public ResponseEntity<Category> createCategory (@RequestBody Category category) {
        Category newCategory = categoryService.save(category);
        return new ResponseEntity<Category>(newCategory, HttpStatus.CREATED);
    }
}
