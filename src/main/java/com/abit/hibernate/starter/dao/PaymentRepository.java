package com.abit.hibernate.starter.dao;

import com.abit.hibernate.starter.entity.Payment;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;

public class PaymentRepository extends BaseRepository<Integer, Payment> {

    public PaymentRepository(EntityManager entityManager) {
        super(Payment.class, entityManager);
    }
    //тут можно писать запросы свои посложнее
}