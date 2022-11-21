package com.e_music.project_emusic.repositories;


import com.e_music.project_emusic.entities.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryCategory extends RepositoryBase<Category, Long> {

    @Query( value = "SELECT * FROM category WHERE category.name = :name", nativeQuery = true )
    Optional<Category> findByName(@Param( "name" ) String name);

    boolean existsByName(String name);

}
