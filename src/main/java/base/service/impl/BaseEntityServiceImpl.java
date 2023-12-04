package base.service.impl;

import base.entity.BaseEntity;
import base.repository.BaseEntityRepository;
import base.service.BaseService;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
public class BaseEntityServiceImpl<T extends BaseEntity<ID>,ID extends Serializable,
        R extends BaseEntityRepository<T,ID>> implements BaseService<T,ID> {

   protected final R repository;

    @Override
    public T saveOrUpdate(T entity) {
       return repository.saveOrUpdate(entity);
    }

    @Override
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public Collection<T> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean existsById(ID id) {
        return repository.existsById(id);
    }

    @Override
    public void beginTransaction() {
            repository.beginTransaction();
    }

    @Override
    public void commitTransaction() {
            repository.commitTransaction();
    }

    @Override
    public void rollback() {
        repository.rollBack();
    }
}
