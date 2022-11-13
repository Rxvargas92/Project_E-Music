package com.e_music.project_emusic.services;

import com.e_music.project_emusic.entities.Brand;
import com.e_music.project_emusic.repositories.RepositoryBase;
import com.e_music.project_emusic.repositories.RepositoryBrand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@Service
@Slf4j
public class ServiceBrandImpl extends ServiceBaseImpl<Brand, Long> implements ServiceBrand {

    public ServiceBrandImpl(RepositoryBase<Brand, Long> repositoryBase){ super(repositoryBase); }

    @Autowired
    private RepositoryBrand repositoryBrand;


    @Override
    public Optional<Brand> getByName(String name) throws Exception {
        return repositoryBrand.findByName(name);
    }

    @Override
    public boolean existsByName(String name) throws Exception {
        try{
            if (repositoryBrand.existsByName(name)){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
}