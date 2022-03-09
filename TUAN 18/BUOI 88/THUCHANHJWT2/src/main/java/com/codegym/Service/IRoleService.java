package com.codegym.Service;

import com.codegym.model.Role;

public interface IRoleService extends IGeneralService<Role> {
    Role findByName(String name);
}
