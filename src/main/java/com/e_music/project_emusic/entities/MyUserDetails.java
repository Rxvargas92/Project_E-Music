package com.e_music.project_emusic.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyUserDetails {

    private Long id;
    private String name;
    private String lastName;
    private Integer dni;
    private Collection<Rol> roles;
    private boolean active = true;
    private String email;
    private String password;
    private Address address;
    private Cart cart;

    public MyUserDetails(String name, String lastName, Integer dni, Collection<Rol> roles, boolean active, String email, String password, Address address, Cart cart) {
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
        this.roles = roles;
        this.active = active;
        this.email = email;
        this.password = password;
        this.address = address;
        this.cart = cart;
    }

    public MyUserDetails(String email) {
        this.email = email;
    }
}
