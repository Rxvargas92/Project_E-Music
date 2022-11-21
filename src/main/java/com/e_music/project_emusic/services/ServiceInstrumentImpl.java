package com.e_music.project_emusic.services;

import com.e_music.project_emusic.entities.Instrument;
import com.e_music.project_emusic.repositories.RepositoryBase;
import com.e_music.project_emusic.repositories.RepositoryInstrument;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ServiceInstrumentImpl extends ServiceBaseImpl< Instrument, Long> implements ServiceInstrument {

    public ServiceInstrumentImpl( RepositoryBase< Instrument, Long > repositoryBase ) {
        super( repositoryBase );
    }

    @Autowired
    private RepositoryInstrument repositoryInstrument;

    @Override
    public Page<Instrument> findAllByActivePageable(Pageable pageable) throws Exception{
        try{
            Page<Instrument> instruments = repositoryInstrument.findAllByActivePageable(pageable);
            return instruments;
        }catch(Exception e){
            log.info(e.getMessage(),e);
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<Instrument> findByActiveAndCategoryPage(Pageable pageable, Long id) throws Exception{
        try{
            Page<Instrument> instruments = repositoryInstrument.findByActiveAndCategoryPage(pageable, id);
            return instruments;
        }catch(Exception e){
            log.info(e.getMessage(),e);
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<Instrument> searchNativePage(String filter, Pageable pageable) throws Exception{
        try{
            Page<Instrument> instruments = repositoryInstrument.searchNativePage(filter, pageable);
            return instruments;
        }catch(Exception e){
            log.info(e.getMessage(),e);
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<Instrument> findAllByActiveAndCategory(Long idCategory, Pageable pageable) throws Exception {
        try{
            Page<Instrument> instruments = repositoryInstrument.findAllByActiveAndCategory(idCategory, pageable);
            return instruments;
        }catch(Exception e){
            log.info(e.getMessage(),e);
            throw new Exception(e.getMessage());
        }
    }
}