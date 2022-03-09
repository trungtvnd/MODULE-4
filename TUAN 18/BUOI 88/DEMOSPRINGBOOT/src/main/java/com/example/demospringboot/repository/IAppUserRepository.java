package com.example.demospringboot.repository;

import com.example.demospringboot.model.AppUser;
import org.springframework.data.repository.CrudRepository;

public interface IAppUserRepository extends CrudRepository<AppUser,Long> {
    AppUser findByUsername(String username);
}
