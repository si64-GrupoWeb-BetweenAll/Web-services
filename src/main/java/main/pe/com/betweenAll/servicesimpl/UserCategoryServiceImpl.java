package main.pe.com.betweenAll.servicesimpl;

import main.pe.com.betweenAll.dtos.DTOUserCategorySummary;
import main.pe.com.betweenAll.entities.Category;
import main.pe.com.betweenAll.entities.Ticket;
import main.pe.com.betweenAll.entities.User;
import main.pe.com.betweenAll.entities.UserCategory;
import main.pe.com.betweenAll.repositories.CategoryRepository;
import main.pe.com.betweenAll.repositories.UserCategoryRepository;
import main.pe.com.betweenAll.repositories.UserRepository;
import main.pe.com.betweenAll.services.UserCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserCategoryServiceImpl implements UserCategoryService {
    @Autowired
    UserCategoryRepository userCategoryRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    UserRepository userRepository;

    @Transactional
    public UserCategory save(UserCategory userCategory,Long idUser, Long idCategory){

        User user = userRepository.findById(idUser).get();
        Category category= categoryRepository.findById(idCategory).get();
        userCategory.setUser(user);
        userCategory.setCategory(category);
        UserCategory newUserCategory=userCategoryRepository.save(userCategory);
        return newUserCategory;
    }
    @Transactional
    public void delete(Long id, boolean forced){
        UserCategory userCategory=userCategoryRepository.findById(id).get();
        userCategoryRepository.delete(userCategory);
    }
    @Transactional
    public List<UserCategory> listAll() {
        List<UserCategory> userCategoryList = userCategoryRepository.findAll();
        for(UserCategory s: userCategoryList){
            s.getUser().setSocialEventList(null);
            s.getUser().setGroupUserList(null);
            s.getUser().setPurchaseList(null);
            s.getUser().setUserCategoryList(null);
            s.getUser().setAuthorityList(null);
            s.getUser().setCardList(null);
            s.getCategory().setGroupList(null);
            s.getCategory().setSocialEventList(null);
            s.getCategory().setUserCategoryList(null);
        }
        return userCategoryList;
    }
    @Transactional
    public List<DTOUserCategorySummary> listUserCategorySummary(){
        List<UserCategory>userCategoryList=userCategoryRepository.findAll();
        List<DTOUserCategorySummary> dtoUserCategorySummaryList = new ArrayList<>();

        for(UserCategory uC: userCategoryList) {
            DTOUserCategorySummary dtoUserCategorySummary= new DTOUserCategorySummary(uC.getUser().getName(),
                    uC.getCategory().getName(), uC.getUser().getId(), uC.getCategory().getId());
            dtoUserCategorySummaryList.add(dtoUserCategorySummary);
        }
        return dtoUserCategorySummaryList;
    }


}
