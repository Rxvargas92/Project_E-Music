package com.e_music.project_emusic.controllers;

import com.e_music.project_emusic.entities.abstractions.Base;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;

public interface BaseController <E extends Base, ID extends Serializable> {

    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAll();
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAll(Pageable pageable);
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getOne(@PathVariable ID id);
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> saveOne(@RequestBody E entity) throws Exception;
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateOne(@PathVariable ID id, @RequestBody E entity);
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteById(@PathVariable ID id);
    
}
