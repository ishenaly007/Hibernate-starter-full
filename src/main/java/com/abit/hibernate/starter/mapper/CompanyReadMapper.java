package com.abit.hibernate.starter.mapper;

import com.abit.hibernate.starter.dto.CompanyReadDto;
import com.abit.hibernate.starter.entity.Company;

public class CompanyReadMapper implements Mapper<Company, CompanyReadDto> {
    @Override
    public CompanyReadDto mapFrom(Company obj) {
        return new CompanyReadDto(obj.getId(), obj.getName());
    }
}
