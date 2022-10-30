package com.e_music.project_emusic.services;

import com.e_music.project_emusic.entities.MyUserDetails;
import com.e_music.project_emusic.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface ServiceUser extends ServiceBase<User, Long>, UserDetailsService {
    public User save(MyUserDetails myUserDetails);
}
