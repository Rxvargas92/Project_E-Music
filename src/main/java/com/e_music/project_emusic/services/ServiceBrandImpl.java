package com.e_music.project_emusic.services;

import com.e_music.project_emusic.entities.Brand;
import com.e_music.project_emusic.repositories.RepositoryBase;
import org.springframework.stereotype.Service;

@Service
public class ServiceBrandImpl extends ServiceBaseImpl<Brand, Long> implements ServiceBrand {

    public ServiceBrandImpl(RepositoryBase<Brand, Long> repositoryBase){ super(repositoryBase); }
}