package com.e_music.project_emusic.services;

import com.e_music.project_emusic.entities.User;
import org.springframework.security.core.Authentication;

import java.util.Optional;

public interface ServiceUser extends ServiceBase<User, Long>{

    Optional<User> getByEmail(String email);

    boolean existsByEmail(String email) throws Exception;

    boolean existsById(Long id) throws Exception;

    User getUserAutenticated(Authentication auth) throws Exception;
}
