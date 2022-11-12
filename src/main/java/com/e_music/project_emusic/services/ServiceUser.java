package com.e_music.project_emusic.services;

import com.e_music.project_emusic.entities.User;

import java.util.Optional;

public interface ServiceUser extends ServiceBase<User, Long>{

    public Optional<User> getByEmail(String email);

    boolean existsByEmail(String email) throws Exception;

    boolean existsById(Long id) throws Exception;
}
