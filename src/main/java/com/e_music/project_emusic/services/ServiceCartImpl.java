package com.e_music.project_emusic.services;

import com.e_music.project_emusic.entities.Brand;
import com.e_music.project_emusic.entities.Cart;
import com.e_music.project_emusic.repositories.RepositoryBase;
import com.e_music.project_emusic.repositories.RepositoryCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceCartImpl extends ServiceBaseImpl<Cart, Long> implements ServiceCart{

    public ServiceCartImpl(RepositoryBase<Cart, Long> repositoryBase){ super(repositoryBase); }

}