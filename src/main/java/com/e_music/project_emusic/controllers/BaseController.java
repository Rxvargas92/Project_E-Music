package com.e_music.project_emusic.controllers;

import com.e_music.project_emusic.entities.Base;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;

public interface BaseController <E extends Base, ID extends Serializable> {

    public ResponseEntity<?> getAll();
    public ResponseEntity<?> getOne(@PathVariable ID id);
    public ResponseEntity<?> saveOne(@RequestBody E entity);
    public ResponseEntity<?> updateOne(@PathVariable ID id, @RequestBody E entity);
    public ResponseEntity<?> deleteById(@PathVariable ID id);
}
