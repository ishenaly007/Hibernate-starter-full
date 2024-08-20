package com.abit.hibernate.starter.dao;

import com.abit.hibernate.starter.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface Repository<K extends Serializable, E extends BaseEntity<K>> {
    E save(E entity);

    void update(E entity);

    void delete(K id);

    List<E> findAll();

    Optional<E> findById(K id);
}
