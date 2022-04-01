package com.codegym.vn.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "appUsers")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
        private String email;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;
}
