package com.e_music.project_emusic.repositories;


import com.e_music.project_emusic.entities.Instrument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositoryInstrument extends RepositoryBase< Instrument, Long>, PagingAndSortingRepository<Instrument, Long>{

    @Query ( value = "SELECT * FROM instrument WHERE instrument.active = true", nativeQuery = true )
    List< Instrument > findAllByActive( );

    @Query ( value = "SELECT * FROM instrument WHERE instrument.id = :id AND instrument.active = true", nativeQuery = true)
    Optional<Instrument> findAllByAndActive(@Param("id") long id);

    @Query ( value = "SELECT * FROM instrument WHERE instrument.active = true",
            countQuery = "SELECT  count(5) from instrument",
            nativeQuery = true )
    Page< Instrument > findAllByActivePageable(Pageable pageable);

    @Query(value = "SELECT * FROM instrument WHERE instrument.active = true AND instrument.fk_category = :idCategory",
            nativeQuery = true,
            countQuery = "SELECT count(*) FROM instrument")
    Page<Instrument> findByActiveAndCategoryPage(Pageable pageable, @Param("idCategory") Long id);


    @Query(
            value = "SELECT * FROM instrument WHERE instrument.name LIKE %:filter%",
            countQuery = "SELECT count(*) FROM instrument",
            nativeQuery = true
    )
    Page<Instrument> searchNativePage(@Param("filter") String filter, Pageable pageable);

    @Query(value = "SELECT * FROM instrument WHERE instrument.active = true AND instrument.fk_category = :idCategory",
            nativeQuery = true,
            countQuery = "SELECT count(*) FROM instrument")
    Page<Instrument> findAllByActiveAndCategory(@Param("idCategory") Long idCategory, Pageable pageable);
}
