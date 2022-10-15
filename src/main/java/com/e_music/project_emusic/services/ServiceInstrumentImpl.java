package com.e_music.project_emusic.services;

import com.e_music.project_emusic.entities.Instrument;
import com.e_music.project_emusic.repositories.RepositoryInstrument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceInstrumentImpl implements ServiceBase< Instrument, Long > {

    @Autowired
    private RepositoryInstrument repositoryInstrument;

    @Override
    @Transactional
    public List<Instrument> findAll() throws Exception {
        try {
            return this.repositoryInstrument.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public List< Instrument > findAllByActive( ) throws Exception {
        try {
            return this.repositoryInstrument.findAllByActive( );
        } catch ( Exception e ) {
            throw new Exception( e.getMessage( ) );
        }
    }

    @Override
    @Transactional
    public Instrument findById(Long id) throws Exception {
        try {
            Optional<Instrument> optional = this.repositoryInstrument.findById(id);
            return optional.get();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Instrument saveOne(Instrument entity) throws Exception {
        try {
            return this.repositoryInstrument.save(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Instrument updateOne(Instrument entity, Long id) throws Exception {
        try {
            Optional<Instrument> optional = this.repositoryInstrument.findById(id);
            Instrument instrument = optional.get();
            instrument = this.repositoryInstrument.save(entity);
            return instrument;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) throws Exception {
        try {
            Optional<Instrument> optional = this.repositoryInstrument.findById(id);
//            if (!optional.isEmpty()) {
            if ( optional.isPresent() ) {
                Instrument instrument = optional.get();
                instrument.setActive(!instrument.isActive());
                this.repositoryInstrument.save(instrument);
            } else {
                throw new Exception();
            }
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}