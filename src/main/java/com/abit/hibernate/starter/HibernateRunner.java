package com.abit.hibernate.starter;

import com.abit.hibernate.starter.dao.CompanyRepository;
import com.abit.hibernate.starter.dao.PaymentRepository;
import com.abit.hibernate.starter.dao.UserRepository;
import com.abit.hibernate.starter.dto.UserCreateDto;
import com.abit.hibernate.starter.dto.UserUpdateDto;
import com.abit.hibernate.starter.entity.*;
import com.abit.hibernate.starter.mapper.CompanyReadMapper;
import com.abit.hibernate.starter.mapper.UserCreateMapper;
import com.abit.hibernate.starter.mapper.UserReadMapper;
import com.abit.hibernate.starter.mapper.UserUpdateMapper;
import com.abit.hibernate.starter.service.UserService;
import com.abit.hibernate.starter.utils.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Proxy;
import java.time.LocalDate;

@Slf4j
public class HibernateRunner {

    public static void main(String[] args) {

        try (var sessionFactory = HibernateUtil.buildSessionFactory()) {
            var session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(),
                    new Class[]{Session.class},
                    ((proxy, method, args1) -> method.invoke(sessionFactory.getCurrentSession(), args1)));
            session.beginTransaction();

            var companyReadMapper = new CompanyReadMapper();
            var companyRepository = new CompanyRepository(session);

            var userReadMapper = new UserReadMapper(companyReadMapper);
            var userCreateMapper = new UserCreateMapper(companyRepository);
            var userUpdateMapper = new UserUpdateMapper(companyRepository);

            var userRepository = new UserRepository(session);
            var userService = new UserService(userRepository, userReadMapper, userCreateMapper, userUpdateMapper);

            UserCreateDto userCreateDto = new UserCreateDto(
                    PersonalInfo.builder()
                            .firstname("Isheeenaly")
                            .lastname("Vladimirov")
                            .birthDate(new Birthday(LocalDate.now()))
                            .build(),
                    "Busy00",
                    Role.USER,
                    2
            );

            var a = 1;

            userService.create(userCreateDto);
            //userService.findById(1).ifPresent(System.out::println);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            log.info("exception :" + e);
        }
    }
}