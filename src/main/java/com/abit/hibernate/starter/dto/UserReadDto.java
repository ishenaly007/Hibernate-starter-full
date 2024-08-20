package com.abit.hibernate.starter.dto;

import com.abit.hibernate.starter.entity.PersonalInfo;
import com.abit.hibernate.starter.entity.Role;

public record UserReadDto(
        Integer id,
        PersonalInfo personalInfo,
        String username,
        Role role,
        CompanyReadDto company
) {
}
