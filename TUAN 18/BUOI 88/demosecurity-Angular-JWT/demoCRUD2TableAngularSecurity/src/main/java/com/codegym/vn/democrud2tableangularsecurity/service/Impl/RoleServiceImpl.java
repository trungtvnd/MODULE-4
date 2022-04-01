package com.codegym.vn.democrud2tableangularsecurity.service.Impl;


import com.codegym.vn.democrud2tableangularsecurity.model.account.Role;
import com.codegym.vn.democrud2tableangularsecurity.model.account.RoleName;
import com.codegym.vn.democrud2tableangularsecurity.repository.IRoleRepository;
import com.codegym.vn.democrud2tableangularsecurity.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleRepository repository;

    @Override
    public Optional<Role> findByName(RoleName roleName) {
        return repository.findByName(roleName);
    }
}
