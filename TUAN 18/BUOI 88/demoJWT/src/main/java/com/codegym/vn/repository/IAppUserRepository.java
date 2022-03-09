package com.codegym.vn.repository;


import com.codegym.vn.model.AppUser;
import org.springframework.data.repository.CrudRepository;

public interface IAppUserRepository extends CrudRepository<AppUser,Long> {
    AppUser findByUsername(String username);
}
