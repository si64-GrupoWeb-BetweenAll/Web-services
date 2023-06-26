package main.pe.com.betweenAll.servicesimpl;

import main.pe.com.betweenAll.entities.*;
import main.pe.com.betweenAll.exceptions.IncompleteDataException;
import main.pe.com.betweenAll.exceptions.KeyRepeatedDataException;
import main.pe.com.betweenAll.exceptions.ResourceNotFoundException;
import main.pe.com.betweenAll.repositories.*;
import main.pe.com.betweenAll.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    GroupUserRepository groupUserRepository;

    @Autowired
    UserCategoryRepository userCategoryRepository;

    @Autowired
    SocialEventRepository socialEventRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CardRepository cardRepository;

    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    AuthorityRepository authorityRepository;

    public List<User> listAll(){
        List<User> users;
        users = userRepository.findAll();
        for(User u : users) {
            u.setGroupUserList(null);
            u.setUserCategoryList(null);
            u.setSocialEventList(null);
            u.setCardList(null);
            u.setPurchaseList(null);
            for(Authority a: u.getAuthorityList()){
                a.setUsers(null);
            }
            u.setGroupUserList(null);
        }
        return users;
    }

    public List<User> listByName(String name){
        List<User> users;
        users=userRepository.findBynameContaining(name);
        for(User u: users) {
            u.setGroupUserList(null);
            u.setUserCategoryList(null);
            u.setSocialEventList(null);
            u.setCardList(null);
            u.setPurchaseList(null);
        }
        return users;
    }

    public User listById(Long id) {
        User user;
        user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Not found an User with id="+id));
        user.setGroupUserList(null);
        user.setUserCategoryList(null);
        user.setSocialEventList(null);
        user.setCardList(null);
        user.setPurchaseList(null);
        return user;
    }

    @Transactional
    public User save(User user) {

        /*if (user.getName() == null || user.getName().isEmpty()) {
            throw new IncompleteDataException("User Name can not be null or empty");
        }

        if (user.getLastname() == null || user.getLastname().isEmpty()) {
            throw new IncompleteDataException("User Last Name can not be null or empty");
        }

        if (user.getTypeDocument() == null || user.getTypeDocument().isEmpty()) {
            throw new IncompleteDataException("User TypeDocument can not be null or empty");
        }

        if (user.getNumberDocument() == null || user.getNumberDocument() == 0) {
            throw new IncompleteDataException("User NumberDocument can not be null or empty");
        }

        if (user.getPhone() == null || user.getPhone().isEmpty()) {
            throw new IncompleteDataException("User Phone can not be null or empty");
        }

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IncompleteDataException("User Email can not be null or empty");
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IncompleteDataException("User Password can not be null or empty");
        }

        if (user.getCity() == null || user.getCity().isEmpty()) {
            throw new IncompleteDataException("User City can not be null or empty");
        }

        if (user.getId()==null || user.getId()==0) {
            if (userRepository.findByNumberDocument(user.getNumberDocument()) != null) {
                throw new KeyRepeatedDataException("Value for NumberDocument is duplicated");
            }
        }



        //User newUser = new User(user.getName(), user.getLastname(), user.getTypeDocument(), user.getNumberDocument(), user.getPhone(), user.getEmail(), user.getPassword(), user.getImage(), user.getCity());

        User savedUser = userRepository.save(user);*/

        User newUser = new User(user.getName(), user.getLastname(), user.getTypeDocument(),
                user.getNumberDocument(), user.getPhone(), user.getEmail(),
                new BCryptPasswordEncoder().encode(user.getPassword()),
                user.getImage(), user.getCity(),List.of(
                authorityRepository.findByName(AuthorityName.ROLE_ADMIN)
        ));
        User savedUser = userRepository.save(newUser);
        return savedUser;
    }

    @Transactional
    public void delete(Long id, boolean forced) {
        User user = userRepository.findById(id).get();
        if (forced) {
            List<GroupUser> groupUserList =  groupUserRepository.findByUser_Id(id);
            for(GroupUser gu : groupUserList) {
                groupUserRepository.delete(gu);
            }
            List<UserCategory> userCategoryList = userCategoryRepository.findByUser_Id(id);
            for(UserCategory uc : userCategoryList) {
                userCategoryRepository.delete(uc);
            }
            List<SocialEvent> socialEventList = socialEventRepository.findByUser_Id(id);
            for(SocialEvent se : socialEventList) {
                socialEventRepository.delete(se);
            }
            List<Card> cardList = cardRepository.findByUser_Id(id);
            for(Card c : cardList) {
                cardRepository.delete(c);
            }
            List<Purchase> purchaseList = purchaseRepository.findByUser_Id(id);
            for(Purchase p : purchaseList) {
                purchaseRepository.delete(p);
            }
        }
        userRepository.delete(user);
    }
}
