package com.e_music.project_emusic.services;

import com.e_music.project_emusic.entities.Instrument;
import com.e_music.project_emusic.entities.User;
import com.e_music.project_emusic.repositories.RepositoryBase;
import com.e_music.project_emusic.repositories.RepositoryInstrument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceInstrumentImpl extends ServiceBaseImpl< Instrument, Long> implements ServiceInstrument {


    public ServiceInstrumentImpl( RepositoryBase< Instrument, Long > repositoryBase ) {
        super( repositoryBase );
    }
}