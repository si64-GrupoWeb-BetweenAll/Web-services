package main.pe.com.betweenAll.services;

import main.pe.com.betweenAll.entities.User;

import java.util.List;

public interface UserService {

    public List<User> listAll();

    public List<User> listByName(String name);

    public User listById(Long id);

    public User save(User user);

    public void delete(Long id, boolean forced);

    public User listByPasword(String password);

    public User lastUser();
}
