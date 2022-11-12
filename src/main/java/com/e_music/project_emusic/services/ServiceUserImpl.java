package com.e_music.project_emusic.services;

import com.e_music.project_emusic.entities.User;
import com.e_music.project_emusic.repositories.RepositoryBase;
import com.e_music.project_emusic.repositories.RepositoryUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Slf4j
public class ServiceUserImpl extends ServiceBaseImpl<User, Long> implements ServiceUser{


    public ServiceUserImpl(RepositoryBase<User, Long> repositoryBase) {
        super(repositoryBase);
    }

    @Autowired
    private RepositoryUser repositoryUser;

    @Override
    @Transactional
    public Optional<User> getByEmail(String email) {
        return repositoryUser.findByEmail(email);
    }

    @Override
    @Transactional
    public boolean existsByEmail(String email) throws Exception{
        try {
            if (repositoryUser.existsByEmail(email)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean existsById(Long id) throws Exception{
        try {
            if (repositoryUser.existsById(id)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
}