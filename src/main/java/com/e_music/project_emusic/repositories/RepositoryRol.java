package com.e_music.project_emusic.repositories;

import com.e_music.project_emusic.entities.Rol;
import com.e_music.project_emusic.enums.RolName;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryRol extends RepositoryBase<Rol, Long> {

    //@Query( value = "SELECT * FROM rol WHERE rol.rolName = :rolName", nativeQuery = true )
    Optional<Rol> findByRolName(@Param( "rolName" ) RolName rolName);

    boolean existsByRolName(RolName rolName);
}
