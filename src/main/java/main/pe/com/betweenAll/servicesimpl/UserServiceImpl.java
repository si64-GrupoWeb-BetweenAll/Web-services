package main.pe.com.betweenAll.servicesimpl;

import main.pe.com.betweenAll.entities.User;
import main.pe.com.betweenAll.repositories.UserRepository;
import main.pe.com.betweenAll.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    public User save(User user) {
        User newUser = new User(user.getName(), user.getLastname(), user.getTypeDocument(), user.getNumberDocument(), user.getPhone(), user.getEmail(), user.getPassword(), user.getImage(), user.getCity());
        User savedUser = userRepository.save(newUser);
        return savedUser;
    }

}
