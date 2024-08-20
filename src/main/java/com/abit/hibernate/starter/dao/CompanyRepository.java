package com.abit.hibernate.starter.dao;

import com.abit.hibernate.starter.entity.Company;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class CompanyRepository extends BaseRepository<Integer, Company>{
    public CompanyRepository(EntityManager entityManager) {
        super(Company.class, entityManager);
    }
    //тут можно писать запросы свои посложнее
}