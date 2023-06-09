package main.pe.com.betweenAll.services;

import main.pe.com.betweenAll.entities.GroupUser;

import java.util.List;

public interface GroupUserService {
    public GroupUser save(GroupUser groupUser);
    public void delete(Long id, boolean forced);
    public List<GroupUser> listAll();
}
