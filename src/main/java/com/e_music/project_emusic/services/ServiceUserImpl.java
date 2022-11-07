package com.e_music.project_emusic.services;

import com.e_music.project_emusic.entities.User;
import com.e_music.project_emusic.repositories.RepositoryBase;
import org.springframework.stereotype.Service;

@Service
public class ServiceUserImpl extends ServiceBaseImpl<User, Long> implements ServiceUser{


    public ServiceUserImpl(RepositoryBase<User, Long> repositoryBase) {
        super(repositoryBase);
    }
}