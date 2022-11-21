package com.e_music.project_emusic.services;

import com.e_music.project_emusic.entities.Category;

import java.util.Optional;

public interface ServiceCategory extends ServiceBase<Category, Long>{

    Optional<Category> getByName(String name) throws Exception;

    boolean existsByName(String name) throws Exception;
}
