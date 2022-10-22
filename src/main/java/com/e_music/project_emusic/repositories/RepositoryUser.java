package com.e_music.project_emusic.repositories;

import com.e_music.project_emusic.entities.User;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryUser extends RepositoryBase<User, Long> {
    public User findByEmail(String email);

}