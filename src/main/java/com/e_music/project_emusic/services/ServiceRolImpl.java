package com.e_music.project_emusic.services;

import com.e_music.project_emusic.entities.Rol;
import com.e_music.project_emusic.entities.User;
import com.e_music.project_emusic.enums.RolName;
import com.e_music.project_emusic.repositories.RepositoryBase;
import com.e_music.project_emusic.repositories.RepositoryRol;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Slf4j
public class ServiceRolImpl extends ServiceBaseImpl<Rol, Long> implements ServiceRol {

    public ServiceRolImpl(RepositoryBase<Rol, Long> repositoryBase) {super(repositoryBase);}

    @Autowired
    private RepositoryRol repositoryRol;


    @Override
    @Transactional
    public Optional<Rol> getByRolName(RolName rolName) throws Exception {
        try {
            if (repositoryRol.existsByRolName(rolName)) {
                Optional<Rol> rol = repositoryRol.findByRolName(rolName);
                return rol;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean existByRolName(RolName rolNombre) throws Exception {
        try {
            if (repositoryRol.existsByRolName(rolNombre)) {
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
