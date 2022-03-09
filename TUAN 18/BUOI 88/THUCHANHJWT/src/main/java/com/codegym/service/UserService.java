package com.codegym.service;

import com.codegym.entities.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    public static List<User> userList = new ArrayList<>();
    static {
        User userKai = new User(1, "kai", "123456");
        userKai.setRoles(new String[] { "ROLE_ADMIN" });
        User userSena = new User(2, "sena", "123456");
        userSena.setRoles(new String[] { "ROLE_USER" });
        userList.add(userKai);
        userList.add(userSena);
    }
    public List<User> findAll(){
        return userList;
    }

    public User findById(int id){
        for (User user: userList) {
            if(user.getId() == id){
                return user;
            }
        }return null;
    }

    public boolean add(User user) {
        for (User userExist : userList) {
            if (user.getId() == userExist.getId() || StringUtils.equals(user.getName(), userExist.getName())) {
                return false;
            }
        }
        userList.add(user);
        return true;
    }

    public void delete(int id) {
        userList.removeIf(user -> user.getId() == id);
    }
    public User loadUserByUsername(String username) {
        for (User user : userList) {
            if (user.getName().equals(username)) {
                return user;
            }
        }
        return null;
    }
    public boolean checkLogin(User user) {
        for (User userExist : userList) {
            if (StringUtils.equals(user.getName(), userExist.getName())
                    && StringUtils.equals(user.getPassword(), userExist.getPassword())) {
                return true;
            }
        }
        return false;
    }

}
