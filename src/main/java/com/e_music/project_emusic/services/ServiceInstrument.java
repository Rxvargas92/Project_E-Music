package com.e_music.project_emusic.services;

import com.e_music.project_emusic.entities.Instrument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface ServiceInstrument extends ServiceBase<Instrument, Long>{

    Page< Instrument > findAllByActivePageable(Pageable pageable) throws Exception;

    Page<Instrument> findByActiveAndCategoryPage(Pageable pageable, @Param("idCategory") Long id) throws Exception;

    Page<Instrument> searchNativePage(@Param("filter") String filter, Pageable pageable) throws Exception;

    Page<Instrument> findAllByActiveAndCategory(Long idCategory, Pageable pageable) throws Exception;


}
