package com.e_music.project_emusic.services;

import com.e_music.project_emusic.entities.Cart;
import com.e_music.project_emusic.repositories.RepositoryBase;
import org.springframework.stereotype.Service;

@Service
public class ServiceCartImpl extends ServiceBaseImpl<Cart, Long> implements ServiceCart{

    public ServiceCartImpl(RepositoryBase<Cart, Long> repositoryBase){ super(repositoryBase); }

}