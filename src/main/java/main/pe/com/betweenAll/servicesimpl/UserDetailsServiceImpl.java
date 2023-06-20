package main.pe.com.betweenAll.servicesimpl;

import main.pe.com.betweenAll.entities.User;
import main.pe.com.betweenAll.repositories.UserRepository;
import main.pe.com.betweenAll.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRespository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRespository.findByName(username);
        if (user!=null){
            return new SecurityUser(user);
        }
        throw  new UsernameNotFoundException("User not found: "+username);
    }
}
