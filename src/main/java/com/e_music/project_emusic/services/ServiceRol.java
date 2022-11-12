package com.e_music.project_emusic.services;

import com.e_music.project_emusic.entities.Rol;
import com.e_music.project_emusic.enums.RolName;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ServiceRol extends ServiceBase<Rol,Long> {


    Optional<Rol> getByRolName(RolName rolNombre) throws Exception;

    boolean existByRolName(RolName rolNombre) throws Exception;
}
