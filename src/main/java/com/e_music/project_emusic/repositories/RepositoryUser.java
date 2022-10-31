package com.e_music.project_emusic.repositories;

import com.e_music.project_emusic.entities.Instrument;
import com.e_music.project_emusic.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryUser extends RepositoryBase<User, Long> {

    @Query ( value = "SELECT * FROM user WHERE user.email = :email", nativeQuery = true )
    User findByEmail( @Param ( "email" ) String email);

}