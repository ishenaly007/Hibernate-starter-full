package com.abit.hibernate.starter.mapper;

import com.abit.hibernate.starter.dao.CompanyRepository;
import com.abit.hibernate.starter.dto.UserCreateDto;
import com.abit.hibernate.starter.entity.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserCreateMapper implements Mapper<UserCreateDto, User> {
    private final CompanyRepository companyRepository;

    @Override
    public User mapFrom(UserCreateDto obj) {
        return User.builder()
                .personalInfo(obj.personalInfo())
                .username(obj.username())
                .role(obj.role())
                .company(companyRepository.findById(obj.companyId())
                        .orElseThrow(IllegalArgumentException::new))
                .build();
    }
}