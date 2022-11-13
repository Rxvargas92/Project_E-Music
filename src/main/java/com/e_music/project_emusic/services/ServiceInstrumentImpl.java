package com.e_music.project_emusic.services;

import com.e_music.project_emusic.entities.Instrument;
import com.e_music.project_emusic.repositories.RepositoryBase;
import org.springframework.stereotype.Service;

@Service
public class ServiceInstrumentImpl extends ServiceBaseImpl< Instrument, Long> implements ServiceInstrument {


    public ServiceInstrumentImpl( RepositoryBase< Instrument, Long > repositoryBase ) {
        super( repositoryBase );
    }
}