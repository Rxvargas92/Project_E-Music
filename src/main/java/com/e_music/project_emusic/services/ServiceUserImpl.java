package com.e_music.project_emusic.services;

import com.e_music.project_emusic.entities.MyUserDetails;
import com.e_music.project_emusic.entities.Rol;
import com.e_music.project_emusic.entities.User;
import com.e_music.project_emusic.repositories.RepositoryBase;
import com.e_music.project_emusic.repositories.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class ServiceUserImpl extends ServiceBaseImpl<User, Long> implements ServiceUser{

    @Autowired
    private RepositoryUser repositoryUser;

    @Autowired
    private static BCryptPasswordEncoder encoder;

    public ServiceUserImpl(RepositoryBase<User, Long> repositoryBase){ super(repositoryBase); }

    @Override
    public User save(MyUserDetails myUserDetails){
        User user = new User(myUserDetails.getName(), myUserDetails.getLastName(),
                myUserDetails.getDni(), myUserDetails.getRoles(), myUserDetails.isActive(),
                myUserDetails.getEmail(), encoder.encode(myUserDetails.getPassword()), myUserDetails.getAddress(),
                myUserDetails.getCart());
        return repositoryUser.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repositoryUser.findByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException("User or password incorrect");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(), mapRol(user.getRoles()));
    }

    public Collection<? extends GrantedAuthority> mapRol(Collection<Rol> rols){
        return rols.stream().map(rol -> new SimpleGrantedAuthority(rol.getType())).collect(Collectors.toList());
    }
}