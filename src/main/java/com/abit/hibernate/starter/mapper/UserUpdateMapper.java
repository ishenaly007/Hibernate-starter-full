package com.abit.hibernate.starter.mapper;

import com.abit.hibernate.starter.dao.CompanyRepository;
import com.abit.hibernate.starter.dto.UserCreateDto;
import com.abit.hibernate.starter.dto.UserUpdateDto;
import com.abit.hibernate.starter.entity.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserUpdateMapper implements Mapper<UserUpdateDto, User> {
    private final CompanyRepository companyRepository;

    @Override
    public User mapFrom(UserUpdateDto obj) {
        return User.builder()
                .id(obj.id())
                .personalInfo(obj.personalInfo())
                .username(obj.username())
                .role(obj.role())
                .company(companyRepository.findById(obj.companyId())
                        .orElseThrow(IllegalArgumentException::new))
                .build();
    }
}