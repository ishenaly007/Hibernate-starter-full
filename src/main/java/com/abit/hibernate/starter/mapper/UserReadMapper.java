package com.abit.hibernate.starter.mapper;

import com.abit.hibernate.starter.dto.UserReadDto;
import com.abit.hibernate.starter.entity.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserReadMapper implements Mapper<User, UserReadDto> {
    private final CompanyReadMapper companyReadMapper;
    @Override
    public UserReadDto mapFrom(User obj) {
        return new UserReadDto(obj.getId(),
                obj.getPersonalInfo(),
                obj.getUsername(),
                obj.getRole(),
                new CompanyReadMapper().mapFrom(obj.getCompany())
        );
    }
}
