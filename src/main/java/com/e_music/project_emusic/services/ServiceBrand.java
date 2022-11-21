package com.e_music.project_emusic.services;

import com.e_music.project_emusic.entities.Brand;

import java.util.Optional;

public interface ServiceBrand extends ServiceBase<Brand, Long>{

    Optional<Brand> getByName(String name) throws Exception;

    boolean existsByName(String name) throws Exception;
}
