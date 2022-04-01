package com.codegym.vn.democrud2tableangularsecurity.service;



import com.codegym.vn.democrud2tableangularsecurity.model.account.Role;
import com.codegym.vn.democrud2tableangularsecurity.model.account.RoleName;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findByName(RoleName roleName);
}
