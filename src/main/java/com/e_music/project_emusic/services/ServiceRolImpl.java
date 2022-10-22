package com.e_music.project_emusic.services;

import com.e_music.project_emusic.entities.Rol;
import com.e_music.project_emusic.repositories.RepositoryBase;
import org.springframework.stereotype.Service;

@Service
public class ServiceRolImpl extends ServiceBaseImpl<Rol, Long> implements ServiceRol {

    public ServiceRolImpl(RepositoryBase<Rol, Long> repositoryBase) {super(repositoryBase);}
}
