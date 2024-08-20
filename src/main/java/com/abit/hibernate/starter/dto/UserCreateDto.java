package com.abit.hibernate.starter.dto;

import com.abit.hibernate.starter.entity.PersonalInfo;
import com.abit.hibernate.starter.entity.Role;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public record UserCreateDto(
        @Valid
        PersonalInfo personalInfo,
        @NotNull
        String username,
        Role role,
        Integer companyId
) {
}
