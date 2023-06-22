package main.pe.com.betweenAll.servicesimpl;

import main.pe.com.betweenAll.entities.*;
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
        user = userRepository.findById(id).get();
        user.setGroupUserList(null);
        user.setUserCategoryList(null);
        user.setSocialEventList(null);
        user.setCardList(null);
        user.setPurchaseList(null);
        return user;
    }

    @Transactional
    public User save(User user) {
        User newUser = new User(user.getName(), user.getLastname(), user.getTypeDocument(),
                user.getNumberDocument(), user.getPhone(), user.getEmail(),
                new BCryptPasswordEncoder().encode(user.getPassword()),
                user.getImage(), user.getCity(),List.of(
                authorityRepository.findByName(AuthorityName.ROLE_ADMIN),
                authorityRepository.findByName(AuthorityName.WRITE),
                authorityRepository.findByName(AuthorityName.READ)
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
