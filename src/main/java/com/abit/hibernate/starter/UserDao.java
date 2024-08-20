package com.abit.hibernate.starter;

import com.abit.hibernate.starter.entity.QCompany;
import com.abit.hibernate.starter.entity.QUser;
import com.abit.hibernate.starter.entity.User;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import org.hibernate.Session;

import java.util.List;

import static com.abit.hibernate.starter.entity.QCompany.company;
import static com.abit.hibernate.starter.entity.QUser.user;

public class UserDao {
    private static final UserDao INSTANCE = new UserDao();

    public List<User> findAll(Session session) {
        return new JPAQuery<User>(session).select(user).from(user).fetch();
    }

    public List<User> findAllByFirstName(String firstName, Session session) {
        return new JPAQuery<User>(session).select(user).from(user)
                .where(user.personalInfo.firstname.eq(firstName)).fetch();
    }

    public List<User> findLimitedUsersByBirthday(int limit, Session session) {
        return new JPAQuery<User>(session).select(user).from(user)
                .orderBy(new OrderSpecifier(Order.ASC, user.personalInfo.birthDate))
                .limit(limit).fetch();
    }

    /*public List<Tuple> findCompanyAndAvgUsersPaymentsOrderByCompanyNames(Session session) {
        return new JPAQuery<Tuple>(session).select(company.name, payment.amount.avg())
                .from(company)
                .join(company.users, user)
                .join(user.payments, payment)
                .groupBy(company.name)
                .orderBy(company.name.asc())
                .fetch();
    }*/
    
}