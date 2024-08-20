package com.abit.hibernate.starter.dao;

import com.abit.hibernate.starter.entity.User;

import javax.persistence.EntityManager;

public class UserRepository extends BaseRepository<Integer, User> {
    public UserRepository(EntityManager entityManager) {
        super(User.class, entityManager);
    }
    //тут можно писать запросы свои посложнее
}