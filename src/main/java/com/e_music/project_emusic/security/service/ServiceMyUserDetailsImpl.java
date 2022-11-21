package com.e_music.project_emusic.security.service;

import com.e_music.project_emusic.entities.User;
import com.e_music.project_emusic.services.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ServiceMyUserDetailsImpl implements UserDetailsService {

    @Autowired
    private ServiceUser serviceUser;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = serviceUser.getByEmail(email).orElseThrow(()-> new UsernameNotFoundException(email));
        return MyUserDetails.build(user);
    }

}
