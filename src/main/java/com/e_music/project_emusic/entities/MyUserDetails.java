package com.e_music.project_emusic.entities;

import com.e_music.project_emusic.repositories.RepositoryUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.CollectionUtils;

import java.security.Permission;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyUserDetails implements UserDetailsService {

    private RepositoryUser userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    /*
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userDb = userRepository.findByEmail(username);
        if(userDb==null){
            throw new UsernameNotFoundException("Usuario o password inválidos");
        }else {
            User user = userDb;
            List<Rol> roles = user.getRoles().stream().toList();
            Set<String> rols = new HashSet<>();
            if (!CollectionUtils.isEmpty(roles)) {
                for (Rol r : roles) {
                    rols.add(r.getType());
                }
            }
            return org.springframework.security.core.userdetails.User
                    .withUsername(username)
                    .roles(rols.toArray(new String[0]))
                    .password(user.getPassword())
                    .build();
        }
    }
    private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getType())).collect(Collectors.toList());
    }
    */

}
