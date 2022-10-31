package com.e_music.project_emusic.repositories;


import com.e_music.project_emusic.entities.Instrument;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryInstrument extends RepositoryBase< Instrument, Long> {

    @Query ( value = "SELECT * FROM instrument WHERE instrument.active = true", nativeQuery = true )
    List< Instrument > findAllByActive( );


    @Query ( value = "select * from instrument where instrument.stock > 0", nativeQuery = true)
    List<Instrument> findAllByStockGreaterThan0();
}
