package com.e_music.project_emusic.repositories;


import com.e_music.project_emusic.entities.Brand;
import com.e_music.project_emusic.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryBrand extends RepositoryBase<Brand, Long> {

    @Query( value = "SELECT * FROM brand WHERE brand.name = :name", nativeQuery = true )
    Optional<Brand> findByName(@Param( "name" ) String name);

    boolean existsByName(String name);
}