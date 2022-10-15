package com.e_music.project_emusic.config;

import org.hibernate.envers.RevisionListener;
import com.e_music.project_emusic.audit.Revision;



public class CustomRevisionListener implements RevisionListener{

    @Override
    public void newRevision(Object revisionEntity) {
        final Revision revision = (Revision) revisionEntity;
    }
}
