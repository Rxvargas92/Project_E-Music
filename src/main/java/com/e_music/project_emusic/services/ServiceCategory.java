package com.e_music.project_emusic.services;

import com.e_music.project_emusic.entities.Category;
import org.aspectj.apache.bcel.classfile.Module;

import java.util.List;
import java.util.Optional;

public interface ServiceCategory extends ServiceBase<Category, Long>{

    List<Category> findAllByActive() throws Exception;
}
