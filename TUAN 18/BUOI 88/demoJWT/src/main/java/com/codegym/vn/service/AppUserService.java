package com.codegym.vn.service;


import com.codegym.vn.model.AppUser;
import com.codegym.vn.repository.IAppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AppUserService implements IAppUserService{
    @Autowired
    private IAppUserRepository repository;
    @Override
    public List<AppUser> getAll() {
        return (List<AppUser>) repository.findAll();
    }

    @Override
    public void save(AppUser appUser) {
        repository.save(appUser);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = repository.findByUsername(username);

        return new User(appUser.getUsername(), appUser.getPassword(),appUser.getRoles());
    }
}
