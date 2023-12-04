package base.service;

import base.entity.BaseEntity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

public interface BaseService<T extends BaseEntity<ID>,ID extends Serializable>{

    T saveOrUpdate(T entity);

    Optional<T> findById(ID id);

    void deleteById(ID id);

    Collection<T> findAll();
    boolean existsById(ID id);

    void beginTransaction();

    void commitTransaction();

    void rollback();

}
