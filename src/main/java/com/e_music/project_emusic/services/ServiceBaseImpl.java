package com.e_music.project_emusic.services;

import com.e_music.project_emusic.entities.abstractions.Base;
import com.e_music.project_emusic.repositories.RepositoryBase;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class ServiceBaseImpl<E extends Base, ID extends Serializable>
    implements ServiceBase<E, ID> {

  protected RepositoryBase<E, ID> repositoryBase;

  public ServiceBaseImpl(RepositoryBase<E, ID> repositoryBase) {
    this.repositoryBase = repositoryBase;
  }

  @Override
  @Transactional //Indica que el método es una transacción.
  public List<E> findAll() throws Exception {
    try {
      List<E> entities = repositoryBase.findAll();
      return entities;
    } catch (Exception e) {
      log.info(e.getMessage());
      throw new Exception(e.getMessage());
    }
  }

  @Override
  @Transactional
  public E findById(ID id) throws Exception {
    try {
      Optional<E> entityOptional = repositoryBase.findById(
          id); //Optional porque no se sabe si se encontrará un registro que tenga el ID especificado como PrimaryKey.
      return entityOptional.get();
    } catch (Exception e) {
      log.info(e.getMessage());
      throw new Exception(e.getMessage());
    }
  }

  @Override
  @Transactional
  public E saveOne(E entity) throws Exception {
    try {
      entity = repositoryBase.save(entity);
      return entity;
    } catch (Exception e) {
      log.info(e.getMessage());
      throw new Exception(e.getMessage());
    }
  }

  @Override
  @Transactional
  public E updateOne(E entity, ID id) throws Exception {
    try {
      Optional<E> entityOptional = repositoryBase.findById(id);
      E entityUpdate = entityOptional.get();
      entityUpdate = repositoryBase.save(entity);
      return entityUpdate;
    } catch (Exception e) {
      log.info(e.getMessage());
      throw new Exception(e.getMessage());
    }
  }

  @Override
  @Transactional
  public boolean deleteById(ID id) throws Exception {
    try {
      if (repositoryBase.existsById(id)) {
        repositoryBase.deleteById(id);
        return true;
      } else {
        throw new Exception();
      }
    } catch (Exception e) {
      log.info(e.getMessage());
      throw new Exception(e.getMessage());
    }
  }
}
