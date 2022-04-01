package com.codegym.vn.democrud2tableangularsecurity.repository;

import com.codegym.vn.democrud2tableangularsecurity.model.account.Role;
import com.codegym.vn.democrud2tableangularsecurity.model.account.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName roleName);
}
