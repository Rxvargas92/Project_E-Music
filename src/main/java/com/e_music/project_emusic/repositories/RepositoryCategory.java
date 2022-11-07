package com.e_music.project_emusic.repositories;


import com.e_music.project_emusic.entities.Category;
import com.e_music.project_emusic.entities.Instrument;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositoryCategory extends RepositoryBase<Category, Long> {

    @Query ( value = "SELECT * FROM category WHERE category.active = true", nativeQuery = true )
    List<Category> findAllByActive( ) throws Exception;

}
