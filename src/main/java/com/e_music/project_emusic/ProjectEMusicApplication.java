package com.e_music.project_emusic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;


@SpringBootApplication
public class ProjectEMusicApplication {

    public static void main( String[] args ) {
        SpringApplication.run( ProjectEMusicApplication.class, args );
    }
}
